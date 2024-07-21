package com.expressMovie.entity;

import java.time.LocalDate;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.format.annotation.DateTimeFormat;


@Entity
public class Movie {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private Integer movie_id;
	
	private String movie_title;
	
	@DateTimeFormat(pattern="yyyy-MM-dd")
	private Date date_released;
	
	//@Temporal(TemporalType.TIME)
	private Date movie_running_time;
	
	@ManyToOne
	@JoinColumn(name="Director_Id", referencedColumnName = "director_id")
	private Director director;

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
	public Date getDate_released() {
		return date_released;
	}

	public void setDate_released(Date date_released) {
		this.date_released = date_released;
	}

	public Date getMovie_running_time() {
		return movie_running_time;
	}

	public void setMovie_running_time(Date movie_running_time) {
		this.movie_running_time = movie_running_time;
	}

	public Director getDirector() {
		return director;
	}

	public void setDirector(Director director) {
		this.director = director;
	}

	@Override
	public String toString() {
		return "Movie [movie_id=" + movie_id + ", movie_title=" + movie_title + ", date_released=" + date_released
				+ ", movie_running_time=" + movie_running_time + ", director=" + director + "]";
	}
	
	
}
