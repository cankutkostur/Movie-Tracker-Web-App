package com.obss.movietracker.model.DTO;

import java.util.Date;

import javax.validation.constraints.NotNull;

public class DirectorDTO {
	private Long directorId;
	@NotNull
	private String name;
	@NotNull
	private String surname;
	@NotNull
	private Date birthDate;
	@NotNull
	private String birthPlace;

	public DirectorDTO() {

	}

	public DirectorDTO(Long directorId, String name, String surname, Date birthDate, String birthPlace) {
		super();
		this.directorId = directorId;
		this.name = name;
		this.surname = surname;
		this.birthDate = birthDate;
		this.birthPlace = birthPlace;
	}

	public Long getDirectorId() {
		return directorId;
	}

	public void setDirectorId(Long directorId) {
		this.directorId = directorId;
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

	public Date getBirthDate() {
		return birthDate;
	}

	public void setBirthDate(Date birthDate) {
		this.birthDate = birthDate;
	}

	public String getBirthPlace() {
		return birthPlace;
	}

	public void setBirthPlace(String birthPlace) {
		this.birthPlace = birthPlace;
	}

}
