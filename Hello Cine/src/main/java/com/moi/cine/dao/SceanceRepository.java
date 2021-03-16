package com.moi.cine.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.web.bind.annotation.CrossOrigin;

import com.moi.cine.entities.Cinema;
import com.moi.cine.entities.Sceance;

@RepositoryRestResource
@CrossOrigin("*")
public interface SceanceRepository extends JpaRepository<Sceance, Long>{

}
