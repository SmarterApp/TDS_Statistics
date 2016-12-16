package test.tds.accommodationstatistics.general;

import static org.junit.Assert.*;

import java.util.UUID;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import tds.accommodationstatistics.util.StatisticsUtil;



/**
@author Ernesto De La Luz Martinez
*/
public class TestMethods {
	private final Logger logger = LoggerFactory.getLogger(TestMethods.class);
	@Test
	public void test() {
		String artIDStr ="2c70fa8fcc8d48adaca133e027bd1559";		
		 UUID id = StatisticsUtil.getUUID(artIDStr);
		logger.info(id.toString());
		
	}

}
