package es.isst.glucomed.dao;

import java.util.List;

import es.isst.glucomed.model.Mensaje;

public interface BuzonDAO {
	
	public List<Mensaje> getMensajesBuzon(String emailPaciente, String emailMedico);
	public void addMensajeEnBuzon(Mensaje mensaje, String emailPaciente, String emailMedico);
	public List<Mensaje> eliminarMensajes(String emailPaciente, String emailMedico);

}