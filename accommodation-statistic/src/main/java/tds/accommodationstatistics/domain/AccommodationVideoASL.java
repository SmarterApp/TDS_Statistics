package tds.accommodationstatistics.domain;

import java.util.Date;
import java.util.UUID;

/**
 * @author Ernesto De La Luz Martinez
 */
public class AccommodationVideoASL extends AbstractAccommodation{
		
	private String fileName;
	private Boolean replayed;
	private Boolean seeked;
	private Float timeToCompletion;
	private Date whenPlayed;

	@Override
	public String toString() {
		return super.toString() + ", File Name: " + fileName + ", Replayed: " + replayed + ", Seeked: " + seeked
				+ ", Time To Completion: " + timeToCompletion;
	}

 

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public Boolean getReplayed() {
		return replayed;
	}

	public void setReplayed(Boolean replayed) {
		this.replayed = replayed;
	}

	public Boolean getSeeked() {
		return seeked;
	}

	public void setSeeked(Boolean seeked) {
		this.seeked = seeked;
	}

	public Float getTimeToCompletion() {
		return timeToCompletion;
	}

	public void setTimeToCompletion(Float timeToCompletion) {
		this.timeToCompletion = timeToCompletion;
	}

	public Date getWhenPlayed() {
		return whenPlayed;
	}

	public void setWhenPlayed(Date whenPlayed) {
		this.whenPlayed = whenPlayed;
	}

	
 

}
