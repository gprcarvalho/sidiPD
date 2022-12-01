package br.com.sidiresidencia.dto;

import java.io.Serializable;

import javax.validation.constraints.NotNull;

import br.com.sidiresidencia.model.Station;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class StationDTO implements Serializable {
	private static final long serialVersionUID = 1L;

	private Long id;
	@NotNull(message = "Required field")
	private String stationNumber;
	@NotNull(message = "Required field")
	private String groupStation;

	public StationDTO(Station station) {

		id = station.getId();
		stationNumber = station.getStationNumber();
		groupStation = station.getGroupStation();
	}

}
