package com.gestionR.service;

import java.util.List;

import com.gestionR.models.Soumission;


public interface SoumissionService {
	
	public Soumission AjouterSoumission(Soumission soumission);
	public List<Soumission> afficherSoumissions();
	public List<Soumission> afficherSoumissionOffre(String id);
	public List<Soumission> soumissionAcceptee(String idFour);
	public Soumission chercherSoumissionId(String id_soum);

}
