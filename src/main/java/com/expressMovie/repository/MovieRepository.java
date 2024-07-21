package com.expressMovie.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.expressMovie.entity.Movie;

@Repository
public interface MovieRepository extends JpaRepository<Movie, Integer>{
	
	@Query(value = "select * from Movie c where c.movie_title =?1", nativeQuery = true)
	List<Movie> findByTitle(String title);
	
	@Query(value = "select m.* from MOVIE m where m.MOVIE_ID in(select MOVIE_ID from MOVIE_DIRECTOR md where md.DIRECTOR_ID=" + 
			"(select d.DIRECTOR_ID from DIRECTOR d where d.FIRST_NAME=?1 and d.LAST_NAME =?2))", nativeQuery = true)
	List<Movie> findByDirector(String dir_first_name,String dir_last_name);

	@Query(value = "select * from Movie", nativeQuery = true)
	List<Movie> getAllMovies();

}
