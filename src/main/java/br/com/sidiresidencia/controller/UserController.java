package br.com.sidiresidencia.controller;

import java.net.URI;

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

import br.com.sidiresidencia.dto.InsertUserDTO;
import br.com.sidiresidencia.dto.UserDTO;
import br.com.sidiresidencia.dto.UserUpdateDTO;
import br.com.sidiresidencia.service.UserService;

@RestController
@RequestMapping("/api")
public class UserController {
	
	@Autowired
	private UserService service;
	
	@GetMapping(value = "/users")
	public ResponseEntity<Page<UserDTO>> findAll(Pageable pageable){
		Page<UserDTO> paged = service.findAllPagedUser(pageable);
		return ResponseEntity.ok().body(paged);
	}
	
	@GetMapping(value = "/user/{id}")
	public ResponseEntity<UserDTO> findById(@PathVariable Long id) {
		UserDTO dto = service.findByIdUser(id);
		return ResponseEntity.ok().body(dto);
	}

	@PostMapping(value = "/user")
	public ResponseEntity<UserDTO> insert(@Valid @RequestBody InsertUserDTO dto){
		UserDTO newDto = service.insertUser(dto);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(dto.getId()).toUri();
		return ResponseEntity.created(uri).body(dto);
	}
	
	@PutMapping(value = "/user/{id}")
	public ResponseEntity<UserDTO> update(@PathVariable Long id,@Valid @RequestBody  UserUpdateDTO dto) {
		UserDTO newDto = service.updateUser(id, dto);
		return ResponseEntity.ok().body(newDto);
	}
	
	@DeleteMapping(value = "/user/{id}")
	public ResponseEntity<Void> delete(@PathVariable Long id) {
		service.deleteUser(id);
		return ResponseEntity.noContent().build();
	}  
}
