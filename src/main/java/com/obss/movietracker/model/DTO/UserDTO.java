package com.obss.movietracker.model.DTO;

import javax.validation.constraints.NotNull;

public class UserDTO {
	private Long userId;
	@NotNull
	private String username;
	@NotNull
	private String name;
	@NotNull
	private String surname;
	@NotNull
	private String email;
	@NotNull
	private String password;
	@NotNull
	private boolean isAdmin;

	public UserDTO() {

	}

	public UserDTO(@NotNull Long userId, @NotNull String username, @NotNull String name, @NotNull String surname,
			@NotNull String email, @NotNull String password, @NotNull boolean isAdmin) {
		super();
		this.userId = userId;
		this.username = username;
		this.name = name;
		this.surname = surname;
		this.email = email;
		this.password = password;
		this.isAdmin = isAdmin;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public boolean isAdmin() {
		return isAdmin;
	}

	public void setAdmin(boolean isAdmin) {
		this.isAdmin = isAdmin;
	}

}
