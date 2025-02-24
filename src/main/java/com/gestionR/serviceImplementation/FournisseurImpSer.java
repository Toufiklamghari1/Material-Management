package com.gestionR.serviceImplementation;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gestionR.models.Fournisseur;
import com.gestionR.repository.FournisseurRepository;
import com.gestionR.service.FournisseurService;
@Service
public class FournisseurImpSer implements FournisseurService {
	@Autowired
	FournisseurRepository fournisseurRep;
	@Override
	public List<String> getFounisseurUsername() {
		List<Fournisseur> fournisseur = fournisseurRep.findAll();
		List<String> userName = new ArrayList<String>();
		for(int i=0;i<fournisseur.size();i++) {
			userName.add(fournisseur.get(i).getUsername());
		}
		return userName;
	}

}