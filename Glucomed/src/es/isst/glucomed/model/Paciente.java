package es.isst.glucomed.model;


import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Paciente implements Serializable{
	private static final long serialVersionUID = 01L;
	@Id
	private String nombre;
	private String fecha;
	private String hora;
	private String valorGlucosa;
	

	public Paciente (String nombre, String fecha, String hora, String valorGlucosa) {
		this.nombre=nombre;
		this.fecha=fecha;
		this.hora=hora;
		this.valorGlucosa=valorGlucosa;
		
		
	}
	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
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