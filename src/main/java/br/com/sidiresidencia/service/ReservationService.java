package br.com.sidiresidencia.service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.sidiresidencia.dto.ReservationDTO;
import br.com.sidiresidencia.ennum.ProfileEnum;
import br.com.sidiresidencia.model.Reservation;
import br.com.sidiresidencia.model.User;
import br.com.sidiresidencia.repository.ReservationRepository;
import br.com.sidiresidencia.repository.StationRepository;
import br.com.sidiresidencia.repository.UserRepository;
import br.com.sidiresidencia.security.TokenService;
import br.com.sidiresidencia.service.exception.EntityException;
import br.com.sidiresidencia.service.exception.ReservationException;

@Service
public class ReservationService {

	@Autowired
	private ReservationRepository repository;

	@Autowired
	private TokenService tokenService;

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private StationRepository stationRepository;

	@Transactional(readOnly = true)
	public Page<ReservationDTO> findAllReservagionPage(Pageable pageable) {
		Page<Reservation> paged = repository.findAll(pageable);
		return paged.map(x -> new ReservationDTO(x));
	}

	@Transactional(readOnly = true)
	public ReservationDTO findByIdReservation(Long id) {
		Optional<Reservation> opt = repository.findById(id);
		Reservation reservation = opt.orElseThrow(() -> new EntityException("Reservation " + id + " not exist"));
		return new ReservationDTO(reservation);
	}

	@Transactional
	public ReservationDTO insertReservationMe(ReservationDTO dto) {

		checkIdStationExist(dto.getStation().getId());

		checkExistReservationUserdate(tokenService.getUserIdMe(), dto.getDateReservation());

		checkReservationExist(dto.getDateReservation(), dto.getStation().getId());

		Reservation reservation = new Reservation();
		dto.setCreatedBy(getIdUserLogged());
		dto.setCreatedFor(getIdUserLogged());
		dtoReservation(dto, reservation);
		reservation = repository.save(reservation);
		return new ReservationDTO(reservation);

	}

	@Transactional
	public ReservationDTO insertReservationFor(ReservationDTO dto) {

		checkProfilePermission();

		checkIdStationExist(dto.getStation().getId());

		checkReservationExist(dto.getDateReservation(), dto.getStation().getId());

		Reservation reservation = new Reservation();
		dto.setCreatedBy(getIdUserLogged());
		dtoReservation(dto, reservation);
		reservation = repository.save(reservation);
		return new ReservationDTO(reservation);

	}

	@Transactional
	public ReservationDTO updateReservation(Long id, ReservationDTO dto) {

		checkIdReservationExist(id);
		
		checkProfilePermission();
		
		checkIdStationExist(dto.getStation().getId());

		checkReservationExist(dto.getDateReservation(), dto.getStation().getId());

		Optional<Reservation> reservation = repository.findById(id);
		dtoReservation(dto, reservation.get());
		return new ReservationDTO(repository.save(reservation.get()));

	}

	@Transactional
	public void canceledReservation(Long id) {
		repository.findById(id).ifPresentOrElse(reservation -> {
			reservation.setCanceled(true);
			repository.save(reservation);
		}, () -> {
			throw new EntityException("Id not found " + id);
		});
	}

	@Transactional(readOnly = true)
	public List<ReservationDTO> findAllActiveReservationByUser(Long id) {
		List<Reservation> list = repository.findAllActiveReservationByUser(id);
		if (list != null) {
			return list.stream().map(x -> new ReservationDTO(x)).collect(Collectors.toList());
		} else {
			throw new EntityException("Not Reservation");
		}
	}

	private void dtoReservation(ReservationDTO dto, Reservation reservation) {

		reservation.setDateReservation(dto.getDateReservation());
		reservation.setCreatedBy(dto.getCreatedBy());
		reservation.setCreatedFor(dto.getCreatedFor());
		reservation.setStation(dto.getStation());
	}

	private User getIdUserLogged() {
		Long idUserLogged = tokenService.getUserIdMe();
		User userLogged = userRepository.findByUser(idUserLogged);

		return userLogged;
	}

	private Long checkIdReservationExist(Long id) {

		Long idReservation = id;

		if (!repository.existsById(idReservation)) {
			throw new EntityException("The reservation with id: " + idReservation + " does not exist");
		}

		return idReservation;
	}

	private Long checkIdStationExist(Long id) {

		Long idStation = id;

		if (!stationRepository.existsById(idStation)) {
			throw new ReservationException("The station with id: " + idStation + " does not exist");
		}

		return idStation;
	}

	private Reservation checkExistReservationUserdate(Long id, LocalDate date) {

		Reservation checkExistReservationUserdate = repository.checkExistReservationUserDate(id, date);

		if (checkExistReservationUserdate != null) {
			throw new ReservationException("You have already booked a station on that date");
		}

		return checkExistReservationUserdate;
	}

	private Reservation checkReservationExist(LocalDate date, Long id) {

		Reservation checkReservationExist = repository.findByReservation(date, id);

		if (checkReservationExist != null) {
			throw new ReservationException("There is already a reservation for that date at that station");
		}

		return checkReservationExist;
	}

	private ProfileEnum checkProfilePermission() {

		ProfileEnum profileUser = tokenService.getProfileUser();

		if (profileUser != ProfileEnum.GESTOR && profileUser != ProfileEnum.ADMINISTRADOR) {
			throw new ReservationException("Profile not authoriuzed");
		}

		return profileUser;
	}

}
