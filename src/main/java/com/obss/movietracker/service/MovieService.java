package com.obss.movietracker.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.obss.movietracker.dao.DirectorRepository;
import com.obss.movietracker.dao.MovieRepository;
import com.obss.movietracker.model.Movie;
import com.obss.movietracker.model.DTO.MovieDTO;

@Service
public class MovieService {
	@Autowired
	MovieRepository movieRepo;
	@Autowired
	DirectorRepository directorRepo;
	
	public boolean add(MovieDTO movie) {
		if (!movieRepo.existsByNameIgnoreCase(movie.getName()) && directorRepo.existsById(movie.getDirectorId())) {
			movieRepo.save(new Movie(movie.getName(), directorRepo.findById(movie.getDirectorId()).get(), movie.getReleaseDate(), movie.getImdbRating(), movie.getDuration(), movie.getGenre()));
			return true;
		}
		return false;
	}

	public boolean delete(Long id) {
		if (movieRepo.existsById(id)) {
			movieRepo.deleteById(id);
			return true;
		}
		return false;
	}

	public boolean update(MovieDTO movie) {
		if (movieRepo.existsById(movie.getMovieId()) && directorRepo.existsById(movie.getDirectorId())) {
			Movie updated = movieRepo.findById(movie.getMovieId()).get();
			updated.setDirector(directorRepo.findById(movie.getDirectorId()).get());
			updated.setDuration(movie.getDuration());
			updated.setGenre(movie.getGenre());
			updated.setImdbRating(movie.getImdbRating());
			updated.setName(movie.getName());
			updated.setReleaseDate(movie.getReleaseDate());
			movieRepo.save(updated);
			return true;
		}
		return false;
	}
	
	public boolean updateById(MovieDTO movie, Long id) {
		if (movieRepo.existsById(id) && directorRepo.existsById(movie.getDirectorId())) {
			Movie updated = movieRepo.findById(id).get();
			updated.setDirector(directorRepo.findById(movie.getDirectorId()).get());
			updated.setDuration(movie.getDuration());
			updated.setGenre(movie.getGenre());
			updated.setImdbRating(movie.getImdbRating());
			updated.setName(movie.getName());
			updated.setReleaseDate(movie.getReleaseDate());
			movieRepo.save(updated);
			return true;
		}
		return false;
	}
	
	public List<Movie> search(String name){
		return movieRepo.findByNameContaining(name);
	}
	public Movie searchById(Long id) {
		if(movieRepo.existsById(id)) {
			return movieRepo.findById(id).get();
		}
		return null;
	}
	
	public List<Movie> listAll(){
		List<Movie> allMovies = new ArrayList<>();
		movieRepo.findAll().forEach(allMovies::add);
		return allMovies;
	}
}
