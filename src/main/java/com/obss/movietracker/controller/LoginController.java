package com.obss.movietracker.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.obss.movietracker.model.DTO.LoginDetailsDTO;
import com.obss.movietracker.security.JwtAuthenticationResponse;
import com.obss.movietracker.security.TokenProvider;

@RestController
@RequestMapping("/login")
@CrossOrigin(origins = "*", maxAge = 5000)
public class LoginController {
	
	@Autowired
    AuthenticationManager authenticationManager;
	@Autowired
    TokenProvider tokenProvider;
	
	@PostMapping
	public ResponseEntity<?> login(@Valid @RequestBody LoginDetailsDTO loginDetails){
		Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginDetails.getUsername(),
                        loginDetails.getPassword()
                )
        );

        SecurityContextHolder.getContext().setAuthentication(authentication);

        String jwt = tokenProvider.generateToken(authentication);
        return ResponseEntity.ok(new JwtAuthenticationResponse(jwt));
	}
}
