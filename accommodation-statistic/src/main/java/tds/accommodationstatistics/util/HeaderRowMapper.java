package tds.accommodationstatistics.util;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.UUID;

import org.springframework.jdbc.core.RowMapper;

import tds.accommodationstatistics.domain.StatisticHeader;

/**
@author Ernesto De La Luz Martinez
*/
public class HeaderRowMapper implements RowMapper<StatisticHeader>{

	@Override
	public StatisticHeader mapRow(ResultSet rs, int rowNum) throws SQLException {
		StatisticHeader header = new StatisticHeader();	
		
		 UUID id = StatisticsUtil.getUUID( rs.getString("id"));
		 header.setId(id);
		 header.setTestKey(rs.getLong("_efk_testee"));
		 header.setDate(rs.getDate("date"));
		return header;
	}

}
