package com.moi.cinema.service;

import java.util.stream.Stream;

import org.springframework.beans.factory.annotation.Autowired;

import com.moi.cinema.dao.CategorieRepository;
import com.moi.cinema.dao.CinemaRepository;
import com.moi.cinema.dao.FilmRepository;
import com.moi.cinema.dao.PlaceRepository;
import com.moi.cinema.dao.ProjectionRepository;
import com.moi.cinema.dao.SalleRepository;
import com.moi.cinema.dao.SceanceRepository;
import com.moi.cinema.dao.VilleRepository;
import com.moi.cinema.entities.Cinema;
import com.moi.cinema.entities.Place;
import com.moi.cinema.entities.Salle;
import com.moi.cinema.entities.Ville;

public class CinemaInitServiceImpl implements ICinemeaInitService{

	@Autowired
	private VilleRepository villeRepository;  //j'enregistre la bille j'ai besoin d'un objet sallerepository, autowired enregistre en auto 
	@Autowired
	private CinemaRepository cinemaRepository;
	@Autowired
	private SalleRepository salleRepository;
	@Autowired
	private PlaceRepository placeRepository;
	@Autowired
	private SceanceRepository sceanceeRepository;
	@Autowired
	private FilmRepository filmRepository;
	@Autowired
	private ProjectionRepository projectionRepository;
	@Autowired
	private CategorieRepository categorieRepository;
	
	@Override
	public void initVilles() {
		Stream.of("Paris", "Lyon,","Toulouse", "Marseille").forEach(nameVille-> {
			Ville ville = new Ville();
			ville.setName(nameVille);
			villeRepository.save(ville);
		});
		
	}

	@Override
	public void initCinema() {
		villeRepository.findAll().forEach(v-> {
			Stream.of("Megarama","Pathe", "UGC", "Gaumont").forEach(nameCinema->{
				Cinema cinema = new Cinema();
				cinema.setName(nameCinema);
				cinema.setNombreDeSalle(3+(int)(Math.random()*7)); //math.random x 7 nb alea entre 0 et  7 pour 3 à 10 salle
				cinema.setVille(v);
				//cinemaRepository.save(cinema);
						
			});
		});
		
	}

	@Override
	public void initSalles() {
		cinemaRepository.findAll().forEach(cinema-> {
			for (int i=0;i<cinema.getNombreDeSalle();i++) {
				Salle salle = new Salle();
				salle.setName("Salle"+(i+1));
				salle.setCinema(cinema);
				salle.setNombrePlace(20+(int)(Math.random()*50));
				salleRepository.save(salle);
			}
		});
		
	}

	@Override
	public void initPlace() {
		salleRepository.findAll().forEach(salle->{
			for(int i =0;i<salle.getNombrePlace();i++) {
				Place place = new Place();
				place.setNumero(i+1);
				place.setSalle(salle);
				placeRepository.save(place);
			}
		});
		
	}

	@Override
	public void initSceance() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void initCategorie() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void films() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void initProjections() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void initTickets() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void initfilms() {
		// TODO Auto-generated method stub
		
	}

}
