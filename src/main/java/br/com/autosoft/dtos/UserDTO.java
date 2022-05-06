package br.com.autosoft.dtos;

import java.io.Serializable;

import javax.validation.constraints.NotEmpty;

import br.com.autosoft.entities.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class UserDTO implements Serializable{
	private static final long serialVersionUID = 1L;
	
	@NotEmpty(message = "The id User cannot be Empty")
	private Integer id;
	@NotEmpty(message = "The Name User cannot be Empty")
	private String username;
	@NotEmpty(message = "The Emal User cannot be Empty")
	private String email;
	@NotEmpty(message = "The access level must be filled")
	private Boolean admin;

	public UserDTO(User user) {
		this.id = user.getId();
		this.username = user.getUsername();
		this.email = user.getEmail();
		this.admin = user.getAdmin();
	}
}
