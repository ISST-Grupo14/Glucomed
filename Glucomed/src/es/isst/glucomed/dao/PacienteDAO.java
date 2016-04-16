package es.isst.glucomed.dao;

import es.isst.glucomed.model.Paciente;

public interface PacienteDAO {
	public boolean insertData (String email, String fecha, String hora, String valorGlucosa);
	public Paciente viewData (String email);
}
