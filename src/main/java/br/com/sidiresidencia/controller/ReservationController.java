package br.com.sidiresidencia.controller;

import java.net.URI;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import br.com.sidiresidencia.dto.ReservationDTO;
import br.com.sidiresidencia.service.ReservationService;

@RestController
@RequestMapping("/api")
public class ReservationController {

	@Autowired
	private ReservationService service;

	@GetMapping(value = "/reservations")
	public ResponseEntity<Page<ReservationDTO>> findAll(Pageable pageable) {
		Page<ReservationDTO> paged = service.findAllReservagionPage(pageable);
		return ResponseEntity.ok().body(paged);
	}

	@GetMapping(value = "/reservation/{id}")
	public ResponseEntity<ReservationDTO> findById(@PathVariable Long id) {
		ReservationDTO dto = service.findByIdReservation(id);
		return ResponseEntity.ok().body(dto);
	}

	@PostMapping(value = "/reservation/me")
	public ResponseEntity<ReservationDTO> insertMe(@Valid @RequestBody ReservationDTO dto) {
		ReservationDTO newDto = service.insertReservationMe(dto);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(dto.getId()).toUri();
		return ResponseEntity.created(uri).body(dto);
	}

	@PostMapping(value = "/reservation/for")
	public ResponseEntity<ReservationDTO> inserFor(@Valid @RequestBody ReservationDTO dto) {
		ReservationDTO newDto = service.insertReservationFor(dto);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(dto.getId()).toUri();
		return ResponseEntity.created(uri).body(dto);
	}

	@PutMapping(value = "/reservation/{id}")
	public ResponseEntity<ReservationDTO> update(@PathVariable Long id, @Valid @RequestBody ReservationDTO dto) {
		ReservationDTO newDto = service.updateReservation(id, dto);
		return ResponseEntity.ok().body(newDto);
	}

	@PutMapping(value = "/reservation/canceled/{id}")
	public ResponseEntity<Void> canceledReservatio(@PathVariable Long id) {
		service.canceledReservation(id);
		return ResponseEntity.ok().build();
	}

	@GetMapping(value = "/reservations/{id}")
	public ResponseEntity<List<ReservationDTO>> findAllReservationCanceledUser(@PathVariable Long id) {
		List<ReservationDTO> list = service.findAllActiveReservationByUser(id);
		return ResponseEntity.ok().body(list);
	}

}