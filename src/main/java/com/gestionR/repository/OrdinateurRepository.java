package com.gestionR.repository;

import com.gestionR.models.Demande;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import com.gestionR.models.Ordinateure;

import java.util.List;

@Repository
public interface OrdinateurRepository extends MongoRepository<Ordinateure, String> {

    @Query("{ 'isAffected' : ? }")
    List<Ordinateure> findByAffected(boolean affected);
}
