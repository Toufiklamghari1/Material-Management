package com.gestionR.models;

import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "Administratif")

public class Administratif extends Personnel {

	
	public Administratif(String username, String email, String password){
		super(username, email, password);
	}

	public Administratif() {
		super();
		
	}

	
	
}