package tds.accommodationstatistics.domain;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * @author Ernesto De La Luz Martinez
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class AccommodationGlossary extends AbstractAccommodation {

	private String typeGlossary;
	private List<GlossaryTerms> glossaries;

	public String getTypeGlossary() {
		return typeGlossary;
	}

	public void setTypeGlossary(String typeGlossary) {
		this.typeGlossary = typeGlossary;
	}

	public List<GlossaryTerms> getGlossaries() {
		return glossaries;
	}

	public void setGlossaries(List<GlossaryTerms> glossaries) {
		this.glossaries = glossaries;
	}

}
