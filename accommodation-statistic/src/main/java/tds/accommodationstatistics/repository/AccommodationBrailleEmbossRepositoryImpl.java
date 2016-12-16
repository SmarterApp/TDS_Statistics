package tds.accommodationstatistics.repository;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import TDS.Shared.Exceptions.ReturnStatusException;
import tds.accommodationstatistics.domain.AbstractAccommodation;
import tds.accommodationstatistics.domain.AccommodationBrailleEmboss;
import tds.accommodationstatistics.domain.AccommodationGlossary;
import tds.accommodationstatistics.domain.GlossaryTerms;
import tds.dll.common.performance.utils.UuidAdapter;

/**
@author Ernesto De La Luz Martinez
*/
@Repository
public class AccommodationBrailleEmbossRepositoryImpl implements AccommodationsRepository {
	private final Logger logger = LoggerFactory.getLogger(AccommodationBrailleEmbossRepositoryImpl.class);

	private NamedParameterJdbcTemplate namedParameterJdbcTemplate;
	@Autowired
	public void setDataSource(DataSource dataSource) {
		this.namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
	}
	@Override
	public <T extends AbstractAccommodation> void addAccommodation(T accommodation) throws ReturnStatusException {
		logger.debug("persisting " + namedParameterJdbcTemplate);
		  
		//cast AbstractAccommodation to Accommodation Braille
		AccommodationBrailleEmboss acc = (AccommodationBrailleEmboss)accommodation;
		//persist accommodation
		UUID accommodationId = UUID.randomUUID(); 
		
		Map<String, Object> parameters = new HashMap<>();
		parameters.put("id",  UuidAdapter.getBytesFromUUID(accommodationId));
		parameters.put("embossed_file_name", acc.getEmbossedFileName());
		parameters.put("when_embossed", acc.getWhenEmbossed());

		//add header
		parameters.put("fk_stat_header", UuidAdapter.getBytesFromUUID(accommodation.getHeader().getId()));


		final String SQL = "INSERT INTO\n" + "acc_statistics.acc_braille_embossed ("
				+ "id, 	embossed_file_name, 	when_embossed, " 
				+ "fk_stat_header) \n"
				+ "VALUES(" 
				+ ":id, :embossed_file_name, 	:when_embossed,  "  
				+ ":fk_stat_header)";
		 
		 
		try {
			logger.debug("parameters: "+parameters);
			namedParameterJdbcTemplate.update(SQL, parameters);			
		} catch (DataAccessException e) {
			logger.error(String.format("%s INSERT threw exception", SQL), e);
			throw new ReturnStatusException(e);
		}
		
	}

}
