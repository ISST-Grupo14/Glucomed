package es.isst.glucomed.dao;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import es.isst.glucomed.model.DatosPaciente;
import es.isst.glucomed.model.Paciente;
import es.isst.glucomed.model.User;

public final class PacienteDAOImpl implements PacienteDAO {

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
			
			// TODO: Esto ser�a un error!!
			
			em.close();
			
			return null;
			
		}
		
	}
	
	public void eliminarDatosPaciente (String email) {

		EntityManager em = EMFService.get().createEntityManager();
		
		Paciente paciente = em.find(Paciente.class, email);
		
		if (paciente != null) {
			
			paciente.eliminarDatosPaciente();
			em.close();
			
		} else {
			
			// TODO: Esto ser�a un error!!
			
			em.close();
			
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
			
			// TODO: Esto ser�a un error!!

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
			
			// TODO: Esto ser�a un error!!

			return false;
			
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
			
			// TODO: Esto ser�a un error!!
			// Sin Asignar implica que el paciente existe pero no tiene medico
			// null implica que el paciente no existe
			
			medicoAsociado = null;
			em.close();
			
			return medicoAsociado;
			
		}	
		
	}
	
	public List<User> viewPacientesDeMedico(String medicoAsociado) {
		
		// String tipoUser = User.getTipoUser();
		// hay que usar el email para filtrar en la query
		
		EntityManager em = EMFService.get().createEntityManager();

		Query q = em.createQuery("select m " + "from Paciente m "
				+ "where m.medicoAsociado LIKE '" + medicoAsociado + "%'");
		@SuppressWarnings("unchecked")
		List<Paciente> pacientes = q.getResultList();
		List<User> listaUsuarios= new ArrayList <User>();
		
		for (int i = 0; i < pacientes.size(); i++) {
			Paciente paciente = pacientes.get(i);
			UserDAO dao =  UserDAOImpl.getInstance();
			listaUsuarios.add(dao.viewUser(paciente.getEmail()));
		}
		
		em.close();
		
		return listaUsuarios;
		
	}
		
}	
