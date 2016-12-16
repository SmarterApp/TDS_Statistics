package tds.accommodationstatistics.exceptions;

import TDS.Shared.Data.ReturnStatus;

/**
@author Ernesto De La Luz Martinez
*/
public class ValidationException extends Exception{

	private static final long serialVersionUID = 1L;
	
	public ValidationException (String message) {
	    super (message);	    
	  }

}
