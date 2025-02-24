package com.gestionR.models;

import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection="Notification")
public class Notification {
		private String idDerstination;
		private String name;
		private String message;
		private String idRessource;

		public String getIdDerstination() {
			return idDerstination;
		}
		public void setIdDerstination(String idDerstination) {
			this.idDerstination = idDerstination;
		}
		public String getName() {
			return name;
		}
		public void setName(String name) {
			this.name = name;
		}
		public String getMessage() {
			return message;
		}
		public void setMessage(String message) {
			this.message = message;
		}
		public String getIdRessource() {
			return idRessource;
		}
		public void setIdRessource(String idRessource) {
			this.idRessource = idRessource;
		}


}