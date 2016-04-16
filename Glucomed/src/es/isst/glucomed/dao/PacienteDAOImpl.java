package es.isst.glucomed.dao;

import javax.persistence.EntityManager;

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
				
		EntityManager em = EMFService.get().createEntityManager();
		
		// Verificamos si el paciente existe
		
		Paciente paciente = em.find(Paciente.class, email);
		
		if (paciente == null) {
			
			// paciente aun sin datos
			em.persist(paciente);
			
		} else {
			
			// Actualizamos datos

			Paciente pacienteFind = em.find(Paciente.class, email);
			
			pacienteFind.setFecha(fecha);
			pacienteFind.setHora(hora);
			pacienteFind.setValorGlucosa(valorGlucosa);
			
		}
		
		em.close();

		return true; 
			
	}
	
	public Paciente viewData (String email){

		EntityManager em = EMFService.get().createEntityManager();
		
		Paciente paciente = em.find(Paciente.class, email);
		
		em.close();
		
		if (paciente == null) {
			return null;
		} else {
			return paciente;
		}

	}

}
