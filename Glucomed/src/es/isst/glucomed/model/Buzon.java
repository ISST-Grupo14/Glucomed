package es.isst.glucomed.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Buzon implements Serializable {
	
	static final long serialVersionUID = 01L;
	
	// IDMensaje --> Tupla String PacienteMail, MedicoMail para identificar la entidad
	
	@Id
	private String idMensaje;
	
	private List <String> buzon;
	
	public Buzon (String mailPaciente, String mailMedico) {
		this.idMensaje = mailPaciente + "," + mailMedico;
		this.buzon     = new ArrayList<String>();
	}
	
	public String getIDMensaje() {
		return idMensaje;
	}

	public void setIDMensaje(String idMensaje) {
		this.idMensaje = idMensaje;
	}
	
	public void setIDMensaje(String mailPaciente, String mailMedico) {
		this.idMensaje = mailPaciente + "," + mailMedico;
	}
	
	// Funciones para la manipulacion de mensajes
	
	public Mensaje getMensajeFromMensajeString ( String mensaje ) {

    	String splitBy = ",";

    	String[] splitLine = mensaje.split(splitBy);
    	
    	String origen    = splitLine[0];
    	String destino   = splitLine[1];
    	String contenido = splitLine[2];
    	String fecha     = splitLine[3];

		return new Mensaje(origen, destino, contenido, fecha);
		
	}
	
	public String getOrigenFromMensajeString ( String mensaje ) {
		
    	String splitBy = ",";

    	String[] splitLine = mensaje.split(splitBy);

		return splitLine[0];
		
	}
	
	public String getDestinoFromMensajeString ( String mensaje ) {
		
    	String splitBy = ",";

    	String[] splitLine = mensaje.split(splitBy);

		return splitLine[1];
		
	}
	
	public String getContenidoFromMensajeString ( String mensaje ) {
		
    	String splitBy = ",";

    	String[] splitLine = mensaje.split(splitBy);

		return splitLine[2];
		
	}
	
	public String getFechaFromMensajeString ( String mensaje ) {
		
    	String splitBy = ",";

    	String[] splitLine = mensaje.split(splitBy);

		return splitLine[3];
		
	}

	public List <Mensaje> getMensajes() {
		
		List <Mensaje> mensajes = new ArrayList<Mensaje>();
		
		for (int i = 0; i < buzon.size(); i++) {
			
			String datosString = buzon.get(i);

			Mensaje datos = getMensajeFromMensajeString(datosString);
			
			mensajes.add(datos);
		}
		
	    return mensajes;
		
	}

	public void setMensajes(List <Mensaje> mensajes) {
		
		buzon.clear();
		
		for (int i = 0; i < mensajes.size(); i++) {
			
			Mensaje mensaje = mensajes.get(i);
		
			buzon.add(mensaje.getOrigen() + "," + mensaje.getDestino() + "," + mensaje.getContenido() + "," + mensaje.getFecha());
			
		}
		
	}
	
	public void addMensajeEnBuzon (String origen, String destino, String contenido, String fecha) {
		buzon.add(origen + "," + destino + "," + contenido + "," + fecha);
	}	
	
	public void addMensajeEnBuzon (Mensaje mensaje) {
		buzon.add(mensaje.getOrigen() + "," + mensaje.getDestino() + "," + mensaje.getContenido() + "," + mensaje.getFecha());
	}

	public void eliminarMensajes() {
		buzon.clear();
	}
	
}