 
/**
 * @author Ernesto De La Luz Martinez
 * 
 * This function manages all the accommodations
 */
'use strict'; 

TDS.StatisticsManager = TDS.StatisticsManager || {};
(function(StatisticsManager) {
	
	
	// Properties
	/**
	 * contains the list of accommodations added each time a page is loaded and
	 * a statistic is tracked
	 */
	var accommodationList = [];
	
	var API = {};
	
	var xhr = new TDS.StatisticsManager.Xhr();
	
	
	
	
	/**
	 * this method verify if there are some accommodations in the list, then
	 * process all the accommodations and sent the accommodations to the RESTful
	 * service then clear the list
	 */
	API.processAccommodations = function() {
		// verify if the list contains values, then means that the
		// accommodations must be sent to the RESTful service
		if (accommodationList.length > 0) {
			// sent accommodations
			console.log("Sending accommodation statistics");
			var statObj = null;
			for ( var t in accommodationList) {
				//get accommodation
				var a = accommodationList[t];
				//serialize				
				statObj = a.formatObject();
				console.log(" t " + statObj);
				console.log(" JSON " + JSON.stringify(statObj));
				
				//call statistics RESTful service
				if (a.type == TYPE_ACCOMMODATION.ASL) {
					API.callService('asl/persist', statObj );
				}else if (a.type == TYPE_ACCOMMODATION.GLOSSARY) {
					API.callService('glossary/persist', statObj );					
				}else if (a.type == TYPE_ACCOMMODATION.BRAILLE) {
					API.callService('braille/embossed/persist', statObj );					
				}  								
			}
			// clear list			
			accommodationList = [];
		}

	}
	
	API.callService = function (operation, statistics) {
		var testee = TDS.Student.Storage.getTestee();
	    var session = TDS.Student.Storage.getTestSession();
		var data = {
				testKey: testee.key,
	            testToken: testee.token,
	            sessionKey: session.key,
	            statistics: statistics
	        };
		console.log(data);
		 var customConfig = {
		            plainJson: true//allows to convert into json
		        };
		 YAHOO.util.Connect.setDefaultPostHeader(false); // allow custom 'Content-Type'
		 YAHOO.util.Connect.initHeader('Content-Type', 'application/json');		 
		 xhr.sendPromise(operation, data, '', customConfig );
		 YAHOO.util.Connect.setDefaultPostHeader(true);    
         
    };
	
	/**
	 * this method get accommodation objects from the list
	 */
	API.getAccommodation = function(type) {		
		// iterates over the list, if the accommodation is found then is
		// returned
		for ( var t in accommodationList) {
			var a = accommodationList[t];
			console.log(" t " + a);
			if (type == a.getType()) {
				return a;
			}
		}
		return null;
	};

	/**
	 * This method creates a new accommodation, but first verify if the accommodation is not in the list
	 */
	API.initializeAccommodation = function(type) {		
		//verify if the accommodation exist, if not then an accommodation is created
		var acc = API.getAccommodation(type);

		if(acc==null){
			if (type == TYPE_ACCOMMODATION.ASL) {
				acc = new AccommodationASL();
				accommodationList.push(acc);
			}else if (type == TYPE_ACCOMMODATION.GLOSSARY) {
				acc = new AccommodationGlossary();
				accommodationList.push(acc); 
			}else if (type == TYPE_ACCOMMODATION.BRAILLE) {
				acc = new AccommodationBraille();
				accommodationList.push(acc); 
			}  
			
			console.log("created new accommodation ***** " + type);
		}			
		return acc;
	};

	/**
	 * Extracts the filename of a string "search" like
	 * '?path=qfIMxtDkFNeI2EiYXSpBe2Y0W9RJ&file=passage_3716_ASL_STEM.MP4'
	 */
	API.extractFileName = function(search){
		var initPos = search.indexOf("&file=");
	    var rawValue= search.substring(initPos, search.length);
	    var fileName = rawValue.substring(6, rawValue.length);
	    return fileName;
	}
	
	
	StatisticsManager.API = API;
	
})(TDS.StatisticsManager);
