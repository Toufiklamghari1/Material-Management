package com.gestionR.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.gestionR.models.Administratif;
import com.gestionR.models.Departement;

public interface DepartementRepository extends MongoRepository<Departement, String>{
}