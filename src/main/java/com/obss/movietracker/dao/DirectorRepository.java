package com.obss.movietracker.dao;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.obss.movietracker.model.Director;

public interface DirectorRepository extends CrudRepository<Director, Long> {
	List<Director> findByNameContaining(String name);
}
