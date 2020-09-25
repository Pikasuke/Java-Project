package com.moi.cine.service;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Random;
import java.util.stream.Stream;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.moi.cine.dao.CategorieRepository;
import com.moi.cine.dao.CinemaRepository;
import com.moi.cine.dao.FilmRepository;
import com.moi.cine.dao.PlaceRepository;
import com.moi.cine.dao.ProjectionRepository;
import com.moi.cine.dao.SalleRepository;
import com.moi.cine.dao.SceanceRepository;
import com.moi.cine.dao.TicketRepository;
import com.moi.cine.dao.VilleRepository;
import com.moi.cine.entities.Categorie;
import com.moi.cine.entities.Cinema;
import com.moi.cine.entities.Film;
import com.moi.cine.entities.Place;
import com.moi.cine.entities.Projection;
import com.moi.cine.entities.Salle;
import com.moi.cine.entities.Sceance;
import com.moi.cine.entities.Ticket;
import com.moi.cine.entities.Ville;

@Service
@Transactional
public class CinemaInitServiceImpl implements ICinemaInitService{
	
	@Autowired
	private VilleRepository villeRepository;
	@Autowired
	private CinemaRepository cinemaRepository;
	@Autowired
	private SalleRepository salleRepository;
	@Autowired
	private PlaceRepository placeRepository;
	@Autowired
	private SceanceRepository sceanceRepository;
	@Autowired
	private FilmRepository filmRepository;
	@Autowired
	private ProjectionRepository projectionRepository;
	@Autowired
	private CategorieRepository categorieRepository;
	@Autowired
	private TicketRepository ticketRepository;
	
	
	
	@Override
	public void initVilles() {
		Stream.of("Paris", "Lyon", "Nice", "Toulouse", "Marseille").forEach(nameVille->{
			Ville ville = new Ville();
			ville.setName(nameVille);
			villeRepository.save(ville);
		});
		
	}

	@Override
	public void initCinemas() {
		
		villeRepository.findAll().forEach(ville-> {
			Stream.of("UGC", "Gaumont", "Pathe", "Megarama", "Imax").forEach(nameCinema -> {
				Cinema cinema = new Cinema();
				cinema.setName(nameCinema);
				cinema.setNombreDeSalle(1+(int)(Math.random()*3)); //3 + 5
				cinema.setVille(ville);
				cinemaRepository.save(cinema);
			});
		});
		
	}

	@Override
	public void initSalles() {
		cinemaRepository.findAll().forEach(cinema-> {
			for (int i = 0; i < cinema.getNombreDeSalle(); i++) {
				Salle salle = new Salle();
				salle.setName("Salle "+i+1);
				salle.setCinema(cinema);
				salle.setNombrePlace(1+(int)(Math.random()*1)); // 15+35
				salleRepository.save(salle);
			}
		});
	}

	@Override
	public void initPlaces() {
		salleRepository.findAll().forEach(salle->{
			for (int j = 0; j < salle.getNombrePlace(); j++) {
				Place place = new Place();
				place.setNumero(j+1);
				place.setSalle(salle);
				placeRepository.save(place);
			}
		
			
		});
		
	}

	@Override
	public void initSceances() {
		DateFormat df=new SimpleDateFormat("HH:mm");
		Stream.of("12:00", "15:00", "18:00", "21:00").forEach(horaire-> {
			Sceance sceance = new Sceance();
			try {
				sceance.setHeureDebut(df.parse(horaire));
				sceanceRepository.save(sceance);
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		});
		
	}

	@Override
	public void initCategories() {
		Stream.of("Action", "Science Fiction", "Drame", "Romantique").forEach(cat->{
			Categorie categorie = new Categorie();
			categorie.setName(cat);
			categorieRepository.save(categorie);
		});
		
	}

	@Override
	public void initFilms() {
		double[] durees = new double[] {1.5, 2, 2.5, 3};
		List<Categorie> categories = categorieRepository.findAll();
		Stream.of("Matrix", "Equilibrium", "Bad Boys", "La ligne Verte", "Inception").forEach(titre->{
			Film film = new Film();
			film.setTitre(titre);
			film.setDuree(durees[new Random().nextInt(durees.length)]);
			film.setPhoto(titre.replaceAll(" ", "")+".jpg");
			film.setCategorie(categories.get(new Random().nextInt(categories.size())));
			filmRepository.save(film);
		});
		
	}

	@Override
	public void initProjections() {
		double[] tabPrix = new double[] {4, 6.5, 7.5, 9, 12 };
		villeRepository.findAll().forEach(ville->{
			ville.getCinemas().forEach(cinema->{
				cinema.getSalles().forEach(salle->{
					filmRepository.findAll().forEach(film-> {
						sceanceRepository.findAll().forEach(sceance->{
							Projection projection = new Projection();
							projection.setDateDeProjection(new Date());
							projection.setFilm(film);
							projection.setPrix(tabPrix[new Random().nextInt(tabPrix.length)]);
							projection.setSalle(salle);
							projection.setSceance(sceance);
							projectionRepository.save(projection);
						});
					});
				});
			});
		});
		
	}

	@Override
	public void initTickets() {
		projectionRepository.findAll().forEach(proj->{
			proj.getSalle().getPlaces().forEach(place->{
				Ticket ticket = new Ticket();
				ticket.setPlace(place);
				ticket.setPrix(proj.getPrix());
				ticket.setProjection(proj);
				ticket.setReservee(false);
				ticketRepository.save(ticket);
			});
			
		});
		
	}

}
