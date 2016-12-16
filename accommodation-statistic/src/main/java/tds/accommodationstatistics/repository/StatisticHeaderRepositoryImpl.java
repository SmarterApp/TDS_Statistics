package tds.accommodationstatistics.repository;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import TDS.Shared.Exceptions.ReturnStatusException;
import tds.accommodationstatistics.domain.StatisticHeader;
import tds.accommodationstatistics.util.HeaderRowMapper;
import tds.dll.common.performance.utils.UuidAdapter;

/**
@author Ernesto De La Luz Martinez
*/
@Repository
public class StatisticHeaderRepositoryImpl implements StatisticHeaderRepository{
	private final Logger logger = LoggerFactory.getLogger(StatisticHeaderRepositoryImpl.class);
	
 
	private NamedParameterJdbcTemplate namedParameterJdbcTemplate;
	@Autowired
	public void setDataSource(DataSource dataSource) {
		this.namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
	}
	
	@Override
	public StatisticHeader addHeader(StatisticHeader header) throws ReturnStatusException {
		logger.debug("persisting " + namedParameterJdbcTemplate);
		
		UUID headerId = UUID.randomUUID(); 
		header.setId(headerId);
		
		Map<String, Object> parameters = new HashMap<>();		
		parameters.put("id", UuidAdapter.getBytesFromUUID(header.getId()));
		parameters.put("date",header.getDate());
		parameters.put("testKey", header.getTestKey());
		
		final String SQL = "INSERT INTO\n" + "acc_statistics.stat_header ("
				+ "id," + " date, _efk_testee)\n"
				+ "VALUES(" 
				+ ":id," + ":date, :testKey)";

		try {			
			logger.debug("parameters: "+parameters);
			namedParameterJdbcTemplate.update(SQL, parameters);
		} catch (DataAccessException e) {
			logger.error(String.format("%s INSERT threw exception", SQL), e);
			throw new ReturnStatusException(e);
		}
 
		return header;
	}

	@Override
	public StatisticHeader findByTestKey(StatisticHeader header) throws ReturnStatusException {			
		Map<String, Object> parameters = new HashMap<>();		
		parameters.put("testKey",header.getTestKey());
		
		final String SQL = "SELECT hex(id) as id ,_efk_testee, date FROM \n" + "acc_statistics.stat_header "
				+ "WHERE " 
				+ "_efk_testee = :testKey";

		try {			
			logger.debug("parameters: "+parameters);
			StatisticHeader result = namedParameterJdbcTemplate.queryForObject(SQL, parameters, new HeaderRowMapper());
			return result;
		}catch (EmptyResultDataAccessException e) {
			return null;
		} catch (DataAccessException e) {
			logger.error(String.format("%s SELECT threw exception", SQL), e);
			throw new ReturnStatusException(e);
		}		
	}

}
