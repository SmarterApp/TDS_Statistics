package tds.accommodationstatistics.service;

import AIR.Common.data.ResponseData;
import TDS.Shared.Exceptions.ReturnStatusException;
import tds.accommodationstatistics.domain.AccommodationVideoASL;
import tds.accommodationstatistics.domain.StatisticHeader;

/**
@author Ernesto De La Luz Martinez
*/
public interface StatisticHeaderService {
	StatisticHeader findOrCreateHeader(StatisticHeader header)throws ReturnStatusException;
}
