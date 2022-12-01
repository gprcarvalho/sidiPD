package br.com.sidiresidencia.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.sidiresidencia.dto.UserLoginDTO;
import br.com.sidiresidencia.dto.UserTokenDTO;
import br.com.sidiresidencia.security.TokenService;

@RestController
@RequestMapping("/api")
public class AuthenticationLoginController {

	@Autowired
	private AuthenticationManager authenticationManager;
	
	@Autowired
	private TokenService tokenService;
	
	@PostMapping(value = "/login")
	public ResponseEntity<UserTokenDTO> authenticationLogin(@Valid @RequestBody UserLoginDTO dto) {
		UsernamePasswordAuthenticationToken login = dto.convet();
		try {
			Authentication authentication = authenticationManager.authenticate(login);
			String token = tokenService.generateToken(authentication);
			return ResponseEntity.ok(new UserTokenDTO("Bearer", token));
		} catch (AuthenticationException e) {
			return ResponseEntity.badRequest().build();
		}
	}
	
}
