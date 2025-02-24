package com.gestionR.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.gestionR.models.constat;

@Repository
public interface ConstatRepository extends MongoRepository<constat, String> {
	public constat findByIdconstat(String id);
}
