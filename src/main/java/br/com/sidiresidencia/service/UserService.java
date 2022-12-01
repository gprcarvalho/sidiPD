package br.com.sidiresidencia.service;

import java.util.Optional;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.sidiresidencia.dto.InsertUserDTO;
import br.com.sidiresidencia.dto.UserDTO;
import br.com.sidiresidencia.ennum.ProfileEnum;
import br.com.sidiresidencia.model.User;
import br.com.sidiresidencia.repository.UserRepository;
import br.com.sidiresidencia.security.TokenService;
import br.com.sidiresidencia.service.exception.DatabaseException;
import br.com.sidiresidencia.service.exception.EntityException;
import br.com.sidiresidencia.service.exception.ReservationException;

@Service
public class UserService{

	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;

	@Autowired
	private UserRepository repository;
	
	@Autowired
	private TokenService tokenService;

	@Transactional(readOnly = true)
	public Page<UserDTO> findAllPagedUser(Pageable pageable){
		
		checkProfilePermission();
		
		Page<User> paged = repository.findAll(pageable);
		return paged.map(x -> new UserDTO(x));
	}
	
	@Transactional(readOnly = true)
	public UserDTO findByIdUser(Long id) {
		
		checkProfilePermission();
		
		Optional<User> obj = repository.findById(id);
		User user = obj.orElseThrow(() -> new EntityException("User " + id +" not exist"));
		return new UserDTO(user);
	}
	
	@Transactional
	public UserDTO insertUser(InsertUserDTO dto){
		
		checkProfilePermission();
		
		User user = new User();
		dtoUsuario(dto, user);
		user.setPassword(bCryptPasswordEncoder.encode(dto.getPassword()));
		user = repository.save(user);
		return new UserDTO(user);
	}
	
	@Transactional
	public UserDTO updateUser(Long id, UserDTO dto) { 
		
		checkProfilePermission();
		
		try {
			Optional<User> user = repository.findById(id);
			dtoUsuario(dto, user.get());
			return new UserDTO(repository.save(user.get()));
		}
		catch(EntityNotFoundException e) {
			throw new EntityException("Id not found " + id);
		}
	}
	
	public void deleteUser(Long id) {
		
		checkProfilePermission();
		
		try {
			repository.deleteById(id);
		}
		catch(EmptyResultDataAccessException e) {
			throw new EntityException("Id not found " + id);
		}
		catch(DataIntegrityViolationException e) {
			throw new DatabaseException("Breach of integrity");
		}
	}   

	private void dtoUsuario(UserDTO dto, User user) {
		
		user.setName(dto.getName());
		user.setLastName(dto.getLastName());
		user.setEmail(dto.getEmail());
		user.setProfile(dto.getProfile());
	}
	
	private ProfileEnum checkProfilePermission() {

		ProfileEnum profileUser = tokenService.getProfileUser();

		if (profileUser != ProfileEnum.GESTOR && profileUser != ProfileEnum.ADMINISTRADOR) {
			throw new ReservationException("Profile not authoriuzed");
		}

		return profileUser;
	}
}
