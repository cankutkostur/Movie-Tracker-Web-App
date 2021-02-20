package com.obss.movietracker.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.obss.movietracker.dao.UserRepository;
import com.obss.movietracker.model.User;
import com.obss.movietracker.model.DTO.UserDTO;

@Service
public class UserService {
	@Autowired
	UserRepository userRepo;
	
	public boolean add(UserDTO user) {
		if (!userRepo.existsByUsername(user.getUsername())) {
			userRepo.save(new User(user.getUsername(), user.getName(), user.getSurname(), user.getEmail(), user.getPassword(), user.isAdmin()));
			return true;
		}
		return false;
	}

	public boolean delete(Long id) {
		if (userRepo.existsById(id)) {
			userRepo.deleteById(id);
			return true;
		}
		return false;
	}

	public boolean update(UserDTO user) {
		if (userRepo.existsByUsername(user.getUsername())) {
			User updated = userRepo.findByUsername(user.getUsername()).get();
			updated.setName(user.getName());
			updated.setSurname(user.getSurname());
			updated.setPassword(user.getPassword());
			updated.setEmail(user.getEmail());
			updated.setAdmin(user.isAdmin());
			userRepo.save(updated);
			return true;
		}
		return false;
	}
	
	public boolean updateById(UserDTO user, Long id) {
		if (userRepo.existsById(id)) {
			User updated = userRepo.findById(id).get();
			updated.setName(user.getName());
			updated.setSurname(user.getSurname());
			updated.setPassword(user.getPassword());
			updated.setEmail(user.getEmail());
			updated.setAdmin(user.isAdmin());
			userRepo.save(updated);
			return true;
		}
		return false;
	}
	
	public List<User> search(String name){
		return userRepo.findByUsernameContaining(name);
	}
	
	public User searchById(Long id) {
		if(userRepo.existsById(id)) {
			return userRepo.findById(id).get();
		}
		return null;
	}
	
	public List<User> listAll(){
		List<User> allUsers = new ArrayList<>();
		userRepo.findAll().forEach(allUsers::add);
		return allUsers;
	}
}
