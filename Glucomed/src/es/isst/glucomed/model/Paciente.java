package es.isst.glucomed.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Transient;

@Entity
public class Paciente implements Serializable{
	
	private static final long serialVersionUID = 01L;
	
	@Id
	private String email;

	private List <String> datosPaciente;

	private String medicoAsociado;

	public Paciente (String email) {
		this.email=email;
		this.datosPaciente = new ArrayList<String>();
		this.medicoAsociado = null;
	}
	
	public String getEmail() {
		return email;
	}

	public void setEmail(String nombre) {
		this.email = nombre;
	}
	
	public DatosPaciente getDatosFromString ( String datos ) {

    	String SplitBy = ",";

    	String[] splitLine = datos.split(SplitBy);
    	
    	String fecha        = splitLine[0];
    	String hora         = splitLine[1];
    	String valorGlucosa = splitLine[2];

		return new DatosPaciente(fecha, hora, valorGlucosa);
		
	}
	
	public String getFechaFromString ( String datos ) {
		
    	String SplitBy = ",";

    	String[] splitLine = datos.split(SplitBy);

		return splitLine[0];
		
	}
	
	public String getHoraFromString ( String datos ) {
		
    	String SplitBy = ",";

    	String[] splitLine = datos.split(SplitBy);

		return splitLine[1];
		
	}
	
	public String getValorGlucosaFromString ( String datos ) {
		
    	String SplitBy = ",";

    	String[] splitLine = datos.split(SplitBy);

		return splitLine[2];
		
	}
	
	public void eliminarDatosPaciente() {
		
		this.datosPaciente.clear();
		
	}
	
	public List <DatosPaciente> getDatosPaciente() {
		
		List <DatosPaciente> listaDatosPacientes = new ArrayList<DatosPaciente>();
		
		for (int i = 0; i < datosPaciente.size(); i++) {
			
			String datosString = datosPaciente.get(i);

			DatosPaciente datos = getDatosFromString(datosString);
			
			listaDatosPacientes.add(datos);
		}
		
	    return listaDatosPacientes;
	}

	public void setDatosPaciente(List <DatosPaciente> datosPaciente) {
		
		this.datosPaciente.clear();
		
		for (int i = 0; i < datosPaciente.size(); i++) {
			
			DatosPaciente datos = datosPaciente.get(i);
		
			this.datosPaciente.add(datos.getFecha() + "," + datos.getHora() + "," + datos.getValorGlucosa());
			
		}

	}
	
	public void addDatosPaciente(String fecha, String hora, String valorGlucosa) {
		
		this.datosPaciente.add(fecha + "," + hora + "," + valorGlucosa);
		
	}
	
	public void addDatosPaciente(DatosPaciente datos) {
		this.datosPaciente.add(datos.getFecha() + "," + datos.getHora() + "," + datos.getValorGlucosa());
	}
	
	public void setMedicoAsociado(String medicoAsociado) {
		this.medicoAsociado = medicoAsociado ;
	}

	public String getMedicoAsociado() {
		return medicoAsociado;
	}

}
