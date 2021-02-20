package com.obss.movietracker.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class MovieListItem implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1038305301065976685L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@ManyToOne
	private User user;
	@ManyToOne
	private Movie movie;
	@Column
	private String listName;

	public MovieListItem() {

	}

	public MovieListItem(User user, Movie movie, String listName) {
		super();
		this.user = user;
		this.movie = movie;
		this.listName = listName;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long movieListId) {
		this.id = movieListId;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Movie getMovie() {
		return movie;
	}

	public void setMovie(Movie movie) {
		this.movie = movie;
	}

}
