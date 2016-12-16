package tds.accommodationstatistics.domain;

import java.util.Date;
import java.util.UUID;
 

/**
@author Ernesto De La Luz Martinez

This class is created, because it is posible that more properties should be added to the header of the statistics
*/
public class StatisticHeader {
	private UUID id;
	private Long testKey;
	private Date date;
	
	
	@Override
	public String toString() {
		return  "Date: "+ date;
	}
	 
	
	public UUID getId() {
		return id;
	}
	public void setId(UUID id) {
		this.id = id;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}


	public Long getTestKey() {
		return testKey;
	}


	public void setTestKey(Long testKey) {
		this.testKey = testKey;
	}
	
	
}
