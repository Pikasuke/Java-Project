package com.moi.cinema;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.moi.cinema.service.ICinemeaInitService;

@SpringBootApplication
public class CinemaApplication implements CommandLineRunner{

	private ICinemeaInitService cinemeaInitService;
	
	public static void main(String[] args) {
		SpringApplication.run(CinemaApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		// TODO Auto-generated method stub
		cinemeaInitService.initVilles();
		cinemeaInitService.initCinema();
		cinemeaInitService.initSalles();
		cinemeaInitService.initPlace();
		cinemeaInitService.initSceance();
		cinemeaInitService.initCategorie();
		cinemeaInitService.initfilms();
		cinemeaInitService.initProjections();
		cinemeaInitService.initTickets();
		
	}

}
