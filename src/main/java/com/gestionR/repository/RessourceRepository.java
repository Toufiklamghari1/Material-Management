package com.gestionR.repository;


import com.gestionR.models.Ressource;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RessourceRepository extends MongoRepository<Ressource , String> {
}
