package tds.accommodationstatistics.domain;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
 

/**
 * @author Ernesto De La Luz Martinez
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class GlossaryTerms {
	private String item;//question id
	private String term;//term selected
	private Integer invokeCounter;
	private Date whenInvoked;
	private Integer audioPlayCounter;
	
	

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return super.toString();
	}
	 

	 


	public String getItem() {
		return item;
	}


	public void setItem(String item) {
		this.item = item;
	}


	public String getTerm() {
		return term;
	}


	public void setTerm(String term) {
		this.term = term;
	}


	public Integer getInvokeCounter() {
		return invokeCounter;
	}


	public void setInvokeCounter(Integer invokeCounter) {
		this.invokeCounter = invokeCounter;
	}


	public Date getWhenInvoked() {
		return whenInvoked;
	}


	public void setWhenInvoked(Date whenInvoked) {
		this.whenInvoked = whenInvoked;
	}

	public Integer getAudioPlayCounter() {
		return audioPlayCounter;
	}

	public void setAudioPlayCounter(Integer audioPlayCounter) {
		this.audioPlayCounter = audioPlayCounter;
	}

}
