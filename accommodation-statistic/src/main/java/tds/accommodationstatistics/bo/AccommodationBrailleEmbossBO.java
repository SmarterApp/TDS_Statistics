package tds.accommodationstatistics.bo;

import tds.accommodationstatistics.domain.AccommodationBrailleEmboss;
import tds.accommodationstatistics.domain.AccommodationGlossary;
import tds.accommodationstatistics.domain.AccommodationVideoASL;
import tds.accommodationstatistics.exceptions.ValidationException;

/**
 * @author Ernesto De La Luz Martinez
 */
public class AccommodationBrailleEmbossBO {
	private AccommodationBrailleEmbossBO() {
		// TODO Auto-generated constructor stub
	}
	
	public static void validateFields(AccommodationBrailleEmboss accommodation) throws ValidationException{
		String message = null;
		if(accommodation.getEmbossedFileName()==null || accommodation.getEmbossedFileName().trim().length()==0 ){
			message = "'embossedFileName' is missing";
		}
		if(accommodation.getWhenEmbossed()==null  ){
			if(message == null){
				message = "'whenEmbossed' is missing";	
			}else{
				message += ", 'whenEmbossed' is missing";
			}
			
		}
		 
		if(message != null){
			throw new ValidationException(message);
		}
	}
}
