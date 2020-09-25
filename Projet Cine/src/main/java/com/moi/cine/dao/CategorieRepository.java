package com.moi.cine.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.moi.cine.entities.Categorie;
import com.moi.cine.entities.Cinema;

@RepositoryRestResource
public interface CategorieRepository extends JpaRepository<Categorie, Long>{

}
