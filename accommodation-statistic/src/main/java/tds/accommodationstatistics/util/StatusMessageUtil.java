package tds.accommodationstatistics.util;

import AIR.Common.Web.TDSReplyCode;
import AIR.Common.data.ResponseData;

/**
@author Ernesto De La Luz Martinez
*/
public class StatusMessageUtil {

	private StatusMessageUtil() {
	}
	
	/**
	 * Returns a standar success message
	 * @return
	 */
	public static ResponseData<String> getSuccessMessage(){
		return new ResponseData<String>(TDSReplyCode.OK.getCode(), "Successful", "Operation executed successfully");	
	}
	/**
	 * Returns a sucess message with a custom message
	 * @param message
	 * @return
	 */
	public static ResponseData<String> getSuccessMessage(String message){
		return new ResponseData<String>(TDSReplyCode.OK.getCode(), "Successful", message);	
	}
	
	/**
	 * Returns an error with a custom message
	 * @param message
	 * @return
	 */
	public static ResponseData<String> getErrorMessage(String message){
		return new ResponseData<String>(TDSReplyCode.Error.getCode(), "Error", message);
	}
	
	 
	
	
}
