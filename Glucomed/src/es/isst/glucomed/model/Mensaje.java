package es.isst.glucomed.model;

import java.io.Serializable;

public class Mensaje implements Serializable {
	
	static final long serialVersionUID = 01L;
	
	private String origen;
	private String destino;
	private String contenido;
	private String fecha;
	
	public Mensaje (String origen, String destino, String contenido, String fecha) {
		this.origen    = origen;
		this.destino   = destino;
		this.contenido = contenido;
		this.fecha     = fecha;
	}

	public String getOrigen() {
		return origen;
	}

	public void setOrigen(String origen) {
		this.origen = origen;
	}

	public String getDestino() {
		return destino;
	}

	public void setDestino(String destino) {
		this.destino = destino;
	}

	public String getContenido() {
		return contenido;
	}

	public void setContenido(String contenido) {
		this.contenido = contenido;
	}
	
	public String getFecha() {
		return fecha;
	}

	public void setFecha(String fecha) {
		this.fecha = fecha;
	}

}