package com.moi.cine.entities;

import java.util.Collection;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
@Entity
@Data @AllArgsConstructor @NoArgsConstructor @ToString
public class Film {
	
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Column(length = 150)
	private String titre;
	private double duree;
	@Column(length = 75)
	private String realisateur;
	private String description;
	private String photo;
	private Date date;
	@OneToMany(mappedBy = "film")
	@JsonProperty(access = Access.WRITE_ONLY) // En cas de controleur manuel Permets lors de la serialization de ne pas prendre en compte la liste des projection afin d'éviter le soucis de boucle infini
	private Collection<Projection> projections;
	@ManyToOne
	private Categorie categorie;

}
