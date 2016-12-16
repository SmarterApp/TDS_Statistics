package tds.accommodationstatistics.bo;

import tds.accommodationstatistics.domain.StatisticHeader;
import tds.accommodationstatistics.exceptions.ValidationException;

/**
 * @author Ernesto De La Luz Martinez
 */
public class StatisticHeaderBO {
	private StatisticHeaderBO() {
	}
	
	public static void validateFields(StatisticHeader header)throws ValidationException{
		String message = null;
		if(header.getTestKey()== null ||  header.getTestKey()==0){
			message = "Test Key is missing";
		}
		 
		if(header.getDate() ==null){
			if(message == null){
				message = "Date is missing";	
			}else{
				message += ", Date is missing";
			}
			
		}
		if(message != null){
			throw new ValidationException(message);
		}		
	}
}
