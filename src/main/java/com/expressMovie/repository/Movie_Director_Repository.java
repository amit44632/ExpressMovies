package com.expressMovie.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.expressMovie.entity.Movie_Director;

@Repository
public interface Movie_Director_Repository extends JpaRepository<Movie_Director, Integer>{

	@Query(value = "select * FROM MOVIE_DIRECTOR WHERE MOVIE_ID =?1", nativeQuery = true)
	Movie_Director getRefernceData(int movie_id);

}
