package com.expressMovie;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.ComponentScan;

import com.expressMovie.dto.DirectorDto;
import com.expressMovie.dto.MovieDto;
import com.expressMovie.entity.Director;
import com.expressMovie.entity.Movie;
import com.expressMovie.entity.Movie_Director;
import com.expressMovie.exception.Director_DetailsException;
import com.expressMovie.exception.Movie_DetailsException;
import com.expressMovie.repository.DirectorRepository;
import com.expressMovie.repository.MovieRepository;
import com.expressMovie.repository.Movie_Director_Repository;
import com.expressMovie.service.DirectorService;
import com.expressMovie.service.DirectorServiceImpl;

import javassist.Loader.Simple;


@SpringBootApplication
public class ExpressMovieApplication {

	public static void main(String[] args) throws Exception {
		//SpringApplication.run(ExpressMovieApplication.class, args);
		ApplicationContext context= SpringApplication.run(ExpressMovieApplication.class, args);
		System.out.println("Running main method.......2");
		DirectorService dir_service=  context.getBean(DirectorService.class);
		MovieRepository movie_service =  context.getBean(MovieRepository.class);
		Movie_Director_Repository md_repo = context.getBean(Movie_Director_Repository.class);
		Scanner sc = new Scanner(System.in);
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		SimpleDateFormat df_time = new SimpleDateFormat("yyyy-MM-dd HH:MM:SS");
		while(true)
		{
			System.out.println("Pleaes select option");
			
			System.out.println("1. Add Movie");
			System.out.println("2. Add Director");
			System.out.println("3. Search Movie");
			System.out.println("4. Search Director");
			System.out.println("5. View Movies");
			System.out.println("6. Update Movie");
			System.out.println("7. Update Director");
			System.out.println("8. Remove Movie");
			
			int option = sc.nextInt();
			
			if(option == 1)
			{
				System.out.println("Enter movie details");
				System.out.println("Enter movie title");
				String title = sc.next();
				System.out.println("Enter release date (yyyy-MM-dd)");
				String release_date_str = sc.next();
				System.out.println("Enter running time (HH:MM:SS)");
				String time = sc.next();
				System.out.println("Enter director");
				int director = sc.nextInt();
				Director d = new Director();
				d.setDirector_id(director);
				
				Movie movie = new Movie();
				Date realease_date = df.parse(release_date_str);
				movie.setMovie_title(title);
				movie.setDate_released(realease_date);
				movie.setMovie_running_time(new Date());
				movie.setDirector(d);
				Movie m = movie_service.save(movie);
				
				Movie_Director md = new Movie_Director();
				
				md.setDirector_id(director);
				md.setMovie_id(m.getMovie_id());
				
				md_repo.save(md);
				
				System.out.println("Movie added successfully");
				
			}
			else if(option == 2)
			{
				System.out.println("Please enter the details");
				System.out.println("Enter first name");
				String first_name = sc.next();
				System.out.println("Enter last name");
				String last_name = sc.next();
				System.out.println("Enter contact number");
				String contact = sc.next();
				System.out.println("Enter address");
				String address = sc.next();
				System.out.println("Enter email");
				String email = sc.next();
				
				Director director = new Director();
				director.setFirst_name(first_name);
				director.setLast_name(last_name);
				director.setContact_number(contact);
				director.setAddress(address);
				director.setEmail(email);
				
				Director dr = dir_service.saveDirector(director);
				
				System.out.println("Director added successfully");
				
			}
			else if(option == 3)
			{
				System.out.println("Choose option");
				System.out.println("1. Search Movie by title");
				System.out.println("2. search movie by director");
				int opt  = sc.nextInt();
				if(opt == 1)
				{
					System.out.println("Enter title");
					String title1 = sc.next();
					List<Movie> movie = movie_service.findByTitle(title1);
					if(movie.size() > 0)
					{
					System.out.println("MOVIE_TITLE\t\t DATE_RELEASED\t\t MOVIE_RUNNING_TIME\t\t");
					for(int j=0;j<movie.size();j++)
					{
					System.out.println(movie.get(j).getMovie_title()+"\t\t"+movie.get(j).getDate_released()+"\t\t"+movie.get(j).getMovie_running_time());	
					}
					}
					else
					{
						try
						{
						throw new Movie_DetailsException("Movie not found with given title");
						}
						catch(Movie_DetailsException e)
						{
							System.out.println(e.getMessage());
						}	
					}
					
				}
				else if(opt == 2)
				{
				System.out.println("Enter director name");
				System.out.println("Enter first name");
				String dir_first_name = sc.next();
				System.out.println("Enter last name");
				String dir_last_name = sc.next();
				List<Movie> movie = movie_service.findByDirector(dir_first_name,dir_last_name);
				if(movie.size() > 0)
				{
				System.out.println("MOVIE_TITLE\t\t DATE_RELEASED\t\t MOVIE_RUNNING_TIME\t\t");
				for(int j=0;j<movie.size();j++)
				{
				System.out.println(movie.get(j).getMovie_title()+"\t\t"+movie.get(j).getDate_released()+"\t\t"+movie.get(j).getMovie_running_time());	
				}
				}
				else
				{
					try
					{
					throw new Movie_DetailsException("Movie not found with given director");
					}
					catch(Movie_DetailsException e)
					{
						System.out.println(e.getMessage());
					}	
				}
				
				}
				else
				{
				System.out.println("Invalid option");	
				}
			}
			else if(option == 4)
			{
				System.out.println("Enter movie title");
				String title = sc.next();
				List<Director> dir_list = dir_service.getDirectorDetails(title);
				if(dir_list.size() > 0)
				{
					System.out.println("First Name\t\t\tLast Name\t\t\tContact Number\t\t\tEmail\t\t\tAddress");
					for(int j=0;j<dir_list.size();j++)
					{
						System.out.println(dir_list.get(j).getFirst_name()+"\t\t\t"+dir_list.get(j).getLast_name()+"\t\t\t"+
					dir_list.get(j).getContact_number()+"\t\t\t"+dir_list.get(j).getEmail()+"\t\t\t"+dir_list.get(j).getAddress());
					}
				}
				else
				{
					try
					{
					throw new Director_DetailsException("Invalid movie title");
					}
					catch(Director_DetailsException e)
					{
						System.out.println(e.getMessage());
					}	
				}
				
			}
			else if(option == 5)
			{
				
				List<Movie> movie = movie_service.getAllMovies();
				if(movie.size() > 0)
				{
				System.out.println("MOVIE_TITLE\t\t DATE_RELEASED\t\t MOVIE_RUNNING_TIME\t\t");
				for(int j=0;j<movie.size();j++)
				{
				System.out.println(movie.get(j).getMovie_title()+"\t\t"+movie.get(j).getDate_released()+"\t\t"+movie.get(j).getMovie_running_time());	
				}
				}
				else
				{
					try
					{
					throw new Movie_DetailsException("Movie not found with given director");
					}
					catch(Movie_DetailsException e)
					{
						System.out.println(e.getMessage());
					}	
				}
				
				
			}
			else if(option == 6)
			{
				System.out.println("Enter movie title");
				String title = sc.next();
				System.out.println("Enter release date(yyyy-MM-dd) to update");
				String release_date = sc.next();
				Date d = df.parse(release_date);
				List<Movie> movie = movie_service.findByTitle(title);
				if(movie.size() > 0)
				{
					Movie m = movie.get(0);
					m.setDate_released(d);
					Movie m1 = movie_service.save(m);
					System.out.println("Movie updated successfully");
					System.out.println("MOVIE_TITLE\t\t DATE_RELEASED\t\t MOVIE_RUNNING_TIME\t\t");
					System.out.println(m1.getMovie_title()+"\t\t"+m1.getDate_released()+"\t\t"+m1.getMovie_running_time());
				}
				else
				{
					try
					{
					throw new Movie_DetailsException("Movie not found with given title");
					}
					catch(Movie_DetailsException e)
					{
						System.out.println(e.getMessage());
					}
				}
			}
			else if(option == 7)
			{
				System.out.println("Enter first name");
				String dir_first_name = sc.next();
				System.out.println("Enter last name");
				String dir_last_name = sc.next();
				List<Director> dir_list = dir_service.getDirectorDetails(dir_first_name,dir_last_name);
				if(dir_list.size() > 0)
				{
					System.out.println("Enter address");
					String add = sc.next();
					
					System.out.println("Enter contact number");
					String contact = sc.next();
					
					System.out.println("Enter email");
					String email = sc.next();
					
					Director d = dir_list.get(0);
					d.setAddress(add);
					d.setContact_number(contact);
					d.setEmail(email);
					
					Director dr = dir_service.saveDirector(d);
					
					System.out.println("Details updated successfully");
					System.out.println("First Name\t\t\tLast Name\t\t\tContact Number\t\t\tEmail\t\t\tAddress");
					System.out.println(dr.getFirst_name()+"\t\t\t"+dr.getLast_name()+"\t\t\t"+dr.getContact_number()+"\t\t\t"+
					dr.getEmail()+"\t\t\t"+dr.getAddress());
					
				}
				else
				{
					try
					{
					throw new Movie_DetailsException("Invalid details");
					}
					catch(Movie_DetailsException e)
					{
						System.out.println(e.getMessage());
					}
				}
			}
			else if(option == 8)
			{
				System.out.println("Enter title");
				String title1 = sc.next();
				List<Movie> movie = movie_service.findByTitle(title1);
				if(movie.size() > 0)
				{
					int movie_id = movie.get(0).getMovie_id();
					movie_service.deleteById(movie_id);
					
					Movie_Director md = md_repo.getRefernceData(movie_id);
					
					md_repo.deleteById(md.getMovie_director_id());
					
					//md_repo.deleteByMovieId(movie_id);
					
					System.out.println("Movie removed successfully");
					
					
				}
				else
				{
					try
					{
					throw new Director_DetailsException("Invalid movie title");
					}
					catch(Director_DetailsException e)
					{
						System.out.println(e.getMessage());
					}
				}
			}
			else
			{
				try
				{
				throw new Director_DetailsException("Invalid Option");
				}
				catch(Director_DetailsException e)
				{
					System.out.println(e.getMessage());
				}
			}
			
		}
		
	}

}
