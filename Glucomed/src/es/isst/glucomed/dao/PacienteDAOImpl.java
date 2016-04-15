package es.isst.glucomed.dao;

import javax.persistence.EntityManager;

import es.isst.glucomed.model.Paciente;
import es.isst.glucomed.model.User;
import es.isst.glucomed.dao.*;

public class PacienteDAOImpl implements PacienteDAO {

	private static PacienteDAOImpl instance;
	
	private PacienteDAOImpl(){}
	
	public static PacienteDAOImpl getInstance(){
		if(instance==null)
			instance = new PacienteDAOImpl();
		return instance;
	}

	@Override
	public boolean insertData(String user, String fecha, String hora,
			String valorGlucosa) {
		
		Paciente paciente = new Paciente(user, fecha, hora, valorGlucosa);
		
		EntityManager em = EMFService.get().createEntityManager();
		em.persist(paciente);
		em.close();
		return true; 
			
	}

	//@Override
	//public Paciente getPaciente(r) {
	//	return null;
	//}

}
