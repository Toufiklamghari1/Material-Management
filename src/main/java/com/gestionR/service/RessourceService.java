package com.gestionR.service;

import java.util.List;

import com.gestionR.models.Ressource;

public interface RessourceService {
	List<Ressource> getRessourse();
	Ressource findByIdR(String id, List<Ressource> list);
}