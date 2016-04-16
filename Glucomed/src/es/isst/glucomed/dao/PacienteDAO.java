package es.isst.glucomed.dao;

import java.util.List;

import es.isst.glucomed.model.Paciente;

public interface PacienteDAO {
	public boolean insertData (String email, String fecha, String hora, String valorGlucosa);
	public List<Paciente> viewData(String email);
}
