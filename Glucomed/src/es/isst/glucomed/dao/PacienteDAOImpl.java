package es.isst.glucomed.dao;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import es.isst.glucomed.model.DatosPaciente;
import es.isst.glucomed.model.Paciente;
import es.isst.glucomed.model.User;

public class PacienteDAOImpl implements PacienteDAO {

	private static PacienteDAOImpl instance;
	
	private PacienteDAOImpl(){}
	
	public static PacienteDAOImpl getInstance(){
		if(instance==null)
			instance = new PacienteDAOImpl();
		return instance;
	}

	@Override
	public boolean createPaciente (String email) {

		EntityManager em = EMFService.get().createEntityManager();
		
		Paciente paciente = em.find(Paciente.class, email);
		
		if (paciente != null) {
			
			// TODO: OJO El paciente ya existe y no se crea uno nuevo!!
			
			em.close();
			
			return false;
			
		} else {
			
			paciente = new Paciente (email);	
			em.persist(paciente);
			em.close();
			
			return true;
			
		}

	}
	
	@Override
	public boolean insertData(String email, String fecha, String hora, String valorGlucosa) {

		EntityManager em = EMFService.get().createEntityManager();

		Paciente paciente = em.find(Paciente.class, email);
		
		if (paciente != null) {
			
			paciente.addDatosPaciente(fecha, hora, valorGlucosa);

			em.close();
			
			return true;
			
		} else {
			
			//TODO: Esto seria un error!!
			
			em.close();
			
			return false;
			
		}
		
	}

	@Override
	public List<DatosPaciente> viewData(String email) {
		
		EntityManager em = EMFService.get().createEntityManager();
		
		Paciente paciente = em.find(Paciente.class, email);
		
		if (paciente != null) {

			List<DatosPaciente> listaSalida = new ArrayList<DatosPaciente>();
			
			listaSalida = (List<DatosPaciente>)paciente.getDatosPaciente();

			em.close();
			
			return listaSalida;
			
		} else {
			
			// TODO: Esto sería un error!!
			
			em.close();
			
			return null;
			
		}
		
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
		
		Paciente paciente = em.find(Paciente.class, email);
		
		if (paciente != null) {
			
			paciente.eliminarDatosPaciente();
			em.close();
			
		} else {
			
			// TODO: Esto sería un error!!
			
			em.close();
			
		}	
	
	}
	
	public String getMedicoAsociado (String email) {
		
		String medicoAsociado;
		
		EntityManager em = EMFService.get().createEntityManager();
		
		Paciente paciente = em.find(Paciente.class, email);
		
		if (paciente != null) {
			
			medicoAsociado = paciente.getMedicoAsociado();
			em.close();
			
			return medicoAsociado;
			
		} else {
			
			// TODO: Esto sería un error!!
			// Sin Asignar implica que el paciente existe pero no tiene medico
			// null implica que el paciente no existe
			
			medicoAsociado = null;
			em.close();
			
			return medicoAsociado;
			
		}	
		
	}

	@Override
	public boolean addMedico(String emailMedico, String emailSession) {
		
		EntityManager em = EMFService.get().createEntityManager();
		
		Paciente paciente = em.find(Paciente.class, emailSession);
		
		if (paciente != null) {
			
			paciente.setMedicoAsociado(emailMedico);
			em.close();
			
			return true;
			
		} else {
			
			// TODO: Esto sería un error!!

			return false;
			
		}	

	}
	
	@Override
	public boolean eliminarMedico(String emailMedico, String emailSession) {
		
		EntityManager em = EMFService.get().createEntityManager();
		
		Paciente paciente = em.find(Paciente.class, emailSession);
		
		if (paciente != null) {
			
			paciente.setMedicoAsociado(null);
			em.close();
			
			return true;
			
		} else {
			
			// TODO: Esto sería un error!!

			return false;
			
		}	

	}
	
	/*
	public boolean addMedico(String medicoMail, String emailSession) {

		EntityManager em = EMFService.get().createEntityManager();
		Query w = em
				.createQuery("select item from User item where item.email = :email ");
		w.setParameter("email", medicoMail);
		if (w.getResultList().isEmpty()) {
			// El médico no esta en la lista o se introducido mal el mail
			em.close();
			return false;

		} else {
			// El medico esta registrado procedemos al cambio
			Query q = em.createQuery("select m " + "from User m "
					+ "where m.email LIKE '" + emailSession + "%'");
			
			@SuppressWarnings("unchecked")
			List<User> res = q.getResultList();
			User update = null;
			if (res.size() > 0) {
				update = (User) (q.getResultList().get(0));
			}
			update.setmedicoAsociado(medicoMail);
			em.merge(update);
			em.close();
			return true;
		}
	} */
	
}	
