package com.gestionR.repository;

import com.gestionR.models.Demande;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import com.gestionR.models.Imprimante;

import java.util.List;

@Repository
public interface ImprimanteRepository extends MongoRepository<Imprimante, String> {
    @Query("{ 'isAffected' : ?0 }")
    List<Imprimante> findByAffected(boolean affected);
}