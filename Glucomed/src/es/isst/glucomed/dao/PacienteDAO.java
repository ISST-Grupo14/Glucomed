package es.isst.glucomed.dao;

import java.util.List;

import es.isst.glucomed.model.DatosPaciente;
import es.isst.glucomed.model.Paciente;

public interface PacienteDAO {
	public boolean createPaciente (String email);
	public boolean insertData (String email, String fecha, String hora, String valorGlucosa);
	public List<DatosPaciente> viewData(String email);
	public List<Paciente> viewDataFromMedico(String emailMedico, String emailPaciente);
	public void eliminarDatosPaciente (String email);
	public String getMedicoAsociado (String email);
	public boolean addMedico(String medicoMail, String emailSession);
	public boolean eliminarMedico(String emailMedico, String emailSession);
}