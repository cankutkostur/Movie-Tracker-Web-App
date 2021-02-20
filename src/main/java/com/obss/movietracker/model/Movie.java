package com.obss.movietracker.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonFormat;

@Entity
public class Movie implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 5219218305599802655L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(updatable = false, nullable = false)
	private Long id;
	@Column(nullable = false)
	@NotNull
	private String name;
	@ManyToOne
	@JoinColumn(nullable = false)
	@NotNull
	private Director director;
	@Column(nullable = false)
	@JsonFormat(pattern = "yyyy-MM-dd")
	private Date releaseDate;
	@Column(nullable = false)
	private float imdbRating;
	@Column(nullable = false)
	private float duration;
	@Column(nullable = false)
	private String genre;

	public Movie() {

	}

	public Movie(String name, Director director, Date releaseDate, float imdbRating, float duration,
			String genre) {
		super();
		this.name = name;
		this.director = director;
		this.releaseDate = releaseDate;
		this.imdbRating = imdbRating;
		this.duration = duration;
		this.genre = genre;
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

	public Director getDirector() {
		return director;
	}

	public void setDirector(Director director) {
		this.director = director;
	}

	public Date getReleaseDate() {
		return releaseDate;
	}

	public void setReleaseDate(Date releaseDate) {
		this.releaseDate = releaseDate;
	}

	public float getImdbRating() {
		return imdbRating;
	}

	public void setImdbRating(float imdbRating) {
		this.imdbRating = imdbRating;
	}

	public float getDuration() {
		return duration;
	}

	public void setDuration(float duration) {
		this.duration = duration;
	}

	public String getGenre() {
		return genre;
	}

	public void setGenre(String genre) {
		this.genre = genre;
	}

}
