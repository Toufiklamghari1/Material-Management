package com.gestionR.service;

import java.util.List;
import java.util.Optional;

import com.gestionR.models.Panne;

public interface PanneService {

	
	public void ajouterPanne(Panne panne);
	public List<Panne> ListerPannes();
	public Panne findByIdPanne(String id);
	
}
