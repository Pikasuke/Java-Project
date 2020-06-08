package com.moi.cinema.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.moi.cinema.entities.Cinema;
import com.moi.cinema.entities.Sceance;
@RepositoryRestResource
public interface SceanceRepository extends JpaRepository<Sceance, Long>{

}
