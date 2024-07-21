package com.expressMovie.entity;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;





@Entity
public class Director {
	
	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE)
	private Integer director_id;
	
	private String first_name;
	
	private String last_name;
	
	
	private String address;
	
	private String contact_number;
	
	private String email;
	
	@OneToMany(mappedBy = "director")
	private List<Movie> movieList;

	public Integer getDirector_id() {
		return director_id;
	}

	public void setDirector_id(Integer director_id) {
		this.director_id = director_id;
	}

	public String getFirst_name() {
		return first_name;
	}

	public void setFirst_name(String first_name) {
		this.first_name = first_name;
	}

	public String getLast_name() {
		return last_name;
	}

	public void setLast_name(String last_name) {
		this.last_name = last_name;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getContact_number() {
		return contact_number;
	}

	public void setContact_number(String contact_number) {
		this.contact_number = contact_number;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public List<Movie> getMovieList() {
		return movieList;
	}

	public void setMovieList(List<Movie> movieList) {
		this.movieList = movieList;
	}

	@Override
	public String toString() {
		return "Director [director_id=" + director_id + ", first_name=" + first_name + ", last_name=" + last_name
				+ ", address=" + address + ", contact_number=" + contact_number + ", email=" + email + ", movieList="
				+ movieList + "]";
	}

	
}
