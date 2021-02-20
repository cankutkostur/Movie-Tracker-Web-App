package com.obss.movietracker.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.password.PasswordEncoder;
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
import com.obss.movietracker.model.User;
import com.obss.movietracker.model.DTO.UserDTO;
import com.obss.movietracker.service.UserService;

@RestController
@RequestMapping("/users")
@CrossOrigin(origins = "*", maxAge = 5000)
public class UserController {
	@Autowired
	UserService userService;
	@Autowired
    PasswordEncoder passwordEncoder;

	@GetMapping
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<?> search(@RequestParam(name = "username", required = false) String username) {
		if (username == null)
			return new ResponseEntity<>(userService.listAll(), HttpStatus.OK);
		List<User> user = userService.search(username);
		if (!user.isEmpty()) {
			return new ResponseEntity<>(user, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(new Message("User not found!"), HttpStatus.NOT_FOUND);
		}
	}

	@GetMapping("/{id}")
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<?> searchById(@PathVariable("id") Long id) {
		User user = userService.searchById(id);
		if (user != null) {
			return new ResponseEntity<>(user, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(new Message("User not found!"), HttpStatus.NOT_FOUND);
		}
	}
	
	@PostMapping
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<?> add(@RequestBody @Valid UserDTO user, BindingResult bindingResult) {
		if(bindingResult.hasErrors()) {
			return new ResponseEntity<>(new Message("Missing info"), HttpStatus.BAD_REQUEST);
		}
		user.setPassword(passwordEncoder.encode(user.getPassword()));
		if(userService.add(user)) {
			return new ResponseEntity<>(new Message("User added"), HttpStatus.OK);
		}
		return new ResponseEntity<>(new Message("User already exist"), HttpStatus.CONFLICT);
	}
	
	@PutMapping
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<?> update(@RequestBody @Valid UserDTO user, BindingResult bindingResult){
		if(bindingResult.hasErrors()) {
			return new ResponseEntity<>(new Message("Missing info"), HttpStatus.BAD_REQUEST);
		}
		user.setPassword(passwordEncoder.encode(user.getPassword()));
		if(userService.update(user)) {
			return new ResponseEntity<>(new Message("User updated"), HttpStatus.OK);
		}
		return new ResponseEntity<>(new Message("User does not exist!"), HttpStatus.CONFLICT);
	}
	
	@PutMapping("/{id}")
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<?> updateById(@RequestBody @Valid UserDTO user, BindingResult bindingResult, @PathVariable("id") Long id){
		if(bindingResult.hasErrors()) {
			return new ResponseEntity<>(new Message("Missing info"), HttpStatus.BAD_REQUEST);
		}
		user.setUserId(id);
		user.setPassword(passwordEncoder.encode(user.getPassword()));
		if(userService.updateById(user, id)) {
			return new ResponseEntity<>(new Message("User updated"), HttpStatus.OK);
		}
		return new ResponseEntity<>(new Message("User does not exist!"), HttpStatus.CONFLICT);
	}
	
	@DeleteMapping("/{id}")
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<?> deleteById(@PathVariable("id") Long id){
		if(userService.delete(id)) {
			return new ResponseEntity<>(new Message("User deleted successfully."), HttpStatus.OK);
		}
		return new ResponseEntity<>(new Message("User does not exist!"), HttpStatus.NOT_FOUND);
	}
}
