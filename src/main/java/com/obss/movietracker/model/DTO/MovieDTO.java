package com.obss.movietracker.model.DTO;

import java.util.Date;

import javax.validation.constraints.NotNull;

public class MovieDTO {
	private Long movieId;
	@NotNull
	private String name;
	@NotNull
	private Long directorId;
	private Date releaseDate;
	private float imdbRating;
	private float duration;
	private String genre;

	public MovieDTO() {
	}

	public MovieDTO(@NotNull Long movieId, @NotNull String name, @NotNull Long directorId, @NotNull Date releaseDate,
			@NotNull float imdbRating, @NotNull float duration, @NotNull String genre) {
		super();
		this.movieId = movieId;
		this.name = name;
		this.directorId = directorId;
		this.releaseDate = releaseDate;
		this.imdbRating = imdbRating;
		this.duration = duration;
		this.genre = genre;
	}

	public Long getMovieId() {
		return movieId;
	}

	public void setMovieId(Long movieId) {
		this.movieId = movieId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Long getDirectorId() {
		return directorId;
	}

	public void setDirectorId(Long directorId) {
		this.directorId = directorId;
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
