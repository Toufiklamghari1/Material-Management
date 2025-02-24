package com.gestionR.serviceImplementation;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gestionR.models.Imprimante;
import com.gestionR.models.Ordinateure;
import com.gestionR.models.Ressource;
import com.gestionR.repository.ImprimanteRepository;
import com.gestionR.repository.OrdinateurRepository;
import com.gestionR.repository.RessourceRepository;
import com.gestionR.service.RessourceService;

@Service
public class RessourceServiceImp implements RessourceService {
	@Autowired
	OrdinateurRepository ordinateurRep;
	@Autowired
	ImprimanteRepository imprimanteRep;
	@Override
	public List<Ressource> getRessourse() {
		List<Ressource> ress= new ArrayList<Ressource>();
		List<Ordinateure> ord =new ArrayList<Ordinateure>();
		ord = ordinateurRep.findAll();
		
		List<Imprimante> impr = new ArrayList<Imprimante>();
		impr = imprimanteRep.findAll();
		
		ress.addAll(impr);
		ress.addAll(ord);
		
		
		return (List<Ressource>) ress;
	}
	@Override
	public Ressource findByIdR(String id,List<Ressource> list) {
		
		for(Ressource ress : list) {
			
			if(ress.getId().equals(id)) {
				
				return ress;
			}
		}
		
		return null;
	}


	
}
