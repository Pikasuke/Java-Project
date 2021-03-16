package com.moi.cine.entities;

import java.util.Collection;
import java.util.Date;

import org.springframework.data.rest.core.config.Projection;

@Projection(name = "projFilm", types = {com.moi.cine.entities.Projection.class})
public interface ProjectionFilm {
	public Long getId();
	public Date getDateDeProjection();
	public double getPrix();
	public Salle getSalle();
	public Film getFilm();
	public Collection<Ticket> getTickets();
	public Sceance getSceance();

}
