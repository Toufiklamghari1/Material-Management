package com.gestionR.repository;

import com.gestionR.models.Besoin;

import com.gestionR.models.Demande;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DemandeRep extends MongoRepository<Demande,String> {

    @Query("{ 'chef' : ?0 }")
    List<Demande> findByChef(String chefID);

    @Query("{ 'num' : ?0 }")
    List<Demande> findByNum(String num);

    @Query("{ 'state' : ?0 }")
    List findByState(String state);
}