package com.obss.movietracker.model;

import java.io.Serializable;
import java.util.Date;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Director implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 5088556030341790773L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(updatable = false, nullable = false)
	private Long id;
	@Column(nullable = false)
	private String name;
	@Column(nullable = false)
	private String surname;
	@Column
	@JsonFormat(pattern = "yyyy-MM-dd")
	private Date birthDate;
	@Column
	private String birthPlace;
	@OneToMany(mappedBy = "director")
	private Set<Movie> movies;

	public Director() {

	}

	public Director(String name, String surname, Date birthDate, String birthPlace) {
		super();
		this.name = name;
		this.surname = surname;
		this.birthDate = birthDate;
		this.birthPlace = birthPlace;
	}

	public Long getId() {
		return id;
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
	
	@JsonIgnore
	public Set<Movie> getMovies() {
		return movies;
	}

	@JsonIgnore
	public void setMovies(Set<Movie> movies) {
		this.movies = movies;
	}
	
}
