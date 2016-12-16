package tds.accommodationstatistics.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

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
public class StatiscticGlossaryServiceImpl implements StatisticsService {
	private final Logger logger = LoggerFactory.getLogger (StatiscticGlossaryServiceImpl.class);
	@Autowired
	private StatisticHeaderService headerService;
	
	@Autowired	
	private AccommodationsRepository accommodationGlossaryRepositoryImpl;
	
	
	@Override
	public <T extends AbstractAccommodation> ResponseData<String> persistStatistic(T accommodation) {
		logger.info("persist: "+accommodationGlossaryRepositoryImpl);
		 			
		 
		try {			
			StatisticHeader header = headerService.findOrCreateHeader(accommodation.getHeader());
			accommodation.setHeader(header);			
			accommodationGlossaryRepositoryImpl.addAccommodation(accommodation);
			logger.debug("processsing");
			return StatusMessageUtil.getSuccessMessage();	
		} catch (ReturnStatusException e) {			
			return StatusMessageUtil.getErrorMessage(e.getMessage());
			
		}
		 
	}

}
