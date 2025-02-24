package com.gestionR.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.gestionR.models.Enseignant;

@Repository
public interface EnseignantRepository extends MongoRepository<Enseignant, String> {
	public Enseignant findByEmail(String email);
}