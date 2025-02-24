package com.gestionR.service;

import java.util.List;

import com.gestionR.models.Affectation;
import com.gestionR.models.Personnel;
import com.gestionR.models.Ressource;
import com.gestionR.models.User;


public interface AffectationService {
	public List<Affectation> getRessources(Personnel name);
}
