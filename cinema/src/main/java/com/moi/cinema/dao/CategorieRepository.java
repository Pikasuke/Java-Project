package com.moi.cinema.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.moi.cinema.entities.Categorie;
import com.moi.cinema.entities.Cinema;
@RepositoryRestResource
public interface CategorieRepository extends JpaRepository<Categorie, Long>{

}
