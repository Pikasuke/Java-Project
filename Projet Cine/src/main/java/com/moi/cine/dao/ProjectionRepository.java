package com.moi.cine.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.moi.cine.entities.Cinema;
import com.moi.cine.entities.Projection;

@RepositoryRestResource
public interface ProjectionRepository extends JpaRepository<Projection, Long>{

}
