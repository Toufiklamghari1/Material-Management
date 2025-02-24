package com.gestionR.models;

import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "ResponsableRessource")

public class ResponsableRessource extends User {
    
	public ResponsableRessource(String username, String email, String password) {
		super(username, email, password);
		
	}

	
}