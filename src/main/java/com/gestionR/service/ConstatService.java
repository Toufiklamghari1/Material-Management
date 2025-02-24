package com.gestionR.service;

import java.util.List;

import com.gestionR.models.constat;

public interface ConstatService {
	public void ajouterConstat(constat con);
	public List<constat> listerConstat();
	public constat findByIdconstat(String id);

}
