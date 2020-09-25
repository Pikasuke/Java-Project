package com.moi.cine;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.moi.cine.service.ICinemaInitService;

@SpringBootApplication
public class CineApplication implements CommandLineRunner {

	@Autowired
	private ICinemaInitService cinemaInitService;
	
	public static void main(String[] args) {
		SpringApplication.run(CineApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		cinemaInitService.initVilles();
		cinemaInitService.initCinemas();
		cinemaInitService.initSalles();
		cinemaInitService.initPlaces();
		cinemaInitService.initSceances();
		cinemaInitService.initCategories();
		cinemaInitService.initFilms();
		cinemaInitService.initProjections();
		cinemaInitService.initTickets();
		
	}
	
	

}
