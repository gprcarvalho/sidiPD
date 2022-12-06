package br.com.sidiresidencia.model;

import java.io.Serializable;
import java.time.Instant;
import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;

import lombok.Data;

@Data
@Entity(name = "tb_reservation")
public class Reservation implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Column
	private LocalDate dateReservation;
	@Column(columnDefinition = "TIMESTAMP WITHOUT TIME ZONE")
	private Instant dateCreated;
	@Column(columnDefinition = "TIMESTAMP WITHOUT TIME ZONE")
	private Instant dateUpdated;
	@Column
	private boolean isCanceled;
	@ManyToOne(optional = false)
	private User createdBy;
	@ManyToOne(optional = false)
	private Station station;
	@ManyToOne
	private User createdFor;

	@PrePersist
	public void prePersist() {
		dateCreated = Instant.now();
	}

	@PreUpdate
	public void preUpdate() {
		dateUpdated = Instant.now();
	}

}