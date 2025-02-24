package com.gestionR.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.gestionR.models.AppelsDoffre;
@Repository
public interface AppelsDoffreRepository  extends MongoRepository<AppelsDoffre, String> {

	public AppelsDoffre findByIdoffre(String idoffre);
	
	
	
	
}
