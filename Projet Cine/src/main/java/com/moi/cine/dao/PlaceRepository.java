package com.moi.cine.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.moi.cine.entities.Cinema;
import com.moi.cine.entities.Place;

@RepositoryRestResource
public interface PlaceRepository extends JpaRepository<Place, Long>{

}
