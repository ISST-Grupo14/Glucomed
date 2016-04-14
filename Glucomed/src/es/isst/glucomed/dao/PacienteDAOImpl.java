package es.isst.glucomed.dao;

import javax.persistence.EntityManager;

import es.isst.glucomed.model.Paciente;
import es.isst.glucomed.model.User;
import es.isst.glucomed.dao.*;

public class PacienteDAOImpl implements PacienteDAO {

	public PacienteDAOImpl() {
		
	}

	@Override
	public boolean insertData(User user, String fecha, String hora,
			String valorGlucosa) {
		UserDAO dao = (UserDAO) UserDAOImpl.getInstance();
		boolean testUser = dao.SuccessLogin (user.getEmail(),user.getPassword());
		Paciente paciente = new Paciente (user.getNombre(), fecha, hora, valorGlucosa);
		
		if (testUser) { 		
			EntityManager em = EMFService.get().createEntityManager();
			em.persist(paciente);
			em.close(); 
			return true;
		} else {
			return false;
		}
	}

	@Override
	public Paciente getPaciente(User user) {
	
	}

}
