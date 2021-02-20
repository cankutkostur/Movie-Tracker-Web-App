package com.obss.movietracker.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.obss.movietracker.dao.DirectorRepository;
import com.obss.movietracker.model.Director;
import com.obss.movietracker.model.DTO.DirectorDTO;

@Service
public class DirectorService {
	@Autowired
	DirectorRepository directorRepo;
	
	public boolean add(DirectorDTO director) {
		directorRepo.save(new Director(director.getName(), director.getSurname(), director.getBirthDate(), director.getBirthPlace()));
		return true;
	}

	public boolean delete(Long id) {
		if (directorRepo.existsById(id)) {
			directorRepo.deleteById(id);
			return true;
		}
		return false;
	}

	public boolean update(DirectorDTO director) {
		if (directorRepo.existsById(director.getDirectorId())) {
			Director updated = directorRepo.findById(director.getDirectorId()).get();
			updated.setName(director.getName());
			updated.setSurname(director.getSurname());
			updated.setBirthDate(director.getBirthDate());
			updated.setBirthPlace(director.getBirthPlace());
			directorRepo.save(updated);
			return true;
		}
		return false;
	}
	
	public boolean updateById(DirectorDTO director, Long id) {
		if (directorRepo.existsById(id)) {
			Director updated = directorRepo.findById(id).get();
			updated.setName(director.getName());
			updated.setSurname(director.getSurname());
			updated.setBirthDate(director.getBirthDate());
			updated.setBirthPlace(director.getBirthPlace());
			directorRepo.save(updated);
			return true;
		}
		return false;
	}
	
	public List<Director> search(String name){
		return directorRepo.findByNameContaining(name);
	}
	
	public Director searchById(Long id) {
		if(directorRepo.existsById(id)) {
			return directorRepo.findById(id).get();
		}
		return null;
	}
	
	public List<Director> listAll(){
		List<Director> allMovies = new ArrayList<>();
		directorRepo.findAll().forEach(allMovies::add);
		return allMovies;
	}
}
