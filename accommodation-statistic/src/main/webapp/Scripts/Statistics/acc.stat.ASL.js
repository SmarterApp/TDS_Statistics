
/**
 * @author Ernesto De La Luz Martinez
 * 
 * This is a subclass that contains the data for the Accommodation ASL
 */

'use strict';
  
	var AccommodationASL = function() {
		this.fileName=null;
		this.isCompleted=false;
		this.isReplayed = false;
		this.isSeeked = false;
		this.isVideoClosed = false; 
		this.timeToCompletion=0;
		AccommodationASL.superclass.constructor.call(this, TYPE_ACCOMMODATION.ASL, new Date());
		
		//setters, getters
		this.setFileName = function(name){
			this.fileName = name;
			//each time the file name is setted then restart isVideoClosed
			this.isVideoClosed = false;
		}				
		this.setSeeked= function(){
			this.isSeeked = true;
		}
		
		this.setVideoClosed = function(){
			this.isVideoClosed = true;
		}
		
		
		//functions		
		this.setReplayed = function(){
			if(this.isCompleted == true){
				this.isReplayed = true;	
			}			
		}
		
		this.applyCompletion = function(duration, currentTime){
			if(this.isCompleted ==false){
				if(this.isVideoClosed==false){
					this.isCompleted = true;
					this.timeToCompletion = 0;
				}else{
					this.timeToCompletion = duration-currentTime;
				}	
			}
						
		}
		
		this.formatObject = function(){
			var testee = TDS.Student.Storage.getTestee();
			var obj = {
					"header":{
						"testKey":testee.key,
						"date":this.date.toISOString()
					},					
					"fileName": this.fileName,
					"timeToCompletion": this.timeToCompletion,
					"replayed" : this.isReplayed,
					"seeked" : this.isSeeked,
					"whenPlayed" : this.date.toISOString()
			};
			return obj;
		}
		

	}
	
	YAHOO.lang.extend(AccommodationASL, AccommodationStatistic);

 
