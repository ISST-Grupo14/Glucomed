package es.isst.glucomed.dao;

import java.util.List;

import es.isst.glucomed.model.DatosPaciente;
import es.isst.glucomed.model.User;

public interface PacienteDAO {
	
	// Registro
	
	public boolean createPaciente (String email);
	
	// Manipulacion de Datos
	
	public boolean insertData (String email, String fecha, String hora, String valorGlucosa);
	public List<DatosPaciente> viewData(String email);
	public void eliminarDatosPaciente (String email);
	
	// Gestion Medico de Paciente
	
	public boolean addMedico(String medicoMail, String emailSession);
	public boolean eliminarMedico(String emailMedico, String emailSession);
	public String getMedicoAsociado (String email);

	// Lista de pacientes de un medico
	
	public List<User> viewPacientesDeMedico(String medicoAsociado);
	
}