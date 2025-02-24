package com.gestionR.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;


import com.gestionR.models.ResponsableRessource;

@Repository
public interface ResponsableRessourceRepository extends MongoRepository<ResponsableRessource, String> {
	public ResponsableRessource findByEmail(String email);
}