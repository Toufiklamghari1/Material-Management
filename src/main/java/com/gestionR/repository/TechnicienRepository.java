package com.gestionR.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.gestionR.models.Enseignant;
import com.gestionR.models.Technicien;


@Repository
public interface TechnicienRepository extends MongoRepository<Technicien, String>{
	Technicien findByEmail(String email);
}