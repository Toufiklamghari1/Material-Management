package com.gestionR.service;

import java.util.List;
import java.util.Optional;

import com.gestionR.models.Panne;

public interface PanneService {
	 void ajouterPanne(Panne panne);
	 List<Panne> ListerPannes();
	 Panne findByIdPanne(String id);

}