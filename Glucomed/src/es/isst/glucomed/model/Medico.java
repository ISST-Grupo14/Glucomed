package es.isst.glucomed.model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Medico implements Serializable{

	private static final long serialVersionUID = 01L;
	
	@Id
	private String email;
	private List<String> listaPacientes;

	public Medico (String email) {
		this.email=email;
		this.setListaPacientes(null);
	}
	
	public String getEmail() {
		return email;
	}

	public void setEmail(String nombre) {
		this.email = nombre;
	}

	public List<String> getListaPacientes() {
		return listaPacientes;
	}

	public void setListaPacientes(List<String> listaPacientes) {
		this.listaPacientes = listaPacientes;
	}

}
