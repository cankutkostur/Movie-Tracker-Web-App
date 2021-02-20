package com.obss.movietracker.model.DTO;

import javax.validation.constraints.NotBlank;

public class LoginDetailsDTO {
	@NotBlank
	String username;
	@NotBlank
	String password;

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

}
