package test.tds.accommodationstatistics.handler;

import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.equalTo;
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
import tds.accommodationstatistics.domain.AccommodationGlossary;
import tds.accommodationstatistics.handler.AccommodationStatisticsHandler;
import tds.accommodationstatistics.service.StatiscticGlossaryServiceImpl;
import tds.accommodationstatistics.service.StatisticsService;
import tds.accommodationstatistics.util.StatusMessageUtil;
 
/**
@author Ernesto De La Luz Martinez
*/
 
public class AccommodationStatisticsHandlerGlossaryTest {
	private final Logger logger = LoggerFactory.getLogger(AccommodationStatisticsHandlerGlossaryTest.class);
	
	 
	private StatisticsService glossaryService;	
	private AccommodationStatisticsHandler accommodationController;
	 
	
	@Before
	public void setUp() throws Exception {
		glossaryService = mock(StatiscticGlossaryServiceImpl.class);
		
		accommodationController = new AccommodationStatisticsHandler();
 
		ReflectionTestUtils.setField(accommodationController, "glossaryService", glossaryService, StatisticsService.class);					
	}

	/**
	 * Test the content of each object in glossaries
	 */
	@Ignore
	@Test
	public void testASLValidationGlossaryTerms() {
		logger.info("testing");
		String json = " { "
				+ " 	\"testKey\":-683,"
				+ " 	\"testToken\":\"026bae47-3c26-49c8-bd4d-214b921ce960\","
				+ " 	\"sessionKey\":\"c238d726-fc39-4ed4-8af3-fe47b9c1e6dd\","
				+ " 	\"statistics\":{"
				+ " 		\"header\" : {"
				+ " 			\"testKey\":-683," 
				+ " 			\"date\":\"2016-07-27T22:56:52.423Z\""
				+ " 			},		"
				+ " 		\"typeGlossary\":\"TDS_WL_ESNGlossary\","
				+ " 		\"glossaries\": ["
				+ " 				{"
				+ " 					\"isOpened\":true,"
							+ " 		\"item\":\"187-1732-3\","
							+ " 		\"term\":\"On the other hand\","
							+ " 		\"invokeCounter\":2,"
							+ " 		\"audioPlayCounter\":3"
							//+ " 		\"whenInvoked\":\"2016-09-28T20:04:09.133Z\""
							+ " 	},"
							+ " 	{"
							+ " 		\"isOpened\":false,"
							//+ " 		\"item\":\"187-1732-1\","
							+ " 		\"term\":\"dialogue\","
							+ " 		\"invokeCounter\":1,"
							//+ " 		\"audioPlayCounter\":2,"
							+ " 		\"whenInvoked\":\"2016-09-28T20:04:22.466Z\""
				+ " 				}"							
				+ " 			]"
				
				+ " 		}"
				+ " }";
		
		 
			when(glossaryService.persistStatistic((AccommodationGlossary) any()
						)
					).thenReturn(StatusMessageUtil.getSuccessMessage());
		 
		try {
			ResultActions result = MockMvcBuilders.standaloneSetup(accommodationController).build()			
					.perform(post("/Statistics/glossary/persist")
							.contentType(MediaType.APPLICATION_JSON)
							.content(json)
					)
					.andExpect(status().isOk())
					.andExpect(content().contentType("application/json"))
					.andExpect(jsonPath("$.replyCode", is(1)))										
					.andExpect(jsonPath("$.data").value(containsString("The next 'Glossary Terms'")))

	                ;
			 			 
			logger.debug("ending");
		} catch (Exception e1) {
			e1.printStackTrace();
		}
		 
		logger.info("end testing");
	}
	/**
	 * Test GlossayTerms, not to be empty
	 */
	@Ignore
	@Test
	public void testASLValidationGlossaryTermsArray() {
		logger.info("testing");
		String json = " { "
				+ " 	\"testKey\":-683,"
				+ " 	\"testToken\":\"026bae47-3c26-49c8-bd4d-214b921ce960\","
				+ " 	\"sessionKey\":\"c238d726-fc39-4ed4-8af3-fe47b9c1e6dd\","
				+ " 	\"statistics\":{"
				+ " 		\"header\" : {"
				+ " 			\"testKey\":-683," 
				+ " 			\"date\":\"2016-07-27T22:56:52.423Z\""
				+ " 			},		"
				+ " 		\"typeGlossary\":\"TDS_WL_ESNGlossary\","
				+ " 		\"glossaries\": ["				
				+ " 			]"
				
				+ " 		}"
				+ " }";
		
		 
			when(glossaryService.persistStatistic((AccommodationGlossary) any()
						)
					).thenReturn(StatusMessageUtil.getSuccessMessage());
		 
		
		try {
			ResultActions result = MockMvcBuilders.standaloneSetup(accommodationController).build()			
					.perform(post("/Statistics/glossary/persist")
							.contentType(MediaType.APPLICATION_JSON)
							.content(json)
					)
					.andExpect(status().isOk())
					.andExpect(content().contentType("application/json"))
					.andExpect(jsonPath("$.replyCode", is(1)))										
					.andExpect(jsonPath("$.data").value(containsString("Glossary Terms")))

	                ;
			 			 
			logger.debug("ending");
		} catch (Exception e1) {
			e1.printStackTrace();
		}
		 
		logger.info("end testing");
	}
	
	/**
	 * test the property "typeGlossary"
	 */
	@Ignore
	@Test
	public void testASLValidationTypeGlossary() {
		logger.info("testing");
		String json = " { "
				+ " 	\"testKey\":-683,"
				+ " 	\"testToken\":\"026bae47-3c26-49c8-bd4d-214b921ce960\","
				+ " 	\"sessionKey\":\"c238d726-fc39-4ed4-8af3-fe47b9c1e6dd\","
				+ " 	\"statistics\":{"
				+ " 		\"header\" : {"
				+ " 			\"testKey\":-683," 
				+ " 			\"date\":\"2016-07-27T22:56:52.423Z\""
				+ " 			},		"
				//+ " 		\"typeGlossary\":\"TDS_WL_ESNGlossary\","
				+ " 		\"glossaries\":["
				+ " 				{"
				+ " 					\"isOpened\":true,"
							+ " 		\"item\":\"187-1732-3\","
							+ " 		\"term\":\"On the other hand\","
							+ " 		\"invokeCounter\":2,"
							+ " 		\"audioPlayCounter\":3,"
							+ " 		\"whenInvoked\":\"2016-09-28T20:04:09.133Z\""
							+ " 	},"
							+ " 	{"
							+ " 		\"isOpened\":false,"
							+ " 		\"item\":\"187-1732-1\","
							+ " 		\"term\":\"dialogue\","
							+ " 		\"invokeCounter\":1,"
							+ " 		\"audioPlayCounter\":2,"
							+ " 		\"whenInvoked\":\"2016-09-28T20:04:22.466Z\""
				+ " 				}"
				+ " 			]"
				+ " 		}"
				+ " }";
		
		 
			when(glossaryService.persistStatistic((AccommodationGlossary) any()
						)
					).thenReturn(StatusMessageUtil.getSuccessMessage());
		 
		
		try {
			ResultActions result = MockMvcBuilders.standaloneSetup(accommodationController).build()			
					.perform(post("/Statistics/glossary/persist")
							.contentType(MediaType.APPLICATION_JSON)
							.content(json)
					)
					.andExpect(status().isOk())
					.andExpect(content().contentType("application/json"))
					.andExpect(jsonPath("$.replyCode", is(1)))										
					.andExpect(jsonPath("$.data").value(containsString("'typeGlossary'")))

	                ;
			 			 
			logger.debug("ending");
		} catch (Exception e1) {
			e1.printStackTrace();
		}
		 
		logger.info("end testing");
	}
	
 
	/**
	 * test the header values
	 * testKey
	 */
	@Ignore 
	@Test
	public void testASLValidationHeader() {
		logger.info("testing");
		String json = " { "
				+ " 	\"testKey\":-683,"
				+ " 	\"testToken\":\"026bae47-3c26-49c8-bd4d-214b921ce960\","
				+ " 	\"sessionKey\":\"c238d726-fc39-4ed4-8af3-fe47b9c1e6dd\","
				+ " 	\"statistics\":{"
				+ " 		\"header\" : {"
				//+ " 			\"testKey\":-683," 
				+ " 			\"date\":\"2016-07-27T22:56:52.423Z\""
				+ " 			},		"
				+ " 		\"typeGlossary\":\"TDS_WL_ESNGlossary\","
				+ " 		\"glossaries\":["
				+ " 				{"
				+ " 					\"isOpened\":true,"
							+ " 		\"item\":\"187-1732-3\","
							+ " 		\"term\":\"On the other hand\","
							+ " 		\"invokeCounter\":2,"
							+ " 		\"audioPlayCounter\":3,"
							+ " 		\"whenInvoked\":\"2016-09-28T20:04:09.133Z\""
							+ " 	},"
							+ " 	{"
							+ " 		\"isOpened\":false,"
							+ " 		\"item\":\"187-1732-1\","
							+ " 		\"term\":\"dialogue\","
							+ " 		\"invokeCounter\":1,"
							+ " 		\"audioPlayCounter\":2,"
							+ " 		\"whenInvoked\":\"2016-09-28T20:04:22.466Z\""
				+ " 				}"
				+ " 			]"
				+ " 		}"
				+ " }";
		
		 
			when(glossaryService.persistStatistic((AccommodationGlossary) any()
						)
					).thenReturn(StatusMessageUtil.getSuccessMessage());
		 
		try {
			ResultActions result = MockMvcBuilders.standaloneSetup(accommodationController).build()			
					.perform(post("/Statistics/glossary/persist")
							.contentType(MediaType.APPLICATION_JSON)
							.content(json)
					)
					.andExpect(status().isOk())
					.andExpect(content().contentType("application/json"))
					.andExpect(jsonPath("$.replyCode", is(1)))										
					.andExpect(jsonPath("$.data").value(equalTo("Test Key is missing")))

	                ;
			 			 
			logger.debug("ending");
		} catch (Exception e1) {
			e1.printStackTrace();
		}
		 
		logger.info("end testing");
	}
	
	
	/**
	 * test accommodation Glossary with all items
	 */
	//@Ignore
	@Test
	public void testGlossary() {
		logger.info("testing");
		String json = " { "
				+ " 	\"testKey\":-683,"
				+ " 	\"testToken\":\"026bae47-3c26-49c8-bd4d-214b921ce960\","
				+ " 	\"sessionKey\":\"c238d726-fc39-4ed4-8af3-fe47b9c1e6dd\","
				+ " 	\"statistics\":{"
				+ " 		\"header\" : {"
				+ " 			\"testKey\":-683,"
				+ " 			\"date\":\"2016-07-27T22:56:52.423Z\""
				+ " 			},		"
				+ " 		\"typeGlossary\":\"TDS_WL_ESNGlossary\","
				+ " 		\"glossaries\":["
				+ " 				{"
				+ " 					\"isOpened\":true,"
							+ " 		\"item\":\"187-1732-3\","
							+ " 		\"term\":\"On the other hand\","
							+ " 		\"invokeCounter\":2,"
							+ " 		\"audioPlayCounter\":3,"
							+ " 		\"whenInvoked\":\"2016-09-28T20:04:09.133Z\""
							+ " 	},"
							+ " 	{"
							+ " 		\"isOpened\":false,"
							+ " 		\"item\":\"187-1732-1\","
							+ " 		\"term\":\"dialogue\","
							+ " 		\"invokeCounter\":1,"
							+ " 		\"audioPlayCounter\":2,"
							+ " 		\"whenInvoked\":\"2016-09-28T20:04:22.466Z\""
				+ " 				}"
				+ " 			]"
				+ " 		}"
				+ " }";
		
		 
			when(glossaryService.persistStatistic((AccommodationGlossary) any()
						)
					).thenReturn(StatusMessageUtil.getSuccessMessage());
		 
		
		try {
			ResultActions result = MockMvcBuilders.standaloneSetup(accommodationController).build()			
					.perform(post("/Statistics/glossary/persist")
							.contentType(MediaType.APPLICATION_JSON)
							.content(json)
					)
					.andExpect(status().isOk())
					.andExpect(content().contentType("application/json"))
					.andExpect(jsonPath("$.replyCode", is(0)))
	                ;
						 
			logger.debug("ending");
		} catch (Exception e1) {
			e1.printStackTrace();
		}
		 
		logger.info("end testing");
	}
	
}
