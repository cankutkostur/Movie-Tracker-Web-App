package com.obss.movietracker.dao;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.obss.movietracker.model.Movie;

public interface MovieRepository extends CrudRepository<Movie, Long> {
	List<Movie> findByNameContaining(String name);
	boolean existsByName(String name);
	boolean existsByNameIgnoreCase(String name);
}
