package com.obss.movietracker.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.obss.movietracker.model.Message;
import com.obss.movietracker.model.Movie;
import com.obss.movietracker.model.DTO.MovieDTO;
import com.obss.movietracker.service.MovieService;

@RestController
@RequestMapping("/movies")
@CrossOrigin(origins = "*", maxAge = 5000)
public class MovieController {
	@Autowired
	MovieService movieService;

	@GetMapping
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<?> search(@RequestParam(name = "name", required = false) String name) {
		if (name == null)
			return new ResponseEntity<>(movieService.listAll(), HttpStatus.OK);
		List<Movie> movie = movieService.search(name);
		if (!movie.isEmpty()) {
			return new ResponseEntity<>(movie, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(new Message("Movie not found!"), HttpStatus.NOT_FOUND);
		}
	}

	@GetMapping("/{id}")
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<?> searchById(@PathVariable("id") Long id) {
		Movie movie = movieService.searchById(id);
		if (movie != null) {
			return new ResponseEntity<>(movie, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(new Message("Movie not found!"), HttpStatus.NOT_FOUND);
		}
	}
	
	@PostMapping
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<?> add(@RequestBody @Valid MovieDTO movie, BindingResult bindingResult) {
		if(bindingResult.hasErrors()) {
			return new ResponseEntity<>(new Message("Missing info"), HttpStatus.BAD_REQUEST);
		}
		if(movieService.add(movie)) {
			return new ResponseEntity<>(new Message("Movie added"), HttpStatus.OK);
		}
		return new ResponseEntity<>(new Message("Movie already exist"), HttpStatus.CONFLICT);
	}
	
	@PutMapping
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<?> update(@RequestBody @Valid MovieDTO movie, BindingResult bindingResult){
		if(bindingResult.hasErrors()) {
			return new ResponseEntity<>(new Message("Missing info"), HttpStatus.BAD_REQUEST);
		}
		if(movieService.update(movie)) {
			return new ResponseEntity<>(new Message("Movie updated"), HttpStatus.OK);
		}
		return new ResponseEntity<>(new Message("Movie or director does not exist!"), HttpStatus.CONFLICT);
	}
	
	@PutMapping("/{id}")
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<?> updateById(@RequestBody @Valid MovieDTO movie, BindingResult bindingResult, @PathVariable("id") Long id){
		if(bindingResult.hasErrors()) {
			return new ResponseEntity<>(new Message("Missing info"), HttpStatus.BAD_REQUEST);
		}
		movie.setMovieId(id);
		if(movieService.updateById(movie, id)) {
			return new ResponseEntity<>(new Message("Movie updated"), HttpStatus.OK);
		}
		return new ResponseEntity<>(new Message("Movie or director does not exist!"), HttpStatus.CONFLICT);
	}
	
	@DeleteMapping("/{id}")
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<?> deleteById(@PathVariable("id") Long id){
		if(movieService.delete(id)) {
			return new ResponseEntity<>(new Message("Movie deleted successfully."), HttpStatus.OK);
		}
		return new ResponseEntity<>(new Message("Movie does not exist!"), HttpStatus.NOT_FOUND);
	}
}
