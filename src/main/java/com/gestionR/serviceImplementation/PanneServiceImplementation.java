package com.gestionR.serviceImplementation;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gestionR.models.Panne;
import com.gestionR.repository.PanneRepository;
import com.gestionR.service.PanneService;

@Service
public class PanneServiceImplementation  implements PanneService{

	@Autowired
	PanneRepository panneRepository;
	
	@Override
	public void ajouterPanne(Panne panne) {
	
		panneRepository.save(panne);
		
	}

	@Override
	public List<Panne> ListerPannes() {
		
		return panneRepository.findAll();
	
	}

	public Panne findByIdPanne(String id) {
		Panne p = panneRepository.findByIdPanne(id);
		return p;
	}

}
