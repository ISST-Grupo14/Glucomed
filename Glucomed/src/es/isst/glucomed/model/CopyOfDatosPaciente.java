package es.isst.glucomed.model;

import java.io.Serializable;

import javax.persistence.Basic;
import javax.persistence.Embeddable;

@Embeddable
public class CopyOfDatosPaciente implements Serializable {
    
	private static final long serialVersionUID = 01L;
	
    @Basic
	private String fecha;
    @Basic
	private String hora;
    @Basic
	private String valorGlucosa;
	
	public CopyOfDatosPaciente (String fecha, String hora, String valorGlucosa) {
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