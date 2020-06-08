package com.moi.cinema.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.moi.cinema.entities.Cinema;
import com.moi.cinema.entities.Ville;
@RepositoryRestResource
public interface VilleRepository extends JpaRepository<Ville, Long>{

}
