package com.moi.cine;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.rest.core.config.RepositoryRestConfiguration;

import com.moi.cine.entities.Categorie;
import com.moi.cine.entities.Cinema;
import com.moi.cine.entities.Film;
import com.moi.cine.entities.Place;
import com.moi.cine.entities.Projection;
import com.moi.cine.entities.Salle;
import com.moi.cine.entities.Sceance;
import com.moi.cine.entities.Ticket;
import com.moi.cine.entities.Ville;
import com.moi.cine.service.ICinemaInitService;

@SpringBootApplication
public class CineApplication implements CommandLineRunner {

	@Autowired
	private ICinemaInitService cinemaInitService;
	
	@Autowired
	private RepositoryRestConfiguration restConfiguration; //permet de configurer l'affichage des services rest
	
	public static void main(String[] args) {
		SpringApplication.run(CineApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		restConfiguration.exposeIdsFor(Ville.class); //permet d'afficher les id de la class donnés
		cinemaInitService.initVilles();
		restConfiguration.exposeIdsFor(Cinema.class);
		cinemaInitService.initCinemas();
		restConfiguration.exposeIdsFor(Salle.class);
		cinemaInitService.initSalles();
		restConfiguration.exposeIdsFor(Place.class);
		cinemaInitService.initPlaces();
		restConfiguration.exposeIdsFor(Sceance.class);
		cinemaInitService.initSceances();
		restConfiguration.exposeIdsFor(Categorie.class);
		cinemaInitService.initCategories();
		restConfiguration.exposeIdsFor(Film.class);	
		cinemaInitService.initFilms(); 
		restConfiguration.exposeIdsFor(Projection.class);
		cinemaInitService.initProjections();
		restConfiguration.exposeIdsFor(Ticket.class);
		cinemaInitService.initTickets();
		
	}
	
	

}
