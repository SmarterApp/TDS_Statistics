package tds.accommodationstatistics.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import AIR.Common.data.ResponseData;
import TDS.Shared.Exceptions.ReturnStatusException;
import tds.accommodationstatistics.bo.AccommodationVideoASLBO;
import tds.accommodationstatistics.domain.AbstractAccommodation;
import tds.accommodationstatistics.domain.AccommodationVideoASL;
import tds.accommodationstatistics.domain.StatisticHeader;
import tds.accommodationstatistics.repository.AccommodationsRepository;
import tds.accommodationstatistics.util.StatusMessageUtil;


/**
@author Ernesto De La Luz Martinez
*/
@Service
public class StatisticVideoASLServiceImpl implements StatisticsService {
	private final Logger logger = LoggerFactory.getLogger (StatisticVideoASLServiceImpl.class);
	@Autowired
	private StatisticHeaderService headerService;
	
	@Autowired
	@Qualifier("accommodationVideoASLRepositoryImpl")
	private AccommodationsRepository aslRepository;
	
	
	@Override
	public <T extends AbstractAccommodation> ResponseData<String> persistStatistic(T accommodation) {
				
		logger.info("persist: "+aslRepository);
		try {
			
			StatisticHeader header = headerService.findOrCreateHeader(accommodation.getHeader());
			accommodation.setHeader(header);			
			aslRepository.addAccommodation(accommodation);
			logger.debug("processsing");
			return StatusMessageUtil.getSuccessMessage();	
		} catch (ReturnStatusException e) {
			// TODO Auto-generated catch block
			return StatusMessageUtil.getErrorMessage(e.getMessage());
		}
		
		
	}


	 


	 
}
