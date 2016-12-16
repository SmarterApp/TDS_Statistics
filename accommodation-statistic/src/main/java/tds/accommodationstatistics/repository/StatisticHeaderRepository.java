package tds.accommodationstatistics.repository;

import java.sql.SQLException;

import TDS.Shared.Exceptions.ReturnStatusException;
import tds.accommodationstatistics.domain.AccommodationVideoASL;
import tds.accommodationstatistics.domain.StatisticHeader;

/**
@author Ernesto De La Luz Martinez
*/
public interface StatisticHeaderRepository {

	StatisticHeader addHeader(StatisticHeader header)throws ReturnStatusException;
	
	StatisticHeader findByTestKey(StatisticHeader header)throws ReturnStatusException;
}
