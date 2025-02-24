package com.gestionR.serviceImplementation;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gestionR.models.Technicien;
import com.gestionR.repository.TechnicienRepository;
import com.gestionR.service.TechnicienService;

@Service
public class TechnicienServiceImplementation implements TechnicienService{
	@Autowired
	TechnicienRepository technicienRep;
	@Override
	public List<Technicien> getTechnicien() {
		List<Technicien> list =technicienRep.findAll();
		return list;
	}
}