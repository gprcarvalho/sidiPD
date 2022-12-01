package br.com.sidiresidencia.dto;

import java.io.Serializable;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class UserTokenDTO implements Serializable {
	private static final long serialVersionUID = 1L;

	private String type;
	private String token;

	public UserTokenDTO(String type, String token) {
		this.type = type;
		this.token = token;
	}

}
