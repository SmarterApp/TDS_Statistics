package tds.accommodationstatistics.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import AIR.Common.data.ResponseData;
import TDS.Shared.Exceptions.ReturnStatusException;
import tds.accommodationstatistics.domain.AbstractAccommodation;
import tds.accommodationstatistics.domain.StatisticHeader;
import tds.accommodationstatistics.repository.AccommodationsRepository;
import tds.accommodationstatistics.util.StatusMessageUtil;

/**
@author Ernesto De La Luz Martinez
*/
@Service
public class StatiscticBrailleEmbossServiceImpl implements StatisticsService {

	private final Logger logger = LoggerFactory.getLogger (StatiscticBrailleEmbossServiceImpl.class);
	@Autowired
	private StatisticHeaderService headerService;
	
	@Autowired	
	private AccommodationsRepository accommodationBrailleEmbossRepositoryImpl;
	
	
	@Override
	public <T extends AbstractAccommodation> ResponseData<String> persistStatistic(T accommodation) {
		logger.info("persist: "+accommodationBrailleEmbossRepositoryImpl);
			
		 
		try {			
			StatisticHeader header = headerService.findOrCreateHeader(accommodation.getHeader());
			accommodation.setHeader(header);			
			accommodationBrailleEmbossRepositoryImpl.addAccommodation(accommodation);
			logger.debug("processsing");
			return StatusMessageUtil.getSuccessMessage();	
		} catch (ReturnStatusException e) {			
			return StatusMessageUtil.getErrorMessage(e.getMessage());
			
		}
		 
	}

}
