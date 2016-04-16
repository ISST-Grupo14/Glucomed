package es.isst.glucomed.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Paciente implements Serializable{
	private static final long serialVersionUID = 01L;
	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE) 
	private String id;
	private String email;
	private String fecha;
	private String hora;
	private String valorGlucosa;

	public Paciente (String email, String fecha, String hora, String valorGlucosa) {
		this.email=email;
		this.fecha=fecha;
		this.hora=hora;
		this.valorGlucosa=valorGlucosa;
		
	}
	public String getEmail() {
		return email;
	}

	public void setEmail(String nombre) {
		this.email = nombre;
	}
	
	public String getFecha() {
		return fecha;
	}

	public void setFecha(String fecha) {
		this.fecha = fecha;
	}

	public String getHora() {
		return hora;
	}

	public void setHora(String hora) {
		this.hora = hora;
	}


	public String getValorGlucosa() {
		return valorGlucosa;
	}

	public void setValorGlucosa(String valorGlucosa) {
		this.valorGlucosa = valorGlucosa;
	}	

}
