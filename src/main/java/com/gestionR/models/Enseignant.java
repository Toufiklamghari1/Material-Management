package com.gestionR.models;


import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "Enseignant")
public class Enseignant extends Personnel {
	private String Lab;

	public Enseignant() {
		super();
	}

	public Enseignant(String username, String email, String password) {
		super(username, email, password);
	}

	public Enseignant(String username , String email , String password , String Lab){
			super(username, email, password);
			this.Lab=Lab;
	}

	public Enseignant(String lab) {
		super();
		Lab = lab;
	}

	public String getLab() {
		return Lab;
	}

	public void setLab(String lab) {
		Lab = lab;
	}
}