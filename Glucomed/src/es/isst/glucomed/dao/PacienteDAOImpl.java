package es.isst.glucomed.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import es.isst.glucomed.model.Paciente;

public class PacienteDAOImpl implements PacienteDAO {

	private static PacienteDAOImpl instance;
	
	private PacienteDAOImpl(){}
	
	public static PacienteDAOImpl getInstance(){
		if(instance==null)
			instance = new PacienteDAOImpl();
		return instance;
	}

	
	@Override
	public boolean insertData(String email, String fecha, String hora, String valorGlucosa) {
		
		Paciente u = null;
		EntityManager em = EMFService.get().createEntityManager();
		u = new Paciente (email, fecha, hora, valorGlucosa);
		em.persist(u);
		em.close();
		return true;
		
	}

	@Override
	public List<Paciente> viewData(String email) {

		//hay que usar el email para filtrar en la query
		EntityManager em = EMFService.get().createEntityManager();
		Query q = em.createQuery("select m " + "from Paciente m " + "where m.email LIKE '" + email + "%'");
		@SuppressWarnings("unchecked")
		List<Paciente> res = q.getResultList();
		em.close();
		return res;
		
	}
	
	public List<Paciente> viewDataFromMedico(String emailMedico, String emailPaciente) {

		//hay que usar el email para filtrar en la query
		EntityManager em = EMFService.get().createEntityManager();
		Query q = em.createQuery("select m " + "from Paciente m " + "where m.email LIKE '" + emailPaciente + "%'");
		@SuppressWarnings("unchecked")
		List<Paciente> res = q.getResultList();
		em.close();
		return res;
	
	}
	
	public void eliminarDatosPaciente (String email) {
	
		EntityManager em = EMFService.get().createEntityManager();
		Query query = em.createQuery("DELETE FROM Paciente m WHERE m.email LIKE '" + email + "%'");
		// int deletedCount = query.executeUpdate();
		query.executeUpdate();
		em.close();
	
	}
	
}	
