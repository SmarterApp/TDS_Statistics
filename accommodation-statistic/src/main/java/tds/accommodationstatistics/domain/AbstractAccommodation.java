package tds.accommodationstatistics.domain;

import java.util.UUID;

/**
 * @author Ernesto De La Luz Martinez
 */

public class AbstractAccommodation {
	private UUID id;

	private StatisticHeader header;

	public StatisticHeader getHeader() {
		return header;
	}

	public void setHeader(StatisticHeader header) {
		this.header = header;
	}

	public UUID getId() {
		return id;
	}

	public void setId(UUID id) {
		this.id = id;
	}

}
