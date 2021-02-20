package com.obss.movietracker.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.obss.movietracker.dao.MovieListRepository;
import com.obss.movietracker.dao.MovieRepository;
import com.obss.movietracker.dao.UserRepository;
import com.obss.movietracker.model.MovieListItem;
import com.obss.movietracker.model.User;

@Service
public class MovieListService {
	@Autowired
	MovieRepository movieRepo;
	@Autowired
	MovieListRepository movieListRepo;
	@Autowired
	UserRepository userRepo;
	
	public List<MovieListItem> listMovies(String username, String listName){
		User user = userRepo.findByUsername(username).get();
		return movieListRepo.findByUserAndListName(user, listName);
	}
	
	public boolean addToList(String username, Long movieId, String listName) {
		User user = userRepo.findByUsername(username).get();
		if(movieRepo.existsById(movieId) && (movieListRepo.findByUserAndMovieAndListName(user, movieRepo.findById(movieId).get(), listName).isEmpty())) {
			movieListRepo.save(new MovieListItem(user, movieRepo.findById(movieId).get(), listName));
			return true;
		}
		return false;
	}
	
	public boolean removeFromList(String username, Long movieId, String listName) {
		User user = userRepo.findByUsername(username).get();
		if(movieRepo.existsById(movieId) && !(movieListRepo.findByUserAndMovieAndListName(user, movieRepo.findById(movieId).get(), listName).isEmpty())) {
			MovieListItem toDelete = movieListRepo.findByUserAndMovieAndListName(user,  movieRepo.findById(movieId).get(), listName).get(0);
			movieListRepo.delete(toDelete);
			return true;
		}
		return false;
	}
}
