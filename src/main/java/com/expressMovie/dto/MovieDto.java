package com.expressMovie.dto;

import java.time.LocalDate;
import java.util.Date;

import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.format.annotation.DateTimeFormat;

import com.expressMovie.entity.Director;


public class MovieDto {

private Integer movie_id;
	
	private String movie_title;
	
	@DateTimeFormat(pattern="yyyy-MM-dd")
	private LocalDate date_released;
	
	//@Temporal(TemporalType.TIME)
	private Date movie_running_time;
	
	private DirectorDto director;

	public Integer getMovie_id() {
		return movie_id;
	}

	public void setMovie_id(Integer movie_id) {
		this.movie_id = movie_id;
	}

	public String getMovie_title() {
		return movie_title;
	}

	public void setMovie_title(String movie_title) {
		this.movie_title = movie_title;
	}

	public LocalDate getDate_released() {
		return date_released;
	}

	public void setDate_released(LocalDate date_released) {
		this.date_released = date_released;
	}

	public Date getMovie_running_time() {
		return movie_running_time;
	}

	public void setMovie_running_time(Date movie_running_time) {
		this.movie_running_time = movie_running_time;
	}

	public DirectorDto getDirector() {
		return director;
	}

	public void setDirector(DirectorDto director) {
		this.director = director;
	}

	@Override
	public String toString() {
		return "MovieDto [movie_id=" + movie_id + ", movie_title=" + movie_title + ", date_released=" + date_released
				+ ", movie_running_time=" + movie_running_time + ", director=" + director + "]";
	}

	
	
	
}
