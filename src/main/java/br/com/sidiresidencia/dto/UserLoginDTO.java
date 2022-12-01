package br.com.sidiresidencia.dto;

import java.io.Serializable;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserLoginDTO implements Serializable {
	private static final long serialVersionUID = 1L;

	private String email;
	private String password;

	public UsernamePasswordAuthenticationToken convet() {
		return new UsernamePasswordAuthenticationToken(email, password);
	}
}
