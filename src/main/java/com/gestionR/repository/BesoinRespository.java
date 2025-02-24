package com.gestionR.repository;

import com.gestionR.models.Besoin;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BesoinRespository extends MongoRepository<Besoin, String> {
    @Query("{ 'enseigantid' : ?0 }")
    List<Besoin> findByPers(String perID);
}
