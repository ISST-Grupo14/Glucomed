package es.isst.glucomed.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Medico implements Serializable{

	private static final long serialVersionUID = 01L;
	
	@Id
	private String email;

	public Medico (String email) {
		this.email              = email;
	}
	
	public String getEmail() {
		return email;
	}

	public void setEmail(String nombre) {
		this.email = nombre;
	}

}
