package br.com.sidiresidencia.controller;

import java.net.URI;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import br.com.sidiresidencia.dto.StationDTO;
import br.com.sidiresidencia.dto.StationDateDTO;
import br.com.sidiresidencia.service.StationService;

@RestController
@RequestMapping("/api")
public class StationController {

	@Autowired
	private StationService service;

	@GetMapping(value = "/stations")
	public ResponseEntity<Page<StationDTO>> findAllPage(Pageable pageable) {
		Page<StationDTO> paged = service.findAllStationPage(pageable);
		return ResponseEntity.ok().body(paged);
	}

	@GetMapping(value = "/station/{id}")
	public ResponseEntity<StationDTO> findById(@PathVariable Long id) {
		StationDTO station = service.findByIdStation(id);
		return ResponseEntity.ok().body(station);
	}

	@PostMapping(value = "/station")
	public ResponseEntity<StationDTO> insert(@Valid @RequestBody StationDTO dto) {
		StationDTO station = service.insertStation(dto);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(dto.getId()).toUri();
		return ResponseEntity.created(uri).body(dto);
	}

	@PutMapping(value = "/station/{id}")
	public ResponseEntity<StationDTO> update(@PathVariable Long id, @Valid @RequestBody StationDTO dto) {
		StationDTO station = service.updateStation(id, dto);
		return ResponseEntity.ok().body(station);
	}

	@DeleteMapping(value = "/station/{id}")
	public ResponseEntity<Void> delete(@PathVariable Long id) {
		service.deleteStation(id);
		return ResponseEntity.noContent().build();
	}

	@PostMapping(value = "/station/date")
	public ResponseEntity<List<StationDTO>> stationNotReservedByDate(@RequestBody StationDateDTO date) {
		List<StationDTO> station = service.stationNotReservedByDate(date);
		return ResponseEntity.ok().body(station);
	}
}