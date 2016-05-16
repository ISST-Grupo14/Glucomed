package es.isst.glucomed.model;

import java.io.Serializable;

public final class DatosPaciente implements Serializable {
	
	static final long serialVersionUID = 01L;
	
	private String fecha;
	private String hora;
	private String valorGlucosa;
	
	public DatosPaciente (String fecha, String hora, String valorGlucosa) {
		this.setFecha(fecha);
		this.setHora(hora);
		this.setValorGlucosa(valorGlucosa);
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