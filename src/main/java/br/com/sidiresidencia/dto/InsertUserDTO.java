package br.com.sidiresidencia.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class InsertUserDTO extends UserDTO {
	private static final long serialVersionUID = 1L;

	private String password;

}
