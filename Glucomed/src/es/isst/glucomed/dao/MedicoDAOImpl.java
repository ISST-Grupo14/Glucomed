package es.isst.glucomed.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import es.isst.glucomed.model.Medico;
import es.isst.glucomed.model.Paciente;
import es.isst.glucomed.model.User;

public class MedicoDAOImpl implements MedicoDAO {

	private static MedicoDAOImpl instance;
	
	private MedicoDAOImpl(){}
	
	public static MedicoDAOImpl getInstance(){
		if(instance==null)
			instance = new MedicoDAOImpl();
		return instance;
	}
	
	@Override
	public boolean createMedico (String email) {

		EntityManager em = EMFService.get().createEntityManager();
		
		Medico medico = em.find(Medico.class, email);
		
		if (medico != null) {
			
			// TODO: OJO El paciente ya existe y no se crea uno nuevo!!
			
			em.close();
			
			return false;
			
		} else {
			
			medico = new Medico (email);	
			em.persist(medico);
			em.close();
			
			return true;
			
		}

	}
	
}	
