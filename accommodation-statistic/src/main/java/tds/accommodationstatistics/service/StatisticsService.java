package tds.accommodationstatistics.service;

import AIR.Common.data.ResponseData;
import TDS.Shared.Exceptions.ReturnStatusException;
import tds.accommodationstatistics.domain.AbstractAccommodation;
import tds.accommodationstatistics.domain.AccommodationVideoASL;

/**
@author Ernesto De La Luz Martinez
*/
public interface StatisticsService {
	
	<T extends AbstractAccommodation>  ResponseData<String> persistStatistic(T accommodation);
	
}
