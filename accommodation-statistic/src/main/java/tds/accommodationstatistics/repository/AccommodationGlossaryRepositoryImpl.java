package tds.accommodationstatistics.repository;

import java.util.HashMap;
import java.util.List;
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
import tds.accommodationstatistics.domain.AccommodationGlossary;
import tds.accommodationstatistics.domain.AccommodationVideoASL;
import tds.accommodationstatistics.domain.GlossaryTerms;
import tds.dll.common.performance.utils.UuidAdapter;

/**
@author Ernesto De La Luz Martinez
*/
@Repository
public class AccommodationGlossaryRepositoryImpl implements AccommodationsRepository{

	private final Logger logger = LoggerFactory.getLogger(AccommodationGlossaryRepositoryImpl.class);

	private NamedParameterJdbcTemplate namedParameterJdbcTemplate;
	@Autowired
	public void setDataSource(DataSource dataSource) {
		this.namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
	}

	@Override
	public <T extends AbstractAccommodation> void addAccommodation(T accommodation) throws ReturnStatusException {
		logger.debug("persisting " + namedParameterJdbcTemplate);
		  
		//cast AbstractAccommodation to Accommodation Glossary
		AccommodationGlossary acc = (AccommodationGlossary)accommodation;
		//persist accommodation
		UUID accommodationId = UUID.randomUUID(); 
		
		Map<String, Object> parameters = new HashMap<>();
		parameters.put("id",  UuidAdapter.getBytesFromUUID(accommodationId));
		parameters.put("typeGlossary", acc.getTypeGlossary());

		//add header
		parameters.put("fk_stat_header", UuidAdapter.getBytesFromUUID(accommodation.getHeader().getId()));


		final String SQL = "INSERT INTO\n" + "acc_statistics.acc_glossary ("
				+ "id," + "type_glossary," 
				+ "fk_stat_header) \n"
				+ "VALUES(" 
				+ ":id," + ":typeGlossary,"  
				+ ":fk_stat_header)";
		 
		try {
			logger.debug("parameters: "+parameters);
			namedParameterJdbcTemplate.update(SQL, parameters);
			//persist each glossary term
			for (GlossaryTerms gt : acc.getGlossaries()) {
				persistGlossaryTerms(accommodationId, gt);
			}
		} catch (DataAccessException e) {
			logger.error(String.format("%s INSERT threw exception", SQL), e);
			throw new ReturnStatusException(e);
		}
			
	}
	
	private void persistGlossaryTerms(UUID accommodationGlossaryId, GlossaryTerms glossaryTerm)throws ReturnStatusException{
		
	 		
		Map<String, Object> parameters = new HashMap<>();
		parameters.put("fk_acc_glossary", UuidAdapter.getBytesFromUUID(accommodationGlossaryId));
		parameters.put("item", glossaryTerm.getItem());
		parameters.put("term", glossaryTerm.getTerm());
		parameters.put("invoke_counter", glossaryTerm.getInvokeCounter());
		parameters.put("when_invoked", glossaryTerm.getWhenInvoked());
		parameters.put("audio_play_counter", glossaryTerm.getAudioPlayCounter());
		
		 
		final String SQL = "INSERT INTO\n" + "acc_statistics.glossary_terms ("
				+ "fk_acc_glossary," + "item," + "term,"+ "invoke_counter,"+ "when_invoked,"+ "audio_play_counter"
				+ " ) \n"
				+ "VALUES(" 
				+ ":fk_acc_glossary, :item, 	 :term,   	:invoke_counter, :when_invoked, 	:audio_play_counter"  
				+ " )";
		 
		try {
			logger.debug("parameters: "+parameters);
			namedParameterJdbcTemplate.update(SQL, parameters);			 
		} catch (DataAccessException e) {
			logger.error(String.format("%s INSERT threw exception", SQL), e);
			throw new ReturnStatusException(e);
		}
		 
	}

}
