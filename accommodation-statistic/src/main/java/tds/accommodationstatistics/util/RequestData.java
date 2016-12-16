package tds.accommodationstatistics.util;

import tds.accommodationstatistics.domain.AbstractAccommodation;

/**
@author Ernesto De La Luz Martinez
*/
public class RequestData<T extends AbstractAccommodation> {
	private String testKey;
	private String testToken;
	private String sessionKey;
	private T statistics;
	
	@Override
	public String toString() {		
		return "testKey: " + testKey 
				+ ", testToken: " + testToken 
				+ ", sessionKey: " + sessionKey
				+ ", statistic: " + (statistics==null?"empty":statistics.toString());
	}
	
	public String getTestKey() {
		return testKey;
	}
	public void setTestKey(String testKey) {
		this.testKey = testKey;
	}
	 
	public T getStatistics() {
		return statistics;
	}
	public void setStatistics(T statistic) {
		this.statistics = statistic;
	}

	public String getTestToken() {
		return testToken;
	}

	public void setTestToken(String testToken) {
		this.testToken = testToken;
	}

	public String getSessionKey() {
		return sessionKey;
	}

	public void setSessionKey(String sessionKey) {
		this.sessionKey = sessionKey;
	}
	
	
	
}
