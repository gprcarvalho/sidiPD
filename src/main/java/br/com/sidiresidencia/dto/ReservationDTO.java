
package br.com.sidiresidencia.dto;

import java.io.Serializable;
import java.time.LocalDate;

import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonFormat;

import br.com.sidiresidencia.model.Reservation;
import br.com.sidiresidencia.model.Station;
import br.com.sidiresidencia.model.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ReservationDTO implements Serializable {
	private static final long serialVersionUID = 1L;

	private Long id;
	@NotNull(message = "Required field")
	@JsonFormat(pattern = "dd/MM/yyyy")
	private LocalDate dateReservation;
	private User createdBy;
	@NotNull
	private Station station;
	private User createdFor;

	public ReservationDTO(Reservation reservation) {

		id = reservation.getId();
		dateReservation = reservation.getDateReservation();
		createdBy = reservation.getCreatedBy();
		station = reservation.getStation();
		createdFor = reservation.getCreatedFor();

	}

}
