
/**
 * @author Ernesto De La Luz Martinez
 * 
 * This is a subclass that contains the data for the Accommodation ASL
 */

'use strict';
  
	var AccommodationBraille = function() {
		this.embossedFileName = null;
		this.whenEmbossed = new Date();
		
		AccommodationBraille.superclass.constructor.call(this, TYPE_ACCOMMODATION.BRAILLE, new Date());
		  
		
		
		//functions
		/**
		 * extracts the attachment from the array and only add the BRF type
		 */
		this.applyFileName = function(attachments, brailleSubType){
			if(this.embossedFileName == null){
				for ( var i in attachments) {
					var attachment = attachments[i];
					if(attachment.type.toUpperCase() == "BRF" && attachment.subType.toUpperCase() == brailleSubType.toUpperCase()){
						 this.embossedFileName =TDS.StatisticsManager.API.extractFileName(attachment.url);						
					}
				}	
			}			
		}
		 
		
		/**
		 * Marshall this object into JSON format
		 */
		this.formatObject = function(){
			var testee = TDS.Student.Storage.getTestee();
			var obj = {
					"header":{
						"testKey":testee.key,
						"date":this.date.toISOString()
					},					
					"embossedFileName": this.embossedFileName,
					"whenEmbossed" : this.whenEmbossed.toISOString()
			};
			return obj;
		}
		
	}
	
	YAHOO.lang.extend(AccommodationBraille, AccommodationStatistic);

 
