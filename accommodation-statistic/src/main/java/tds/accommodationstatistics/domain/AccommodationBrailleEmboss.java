package tds.accommodationstatistics.domain;

import java.util.Date;

/**
 * @author Ernesto De La Luz Martinez
 */
public class AccommodationBrailleEmboss extends AbstractAccommodation {
	private String embossedFileName;
	private Date whenEmbossed;
	
	public String getEmbossedFileName() {
		return embossedFileName;
	}
	public void setEmbossedFileName(String embossedFileName) {
		this.embossedFileName = embossedFileName;
	}
	public Date getWhenEmbossed() {
		return whenEmbossed;
	}
	public void setWhenEmbossed(Date whenEmbossed) {
		this.whenEmbossed = whenEmbossed;
	}
	
	
	
}
