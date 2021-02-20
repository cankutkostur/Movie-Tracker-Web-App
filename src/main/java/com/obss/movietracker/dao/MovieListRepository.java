package com.obss.movietracker.dao;

import java.util.List;
import org.springframework.data.repository.CrudRepository;

import com.obss.movietracker.model.Movie;
import com.obss.movietracker.model.MovieListItem;
import com.obss.movietracker.model.User;

public interface MovieListRepository extends CrudRepository<MovieListItem, Long> {
	List<MovieListItem> findByUserAndListName(User user, String listName);
	List<MovieListItem> findByUserAndMovieAndListName(User user, Movie movie, String listName);
}
