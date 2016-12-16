package tds.accommodationstatistics.util;

import java.util.UUID;

/**
 * @author Ernesto De La Luz Martinez
 */
public class StatisticsUtil {
	private StatisticsUtil() {
		// TODO Auto-generated constructor stub
	}
	
	public static UUID getUUID(String stringUUID){		  
		 String value   =String.format("%s%s%s%s%s%s%s%s%s-%s%s%s%s-%s%s%s%s-%s%s%s%s-%s%s%s%s%s%s%s%s%s%s%s%s", stringUUID.split(""));
		 UUID newUUID =  UUID.fromString(value);
		 return newUUID;
	}
}
