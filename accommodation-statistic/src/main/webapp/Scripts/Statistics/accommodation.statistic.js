/**
 * @author Ernesto De La Luz Martinez
 * 
 */
'use strict';

/**
 * This is the Superclass for the Accommodations, it contains the headers that
 * are shared between Accommodations
 */
var AccommodationStatistic = function(type, date) {
	this.type = type;
	this.date = date;
	 

	this.getType = function() {
		return this.type;
	}
	this.getDate = function() {
		return this.date;
	}
 

};

/**
 * Enum of Accommodations
 */

var TYPE_ACCOMMODATION = {
	ASL : 'VIDEO_ASL',
	BRAILLE : 'BRAILLE',
	GLOSSARY : 'GLOSSARY'

};