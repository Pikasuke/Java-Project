package com.moi.cine.web;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.print.DocFlavor.URL;
import javax.transaction.Transactional;
import javax.websocket.server.PathParam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.moi.cine.dao.FilmRepository;
import com.moi.cine.dao.TicketRepository;
import com.moi.cine.entities.Film;
import com.moi.cine.entities.Projection;
import com.moi.cine.entities.Ticket;

import javafx.scene.media.Media;
import javassist.expr.NewArray;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@RestController
@CrossOrigin("*")
public class CinemaRestController {
	
	
	/*********************************************
		
	Pour la création d'un Rest controler à la main on utilise instancie le repository et utilise les annotaion Mapping (@getMpping...)
	Néanmoins l'appel de l'objet appellera aussi la Collection categorie qui appellera lui mm le film et ainsi de suite = boucle infini.
	Pour resoudre il faut ajouter l'annotation @JsonProperty (access = Access.WRITE_ONLY) au dessus de l'attribut de la classe concernerné afin 
	d'ignorer la conversion en Json lors de l'appel URL. 
	
		@Autowired
		private FilmRepository filmRepository;
		@GetMapping("/listFilms")
		public List<Film> films(){
			return filmRepository.findAll();
		}
			public class Film {
				private Long id;
				@OneToMany(mappedBy = "film")
				@JsonProperty (access = Access.WRITE_ONLY)
				private Collection<Projection> projections;
			}
			public class Categorie {
				private Long id;
				@OneToMany(mappedBy = "categorie")
				@JsonProperty (access = Access.WRITE_ONLY)
				private Collection<Film> films;
			} 
		**************************************************************/
			//Par default produces = Json là on lui précise que c'est un string d'image
	@Autowired
	private FilmRepository filmRepository;
	@Autowired
	private TicketRepository ticketRepository;
		
	
	//Creer une methode pour transformer les immages en bytes
	@GetMapping(path = "/imageFilm/{id}", produces = MediaType.IMAGE_JPEG_VALUE) // le navigateur va comprendre que ce tableau de byte est une image
	public byte[] image(@PathVariable(name="id") Long id) throws Exception {
		Film f = filmRepository.findById(id).get();
		String photoName = f.getPhoto();
		File file = new File(System.getProperty("user.home")+"/cinema/images/"+photoName);//on va chercher le dossier de l'utilisteur pr stocker les photos
		Path path = Paths.get(file.toURI());
		return Files.readAllBytes(path);
	}
	
	@PostMapping("/payerTickets")
	@Transactional
	public List<Ticket> payerTicket(@RequestBody TicketForm ticketForm) { //RequestBody permet de recuperer les ticket dans le corps de la requette au format jSon
		List<Ticket> listTickets = new ArrayList<Ticket>();
		ticketForm.getTickets().forEach(idTicket-> {
			Ticket ticket = ticketRepository.findById(idTicket).get();
			ticket.setNomClient(ticketForm.getNomClient());
			ticket.setReservee(true);
			ticket.setCodePaiement(ticketForm.getCodePayment());
			ticketRepository.save(ticket);
			listTickets.add(ticket);
		});
		return listTickets; //Les tickets renvoie la place et la place la list de ticket -> boucle infini, il faut ajouter l'annotation @json write only sur la classe Place	
	}
}


@Data @NoArgsConstructor @AllArgsConstructor
class TicketForm{
	private String nomClient;
	private int codePayment;
	private List<Long> tickets=new ArrayList<Long>();
}
