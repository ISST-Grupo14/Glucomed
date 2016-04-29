package es.isst.glucomed.files;

import com.opencsv.bean.CsvBind;



import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;


public class DataBean {
	@GeneratedValue(strategy=GenerationType.SEQUENCE) 
	private String id;
	@CsvBind
	private String fecha;
	@CsvBind
	private String hora;
	@CsvBind//(required = true)
	private int valorGlucosa;

	
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


	public int getValorGlucosa() {
		return valorGlucosa;
	}

	public void setValorGlucosa(int valorGlucosa) {
		this.valorGlucosa = valorGlucosa;
	}	


}