package com.expressMovie.service;

import java.time.LocalDate;
import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.expressMovie.dto.DirectorDto;
import com.expressMovie.dto.MovieDto;
import com.expressMovie.entity.Director;
import com.expressMovie.entity.Movie;
import com.expressMovie.repository.DirectorRepository;
import com.expressMovie.repository.MovieRepository;
import com.expressMovie.repository.Movie_Director_Repository;

@Service
public class DirectorServiceImpl implements DirectorService{
	
	@Autowired
	private MovieRepository movie_repository;
	
	@Autowired
	private DirectorRepository director_repository;
	
	@Autowired
	private Movie_Director_Repository movie_director_repository;

	@Override
	public String insertMovieAndDirector(DirectorDto directorDto) {

		Director director= new Director();
		
		director.setDirector_id(directorDto.getDirector_id());
		director.setFirst_name(directorDto.getFirst_name());
		director.setLast_name(directorDto.getLast_name());
		director.setEmail(directorDto.getEmail());
		director.setAddress(directorDto.getAddress());
		//director.setContact_number(directorDto.getContact_number());
		//director.setMovieList(directorDto.getMovieList());
		
		director_repository.save(director);
		
		List<MovieDto> list= directorDto.getMovieList();
		
		for(MovieDto movie_dto :list) {
			
			Movie movie = new Movie();
			
			movie.setMovie_id(movie_dto.getMovie_id());
			movie.setMovie_title(movie_dto.getMovie_title());
			//movie.setDate_released(LocalDate.from(movie_dto.getDate_released()));
			//movie.setMovie_running_time(movie_dto.getMovie_running_time());
			movie.setDirector(director);
			
			movie_repository.save(movie);
		}
		
		return "Director and Movie inserted successfully.";
	}

	@Override
	public Director saveDirector(Director director) {
		// TODO Auto-generated method stub
		return director_repository.save(director);
	}

	@Override
	public List<Director> getDirectorDetails(String title) {
		// TODO Auto-generated method stub
		return director_repository.getDirectorDetails(title);
	}

	@Override
	public List<Director> getDirectorDetails(String dir_first_name, String dir_last_name) {
		// TODO Auto-generated method stub
		return director_repository.getDirectorDetails(dir_first_name,dir_last_name);
	}
	
	
	
}
