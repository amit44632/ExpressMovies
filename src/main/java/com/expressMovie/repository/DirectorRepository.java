package com.expressMovie.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.expressMovie.entity.Director;
import com.expressMovie.entity.Movie;

@Repository
public interface DirectorRepository extends JpaRepository<Director, Integer>{

	
	@Query(value = "SELECT D.* FROM DIRECTOR D WHERE DIRECTOR_ID = (SELECT MD.DIRECTOR_ID FROM MOVIE_DIRECTOR MD WHERE MD.MOVIE_ID = \r\n" + 
			"(SELECT M.MOVIE_ID FROM MOVIE M WHERE M.MOVIE_TITLE =?1))", nativeQuery = true)
	List<Director> getDirectorDetails(String title);
	@Query(value = "SELECT * FROM DIRECTOR D WHERE D.FIRST_NAME =?1 and D.LAST_NAME =?2", nativeQuery = true)
	List<Director> getDirectorDetails(String dir_first_name, String dir_last_name);

	

}
