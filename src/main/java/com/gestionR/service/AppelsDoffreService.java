package com.gestionR.service;

import java.util.List;

import com.gestionR.models.AppelsDoffre;
import com.gestionR.models.Panne;

public interface AppelsDoffreService {
	public void AjouterOffre(AppelsDoffre A);
	public List<AppelsDoffre> afficherOffres();
	public AppelsDoffre chercherAppelOffre(String idoffre);
	public void sauvegarderOffre(AppelsDoffre appelsDoffre);
	
	}
