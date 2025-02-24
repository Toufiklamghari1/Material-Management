package com.gestionR.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.gestionR.models.Panne;


@Repository
public interface PanneRepository extends MongoRepository<Panne, String> {
	public Panne findByIdPanne(String idPanne);
}
