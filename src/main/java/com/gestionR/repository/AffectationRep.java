package com.gestionR.repository;

import com.gestionR.models.Affectation;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AffectationRep extends MongoRepository<Affectation,String> {
}
