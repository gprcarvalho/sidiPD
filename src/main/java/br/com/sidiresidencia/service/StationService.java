package br.com.sidiresidencia.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.sidiresidencia.dto.StationDTO;
import br.com.sidiresidencia.dto.StationDateDTO;
import br.com.sidiresidencia.ennum.ProfileEnum;
import br.com.sidiresidencia.model.Station;
import br.com.sidiresidencia.repository.StationRepository;
import br.com.sidiresidencia.security.TokenService;
import br.com.sidiresidencia.service.exception.DatabaseException;
import br.com.sidiresidencia.service.exception.EntityException;
import br.com.sidiresidencia.service.exception.ReservationException;
import br.com.sidiresidencia.service.exception.StationException;

@Service
public class StationService {

	@Autowired
	private StationRepository repository;
	
	@Autowired
	private TokenService tokenService;

	@Transactional(readOnly = true)
	public Page<StationDTO> findAllStationPage(Pageable pageable) {
		
		checkProfilePermission();
		
		Page<Station> paged = repository.findAll(pageable);
		return paged.map(x -> new StationDTO(x));
	}

	@Transactional(readOnly = true)
	public StationDTO findByIdStation(Long id) {
		
		checkProfilePermission();
		
		Optional<Station> opt = repository.findById(id);
		Station station = opt.orElseThrow(() -> new EntityException("Station " + id + " not found"));
		return new StationDTO(station);
	}

	@Transactional
	public StationDTO insertStation(StationDTO dto) {
		
		checkProfilePermission();
		
		Station station = new Station();
		dtoStation(dto, station);
		station = repository.save(station);
		return new StationDTO(station);
	}

	@Transactional
	public StationDTO updateStation(Long id, StationDTO dto) {
		
		checkProfilePermission();
		
		try {
			Optional<Station> station = repository.findById(id);
			dtoStation(dto, station.get());
			return new StationDTO(repository.save(station.get()));
		} catch (EntityNotFoundException e) {
			throw new EntityException("Id not found " + id);
		}
	}

	public void deleteStation(Long id) {
		
		checkProfilePermission();
		
		try {
			repository.deleteById(id);
		} catch (EmptyResultDataAccessException e) {
			throw new EntityException("Id not found " + id);
		} catch (DataIntegrityViolationException e) {
			throw new DatabaseException("Breach of integrity");
		}
	}

	@Transactional(readOnly = true)
	public List<StationDTO> stationNotReservedByDate(StationDateDTO date) {
		
		List<Station> station = repository.stationNotReservedByDate(date.getDate());
		if (station != null && !station.isEmpty()) {
			return station.stream().map(x -> new StationDTO(x)).collect(Collectors.toList());
		} else {
			throw new StationException("No station available");
		}
	}

	private void dtoStation(StationDTO dto, Station station) {

		station.setStationNumber(dto.getStationNumber());
		station.setGroupStation(dto.getGroupStation());

	}
	
	private ProfileEnum checkProfilePermission() {

		ProfileEnum profileUser = tokenService.getProfileUser();

		if (profileUser != ProfileEnum.GESTOR && profileUser != ProfileEnum.ADMINISTRADOR) {
			throw new ReservationException("Profile not authoriuzed");
		}

		return profileUser;
	}
}
