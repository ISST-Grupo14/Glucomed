package es.isst.glucomed.dao;
import es.isst.glucomed.model.*;

public interface PacienteDAO {
	public boolean insertData (User user, String fecha, String hora, String valorGlucosa);
	public Paciente getPaciente (User user);
}
