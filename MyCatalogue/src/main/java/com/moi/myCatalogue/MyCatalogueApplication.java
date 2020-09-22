package com.moi.myCatalogue;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import com.moi.myCatalogue.DAO.ProduitRepository;
import com.moi.myCatalogue.entities.Produit;

@SpringBootApplication
public class MyCatalogueApplication implements CommandLineRunner{

	@Autowired
	private ProduitRepository produitRepository;
	
	public static void main(String[] args) {
		SpringApplication.run(MyCatalogueApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
//		produitRepository.save(new Produit(null, "ordi HP 500", 6500, 54));
//		produitRepository.save(new Produit(null, "imprimante", 4500, 67));
//		produitRepository.save(new Produit(null, "Telephone", 700, 5));
		
		Page<Produit> produits = produitRepository.findByDesignationContains("H",PageRequest.of(0, 2));
		System.out.println(produits.getSize());
		System.out.println(produits.getTotalElements());
		System.out.println(produits.getTotalPages());
		produits.getContent().forEach(p-> {
			System.out.println(p.toString());
		});
		
		System.out.println("   #######################################   ");
		Page<Produit> produits2 = produitRepository.chercher("%h%", 70, PageRequest.of(0, 2));
		System.out.println(produits2.getSize());
		System.out.println(produits2.getTotalElements());
		System.out.println(produits2.getTotalPages());
		produits2.getContent().forEach(p-> {
			System.out.println(p.toString());
		});
		
		
		
	}

}
