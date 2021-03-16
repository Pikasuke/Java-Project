package com.moi.cine.service;

public interface ICinemaInitService {
	// Interface qui défini les methodes d'initialisations des différentes entitées pour populer la BDD à l'execution de l'appli 
	public void initVilles();
	public void initCinemas();
	public void initSalles();
	public void initPlaces();
	public void initSceances();
	public void initCategories();
	public void initFilms();
	public void initProjections();
	public void initTickets();
	

}
