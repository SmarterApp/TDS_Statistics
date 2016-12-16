package tds.accommodationstatistics.bo;

import tds.accommodationstatistics.domain.AccommodationGlossary;
import tds.accommodationstatistics.domain.AccommodationVideoASL;
import tds.accommodationstatistics.domain.GlossaryTerms;
import tds.accommodationstatistics.exceptions.ValidationException;

/**
 * @author Ernesto De La Luz Martinez
 */
public class AccommodationGlossaryBO {
	private AccommodationGlossaryBO() {
		// TODO Auto-generated constructor stub
	}

	public static void validateFields(AccommodationGlossary accommodation) throws ValidationException {
		String message = null;
		if (accommodation.getTypeGlossary() == null || accommodation.getTypeGlossary().trim().length() == 0) {
			message = "'typeGlossary' is missing";
		}
		if (accommodation.getGlossaries() == null || accommodation.getGlossaries().size() == 0) {
			if (message == null) {
				message = "Glossary Terms are missing";
			} else {
				message += ", Glossary Terms are missing";
			}

		} else {

			String msgGlossaries = null;
			for (GlossaryTerms gt : accommodation.getGlossaries()) {

				String msgTerm = null;
				if (gt.getTerm() == null) {
					msgTerm = "'Term' is missing";
				}
				if (gt.getItem() == null) {
					if (msgTerm == null) {
						msgTerm = "'Item' is missing";
					} else {
						msgTerm += ", 'Item' is missing";
					}

				}
				if (gt.getWhenInvoked() == null) {
					if (msgTerm == null) {
						msgTerm = "'whenInvoked' is missing";
					} else {
						msgTerm += ", 'whenInvoked' is missing";
					}

				}

				if (gt.getInvokeCounter() == null || gt.getInvokeCounter() == 0) {
					if (msgTerm == null) {
						msgTerm = "'invokeCounter' cannot be null or zero";
					} else {
						msgTerm += ", 'invokeCounter' cannot be null or zero";
					}

				}
				if (gt.getAudioPlayCounter() == null) {
					if (msgTerm == null) {
						msgTerm = "'audioPlayCounter' cannot be null or zero";
					} else {
						msgTerm += ", 'audioPlayCounter' cannot be null or zero";
					}

				}

				if (msgTerm != null) {
					if (msgGlossaries == null) {
						msgGlossaries = "Glossary Term: "+ "'"+(gt.getTerm()!=null?gt.getTerm()+ "'":"") +" [" + msgTerm + "]";
					} else {
						msgGlossaries += ", Glossary Term: "+ "'"+(gt.getTerm()!=null?gt.getTerm()+ "'":"") +" [" + msgTerm + "]";
					}
				}

			}
			
			if(msgGlossaries!= null){
				if (message == null) {
					message = "The next 'Glossary Terms' contain missing data: "+ msgGlossaries;
				} else {
					message += ", The next 'Glossary Terms' contain missing data: " + msgGlossaries;
				}
			}
		}

		if (message != null) {
			throw new ValidationException(message);
		}
	}
}
