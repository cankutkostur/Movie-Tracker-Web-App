package com.obss.movietracker.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.obss.movietracker.model.Message;
import com.obss.movietracker.service.MovieListService;

@RestController
@RequestMapping("/watched")
@CrossOrigin(origins = "*", maxAge = 5000)
public class WatchedListController {
	@Autowired
	MovieListService movieListService;
	
	@GetMapping
	@PreAuthorize("hasRole('ADMIN') or hasRole('USER')")
	public ResponseEntity<?> listAll(){
		String username = SecurityContextHolder.getContext().getAuthentication().getName();
		return new ResponseEntity<>(movieListService.listMovies(username, "watched"), HttpStatus.OK);
	}
	
	@PostMapping("/{id}")
	@PreAuthorize("hasRole('ADMIN') or hasRole('USER')")
	public ResponseEntity<?> add(@PathVariable("id") Long id){
		String username = SecurityContextHolder.getContext().getAuthentication().getName();
		if(movieListService.addToList(username, id, "watched")) {
			return new ResponseEntity<>(new Message("Added to watched list."), HttpStatus.OK);
		}
		return new ResponseEntity<>(new Message("Could not added to watched list."), HttpStatus.NOT_FOUND);
	}
	
	@DeleteMapping("/{id}")
	@PreAuthorize("hasRole('ADMIN') or hasRole('USER')")
	public ResponseEntity<?> delete(@PathVariable("id") Long id){
		String username = SecurityContextHolder.getContext().getAuthentication().getName();
		if(movieListService.removeFromList(username, id, "watched")) {
			return new ResponseEntity<>(new Message("Deleted from watched list."), HttpStatus.OK);
		}
		return new ResponseEntity<>(new Message("Could not deleted from watched list."), HttpStatus.NOT_FOUND);
	}
}
