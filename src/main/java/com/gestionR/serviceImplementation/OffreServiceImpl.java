package com.gestionR.serviceImplementation;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gestionR.models.AppelsDoffre;
import com.gestionR.repository.AppelsDoffreRepository;
import com.gestionR.service.AppelsDoffreService;

@Service
public class OffreServiceImpl implements AppelsDoffreService{
		
	@Autowired
	private AppelsDoffreRepository RepOffre;
	
	@Override
	public void AjouterOffre(AppelsDoffre A) {
		RepOffre.save(A);
	}

	@Override
	public List<AppelsDoffre> afficherOffres() {
		return RepOffre.findAll();
		
	}

	@Override
	public AppelsDoffre chercherAppelOffre(String idoffre) {
		return RepOffre.findByIdoffre(idoffre);
		
	}

	@Override
	public void sauvegarderOffre(AppelsDoffre appelsDoffre) {
		RepOffre.save(appelsDoffre);
		
	}
	
	
	
}
