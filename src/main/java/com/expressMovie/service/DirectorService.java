package com.expressMovie.service;



import java.util.List;

import com.expressMovie.dto.DirectorDto;
import com.expressMovie.entity.Director;

public interface DirectorService {
	
	public String insertMovieAndDirector(DirectorDto directorDto);

	public Director saveDirector(Director director);

	public List<Director> getDirectorDetails(String title);

	public List<Director> getDirectorDetails(String dir_first_name, String dir_last_name);

}
