/**
 * @author Ernesto De La Luz Martinez
 * 
 * This is a subclass that contains the data for the Accommodation Glossary
 * this object contains all the accommodations of glossary type
 */

'use strict';

var AccommodationGlossary = function() {
	this.typeGlossary = null;
	this.glossaryList = [];

	AccommodationGlossary.superclass.constructor.call(this, TYPE_ACCOMMODATION.GLOSSARY, new Date());

	 

	// functions
	 
	
	/**
	 * set the TypeGlossary property only when is null, it means the object was created before but not as an accommodation, 
	 * only to apply some values
	 */
	this.setTypeGlossary = function(typeGlossary) {	
		if(this.typeGlossary == null){
			this.typeGlossary = typeGlossary;
			this.date = new Date();
		}
	}
	/**
	 * get glossary according to the item and term
	 */
	this.getGlossary = function(item, term) {
		this.closeGlossaries();
		for ( var t in this.glossaryList) {
			var a = this.glossaryList[t];						
			if (item == a.item && term == a.term) {
				a.isOpened = true;
				return a;
			}
		}
		return null;
	}

	/**
	 * get glossary, assuming the glossary already exist and it was previously opened
	 */
	this.getOpenedGlossary = function() {		 
		for ( var t in this.glossaryList) {
			var a = this.glossaryList[t];		
			if (a.isOpened == true) {
				return a;
			}
		}
		return null;
	}
	
	/**
	 * close all the glossaries.
	 */
	this.closeGlossaries = function() {
		for ( var t in this.glossaryList) {
			var a = this.glossaryList[t];
			a.isOpened = false;		
		}
	}
	/**
	 * add a new glossary
	 */
	this.addGlossary = function(item, term) {
		var acc = this.getGlossary(item, term);

		if (acc == null) {
			acc = new GlossaryTerm(item, term);
			this.glossaryList.push(acc);
			console.log("created new accommodation glossary***** " + term);
		} else {
			// if exist increment counter
			acc.incrementCounter();
		}
		return acc;
	}

	this.formatObject = function() {
		var testee = TDS.Student.Storage.getTestee();
		var obj = {
			"header" : {
				"testKey" : testee.key,
				"date" : this.date.toISOString()
			},
			"typeGlossary" : this.typeGlossary,			
			"glossaries" : this.glossaryList
		};
		 
		return obj;
	}

}

YAHOO.lang.extend(AccommodationGlossary, AccommodationStatistic);


//this object corresponds to the accommodation object
var GlossaryTerm = function(item, term) {
	this.isOpened = true; //when this object is created is because is the one that is active, so it means is opened.
	this.item = item;
	this.term = term;
	this.invokeCounter = 1;
	this.audioPlayCounter = 0;
	//this date has to be with the format as ISO, because is not formatted before marshalling this object into JSON format
	this.whenInvoked = new Date().toISOString();

	// functions
	/**
	 * counts the times each glossary term is opened
	 */
	this.incrementCounter = function() {
		this.invokeCounter = this.invokeCounter + 1;
	}
	/**
	 * counts the times the audio for the glossary term is played
	 */
	this.incrementAudioPlayCounter = function(urlFile) {
		//verify if the audio contains  then increments the counter
		if(urlFile.includes("_glossary_ogg_m4a")){
			this.audioPlayCounter = this.audioPlayCounter + 1;	
		}
		
	}
	
	
	
};
