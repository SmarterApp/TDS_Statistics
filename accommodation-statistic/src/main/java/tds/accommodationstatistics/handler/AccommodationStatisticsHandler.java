package tds.accommodationstatistics.handler;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import AIR.Common.Web.TDSReplyCode;
import AIR.Common.data.ResponseData;
import TDS.Shared.Exceptions.ReturnStatusException;
import tds.accommodationstatistics.bo.AccommodationBrailleEmbossBO;
import tds.accommodationstatistics.bo.AccommodationGlossaryBO;
import tds.accommodationstatistics.bo.AccommodationVideoASLBO;
import tds.accommodationstatistics.bo.StatisticHeaderBO;
import tds.accommodationstatistics.domain.AccommodationBrailleEmboss;
import tds.accommodationstatistics.domain.AccommodationGlossary;
import tds.accommodationstatistics.domain.AccommodationVideoASL;
import tds.accommodationstatistics.domain.StatisticHeader;
import tds.accommodationstatistics.exceptions.ValidationException;
import tds.accommodationstatistics.service.StatisticsService;
import tds.accommodationstatistics.util.RequestData;
import tds.accommodationstatistics.util.StatusMessageUtil;
/**
@author Ernesto De La Luz Martinez
*/
@Controller
@RequestMapping(value="Statistics")
public class AccommodationStatisticsHandler {
	private final Logger logger = LoggerFactory.getLogger (AccommodationStatisticsHandler.class);
	
	@Autowired
	@Qualifier("statisticVideoASLServiceImpl")
	private StatisticsService aslService; 
	
	@Autowired
	@Qualifier("statiscticGlossaryServiceImpl")
	private StatisticsService glossaryService;
	
	@Autowired
	@Qualifier("statiscticBrailleEmbossServiceImpl")
	private StatisticsService brailleService;
	
	
	public AccommodationStatisticsHandler() {
		logger.debug("Creating restful service-");
	}
	
	@RequestMapping(value = "asl/persist" , method=RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseData<String> persistASL(@RequestBody RequestData<AccommodationVideoASL> requestData){	
		ResponseData<String> res = null;
		 
		
		try {
			StatisticHeaderBO.validateFields(requestData.getStatistics().getHeader());
			AccommodationVideoASLBO.validateFields(requestData.getStatistics());			
			res = aslService.persistStatistic(requestData.getStatistics());				 
		} catch (ValidationException e) {
			logger.debug(e.getMessage());
			res= StatusMessageUtil.getErrorMessage(e.getMessage());
		} 	
		return res;		 
	}
	
	@RequestMapping(value = "glossary/persist" , method=RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseData<String> persistGlossary(@RequestBody RequestData<AccommodationGlossary> requestData){	
		ResponseData<String> res = null;

		try {
			StatisticHeaderBO.validateFields(requestData.getStatistics().getHeader());
			AccommodationGlossaryBO.validateFields(requestData.getStatistics());			
			res = glossaryService.persistStatistic(requestData.getStatistics());			
		} catch (ValidationException e) {
			logger.debug(e.getMessage());
			res= StatusMessageUtil.getErrorMessage(e.getMessage());
		} 
		return res;		 					 
	}
	
	
	
	@RequestMapping(value = "braille/embossed/persist" , method=RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseData<String> persistBrailleEmbossed(@RequestBody RequestData<AccommodationBrailleEmboss> requestData){	
		ResponseData<String> res = null;
		 
		try {
			StatisticHeaderBO.validateFields(requestData.getStatistics().getHeader());
			AccommodationBrailleEmbossBO.validateFields(requestData.getStatistics());			
			res = brailleService.persistStatistic(requestData.getStatistics());			
		} catch (ValidationException e) {
			logger.debug(e.getMessage());
			res= StatusMessageUtil.getErrorMessage(e.getMessage());
		} 	
		return res;			 
	}
	
	
	
	@RequestMapping(value = "test", method=RequestMethod.GET, produces= MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseData<String> testService(){
		logger.debug("testing service 1");
		return StatusMessageUtil.getSuccessMessage();
	}

}