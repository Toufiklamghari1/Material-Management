package com.gestionR.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import org.springframework.stereotype.Repository;

import com.gestionR.models.Administratif;


@Repository
public interface AdministratifRepository extends MongoRepository<Administratif, String>{

	public Administratif findByEmail(String email);

}
