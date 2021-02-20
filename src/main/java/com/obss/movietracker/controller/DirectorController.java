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
import com.obss.movietracker.model.DTO.DirectorDTO;
import com.obss.movietracker.model.Director;
import com.obss.movietracker.service.DirectorService;

@RestController
@RequestMapping("/directors")
@CrossOrigin(origins = "*", maxAge = 5000)
public class DirectorController {
	@Autowired
	DirectorService directorService;

	@GetMapping
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<?> search(@RequestParam(name = "name", required = false) String name) {
		if (name == null)
			return new ResponseEntity<>(directorService.listAll(), HttpStatus.OK);
		List<Director> director = directorService.search(name);
		if (!director.isEmpty()) {
			return new ResponseEntity<>(director, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(new Message("Director not found!"), HttpStatus.NOT_FOUND);
		}
	}

	@GetMapping("/{id}")
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<?> searchById(@PathVariable("id") Long id) {
		Director director = directorService.searchById(id);
		if (director != null) {
			return new ResponseEntity<>(director, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(new Message("Director not found!"), HttpStatus.NOT_FOUND);
		}
	}
	
	@PostMapping
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<?> add(@RequestBody @Valid DirectorDTO director, BindingResult bindingResult) {
		if(bindingResult.hasErrors()) {
			return new ResponseEntity<>(new Message("Missing info"), HttpStatus.BAD_REQUEST);
		}
		if(directorService.add(director)) {
			return new ResponseEntity<>(new Message("Director added"), HttpStatus.OK);
		}
		return new ResponseEntity<>(new Message("Director already exist"), HttpStatus.CONFLICT);
	}
	
	@PutMapping
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<?> update(@RequestBody @Valid DirectorDTO director, BindingResult bindingResult){
		if(bindingResult.hasErrors()) {
			return new ResponseEntity<>(new Message("Missing info"), HttpStatus.BAD_REQUEST);
		}
		if(directorService.update(director)) {
			return new ResponseEntity<>(new Message("Director updated"), HttpStatus.OK);
		}
		return new ResponseEntity<>(new Message("Director does not exist!"), HttpStatus.CONFLICT);
	}
	
	@PutMapping("/{id}")
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<?> updateById(@RequestBody @Valid DirectorDTO director, BindingResult bindingResult, @PathVariable("id") Long id){
		if(bindingResult.hasErrors()) {
			return new ResponseEntity<>(new Message("Missing info"), HttpStatus.BAD_REQUEST);
		}
		director.setDirectorId(id);
		if(directorService.updateById(director, id)) {
			return new ResponseEntity<>(new Message("Director updated"), HttpStatus.OK);
		}
		return new ResponseEntity<>(new Message("Director does not exist!"), HttpStatus.CONFLICT);
	}
	
	@DeleteMapping("/{id}")
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<?> deleteById(@PathVariable("id") Long id){
		if(directorService.delete(id)) {
			return new ResponseEntity<>(new Message("Director deleted successfully."), HttpStatus.OK);
		}
		return new ResponseEntity<>(new Message("Director does not exist!"), HttpStatus.NOT_FOUND);
	}
}
