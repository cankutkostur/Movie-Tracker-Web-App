package com.obss.movietracker.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import com.obss.movietracker.model.User;

public interface UserRepository extends CrudRepository<User, Long> {
	List<User> findByUsernameContaining(String name);
	Optional<User> findByUsername(String username);
	boolean existsByUsername(String name);
}
