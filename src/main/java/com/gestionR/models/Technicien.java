package com.gestionR.models;

import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "Technicien")

public class Technicien extends User {


	public Technicien(String username , String email , String password){
		super(username, email, password);
	}
	
	
	public Technicien() {
		
	}
}