package com.gestionR.models;

import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection="Personnel")
public abstract class Personnel extends User {

	public Personnel(String username , String email , String password){
		super(username, email, password);
	}

	public Personnel() {
		
	}
	
}