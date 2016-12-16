package tds.accommodationstatistics.repository;

import java.sql.SQLException;

import AIR.Common.data.ResponseData;
import TDS.Shared.Exceptions.ReturnStatusException;
import tds.accommodationstatistics.domain.AbstractAccommodation;
import tds.accommodationstatistics.domain.AccommodationVideoASL;

/**
@author Ernesto De La Luz Martinez
*/
public interface AccommodationsRepository {

	<T extends AbstractAccommodation> void addAccommodation(T accommodation)throws ReturnStatusException;
	 
}
