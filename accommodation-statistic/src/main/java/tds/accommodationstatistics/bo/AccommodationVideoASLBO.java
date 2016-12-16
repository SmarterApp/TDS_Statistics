package tds.accommodationstatistics.bo;

import tds.accommodationstatistics.domain.AccommodationVideoASL;
import tds.accommodationstatistics.exceptions.ValidationException;

/**
@author Ernesto De La Luz Martinez
*/
public class AccommodationVideoASLBO {
	
	private AccommodationVideoASLBO() {
	}

	public static void validateFields(AccommodationVideoASL accommodation) throws ValidationException{
		String message = null;
		if(accommodation.getFileName()==null || accommodation.getFileName().trim().length()==0 ){
			message = "'fileName' is missing";
		}
		if(accommodation.getTimeToCompletion()==null  ){
			if(message == null){
				message = "'timeToCompletion' is missing";	
			}else{
				message += ", 'timeToCompletion' is missing";
			}
			
		}
		if(accommodation.getReplayed()==null  ){
			if(message == null){
				message = "'replayed'  is missing";	
			}else{
				message += ", 'replayed'  is missing";
			}
			
		}
		if(accommodation.getSeeked()==null  ){
			if(message == null){
				message = "'seeked'  is missing";	
			}else{
				message += ", 'seeked'  is missing";
			}
			
		}
		
		if(accommodation.getWhenPlayed()==null  ){
			if(message == null){
				message = "'whenPlayed'  is missing";	
			}else{
				message += ", 'whenPlayed'  is missing";
			}
			
		}
		 
		if(message != null){
			throw new ValidationException(message);
		}
	}
}
