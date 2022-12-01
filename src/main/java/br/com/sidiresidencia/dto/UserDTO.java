package br.com.sidiresidencia.dto;

import java.io.Serializable;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import br.com.sidiresidencia.ennum.ProfileEnum;
import br.com.sidiresidencia.model.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserDTO implements Serializable {
	private static final long serialVersionUID = 1L;

	private Long id;

	@NotBlank(message = "Required field")
	private String name;
	@NotBlank(message = "Required field")
	private String lastName;
	@Email(message = "Wrong email body")
	private String email;
	@NotNull(message = "Required field")
	private ProfileEnum profile;

	public UserDTO(User user) {
		id = user.getId();
		name = user.getName();
		lastName = user.getLastName();
		email = user.getEmail();
		profile = user.getProfile();
	}

}
