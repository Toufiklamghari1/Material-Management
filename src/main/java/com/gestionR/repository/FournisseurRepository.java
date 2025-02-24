package com.gestionR.repository;


import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.gestionR.models.Fournisseur;


@Repository
public interface FournisseurRepository extends MongoRepository<Fournisseur, String>{

	public Fournisseur findByEmail(String email);
	public Fournisseur  findByIdFourniseur(String idFourniseur);
	
}
