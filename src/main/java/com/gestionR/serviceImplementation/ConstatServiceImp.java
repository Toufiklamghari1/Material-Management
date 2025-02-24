package com.gestionR.serviceImplementation;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gestionR.models.constat;
import com.gestionR.repository.ConstatRepository;
import com.gestionR.service.ConstatService;
@Service
public class ConstatServiceImp implements ConstatService{
	@Autowired
	ConstatRepository constatRep;
	@Override
	public void ajouterConstat(constat con) {
		constatRep.save(con);
		
	}
	
	@Override
	public List<constat> listerConstat() {
		List<constat> con = new ArrayList<constat>();
		con = constatRep.findAll();
		return con;
	}
	
	public constat findByIdconstat(String id) {
		constat con=constatRep.findByIdconstat(id);
		return con;
	}

}
