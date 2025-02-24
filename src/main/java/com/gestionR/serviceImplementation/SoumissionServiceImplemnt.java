package com.gestionR.serviceImplementation;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.gestionR.models.Soumission;
import com.gestionR.repository.SoumissionRepository;
import com.gestionR.service.SoumissionService;

@Service
public class SoumissionServiceImplemnt implements SoumissionService{

	@Autowired
	SoumissionRepository soumR;
	
	@Override
	public Soumission AjouterSoumission(Soumission soumission) {
		return soumR.save(soumission);
		
	}

	@Override
	public List<Soumission> afficherSoumissions() {
		return soumR.findAll();
	}

	@Override
	public List<Soumission> afficherSoumissionOffre(String idoffre) {
		
		return soumR.findByIdoffre(idoffre);
	}

	@Override
	public Soumission chercherSoumissionId(String id_soum) {
		 
		return soumR.findBySoum(id_soum);
	}

	@Override
	public List<Soumission> soumissionAcceptee(String idFour) {
		
		return soumR.findByFournisseurAplicants(idFour);
	}

	
	

}
