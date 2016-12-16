package test.tds.accommodationstatistics.handler;

import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.is;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.test.util.ReflectionTestUtils;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import TDS.Shared.Exceptions.ReturnStatusException;
import tds.accommodationstatistics.domain.AccommodationBrailleEmboss;
import tds.accommodationstatistics.domain.AccommodationVideoASL;
import tds.accommodationstatistics.handler.AccommodationStatisticsHandler;
import tds.accommodationstatistics.service.StatisticsService;
import tds.accommodationstatistics.service.StatiscticBrailleEmbossServiceImpl;
import tds.accommodationstatistics.service.StatisticVideoASLServiceImpl;
import tds.accommodationstatistics.util.StatusMessageUtil;
 
/**
@author Ernesto De La Luz Martinez
*/
 
public class AccommodationStatisticsHandlerBrailleEmbossedTest {
	private final Logger logger = LoggerFactory.getLogger(AccommodationStatisticsHandlerBrailleEmbossedTest.class);
	
	 
	private StatisticsService brailleService;	
	private AccommodationStatisticsHandler accommodationController;
	 
	
	@Before
	public void setUp() throws Exception {
		brailleService = mock(StatiscticBrailleEmbossServiceImpl.class);
		
		accommodationController = new AccommodationStatisticsHandler();
 
		ReflectionTestUtils.setField(accommodationController, "brailleService", brailleService, StatisticsService.class);					
	}
	
	/**
	 * Test only that the server is receiving the json
	 */
	@Test
	public void testValidation() {
		logger.info("testing");
		String json = " { "
				+ " 	\"testKey\":-683,"
				+ " 	\"testToken\":\"026bae47-3c26-49c8-bd4d-214b921ce960\","
				+ " 	\"sessionKey\":\"c238d726-fc39-4ed4-8af3-fe47b9c1e6dd\","
				+ " 	\"statistics\":{"
				+ " 		\"header\" : {"
				+ " 			\"testKey\":-870,"
				+ " 			\"date\":\"2016-10-03T20:38:04.250Z\""
				+ " 			},		"
				//+ " 		\"embossedFileName\":\"passage_3709_enu_uncontracted.brf\","
				+ " 		\"whenEmbossed\":\"2016-10-03T20:38:04.250Z\""
				+ " 		}"
				+ " }";
		
		 
			when(brailleService.persistStatistic((AccommodationBrailleEmboss) any()
						)
					).thenReturn(StatusMessageUtil.getSuccessMessage());
			
		
		try {
			ResultActions result = MockMvcBuilders.standaloneSetup(accommodationController).build()			
					.perform(post("/Statistics/braille/embossed/persist")
							.contentType(MediaType.APPLICATION_JSON)
							.content(json)
					)
					.andExpect(status().isOk())
					.andExpect(content().contentType("application/json"))
					.andExpect(jsonPath("$.replyCode", is(1)))
					.andExpect(jsonPath("$.data").value(containsString("'embossedFileName'")))
	                ;
						 
			logger.debug("ending");
		} catch (Exception e1) {
			e1.printStackTrace();
		}
		 
		logger.info("end testing");
	}
	
	/**
	 * Test only that the server is receiving the json
	 */
	//@Ignore
	@Test
	public void testBase() {
		logger.info("testing");
		String json = " { "
				+ " 	\"testKey\":-683,"
				+ " 	\"testToken\":\"026bae47-3c26-49c8-bd4d-214b921ce960\","
				+ " 	\"sessionKey\":\"c238d726-fc39-4ed4-8af3-fe47b9c1e6dd\","
				+ " 	\"statistics\":{"
				+ " 		\"header\" : {"
				+ " 			\"testKey\":-870,"
				+ " 			\"date\":\"2016-10-03T20:38:04.250Z\""
				+ " 			},		"
				+ " 		\"embossedFileName\":\"passage_3709_enu_uncontracted.brf\","
				+ " 		\"whenEmbossed\":\"2016-10-03T20:38:04.250Z\""
				+ " 		}"
				+ " }";
		
		 
			when(brailleService.persistStatistic((AccommodationBrailleEmboss) any()
						)
					).thenReturn(StatusMessageUtil.getSuccessMessage());
			
		
		try {
			ResultActions result = MockMvcBuilders.standaloneSetup(accommodationController).build()			
					.perform(post("/Statistics/braille/embossed/persist")
							.contentType(MediaType.APPLICATION_JSON)
							.content(json)
					)
					.andExpect(status().isOk())
					.andExpect(content().contentType("application/json"))
					//.andExpect(jsonPath("$.replyCode", is(0)))
	                ;
						 
			logger.debug("ending");
		} catch (Exception e1) {
			e1.printStackTrace();
		}
		 
		logger.info("end testing");
	}
	
 
	
}
