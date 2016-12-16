package tds.accommodationstatistics.service;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import TDS.Shared.Exceptions.ReturnStatusException;
import tds.accommodationstatistics.domain.StatisticHeader;
import tds.accommodationstatistics.repository.StatisticHeaderRepository;

/**
@author Ernesto De La Luz Martinez
*/
@Service
public class StatisticHeaderServiceImpl implements StatisticHeaderService {

	@Autowired
	private StatisticHeaderRepository headerRepository; 
	
	@Override
	public StatisticHeader findOrCreateHeader(StatisticHeader header) throws ReturnStatusException {
				
		StatisticHeader res = headerRepository.findByTestKey(header);
		
		if(res == null){			
			 
			res = headerRepository.addHeader(header);
		}
				 
		return res;
	}

}
