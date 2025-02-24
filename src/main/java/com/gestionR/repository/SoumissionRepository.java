package com.gestionR.repository;


import java.util.List;
import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import com.gestionR.models.Soumission;

@Repository
public interface SoumissionRepository extends MongoRepository<Soumission, String> {
	
	
	public List<Soumission> findByIdoffre(String idoffre);
    public Soumission findBySoum(String id_soum);
    public List<Soumission> findByFournisseurAplicants(String fournisseurAplicants);
    
	
	
}
