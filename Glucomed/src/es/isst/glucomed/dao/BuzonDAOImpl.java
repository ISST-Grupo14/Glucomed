package es.isst.glucomed.dao;

import java.util.List;

import javax.persistence.EntityManager;

import es.isst.glucomed.model.Buzon;
import es.isst.glucomed.model.Mensaje;

public class BuzonDAOImpl implements BuzonDAO {

	private static BuzonDAOImpl instance;
	
	private BuzonDAOImpl(){}
	
	public static BuzonDAOImpl getInstance(){
		if(instance==null)
			instance = new BuzonDAOImpl();
		return instance;
	}

	@Override
	public List<Mensaje> getMensajesBuzon(String emailPaciente, String emailMedico) {
		
		EntityManager em = EMFService.get().createEntityManager();
		
		Buzon buzon = em.find(Buzon.class, emailPaciente + "," + emailMedico);
		
		if (buzon == null) {
			
			// No existe aun un buzon. Lo creamos !!
			
			buzon = new Buzon (emailPaciente, emailMedico);
			
			em.persist(buzon);
						
		}

		em.close();
		
		return buzon.getMensajes();

	}

	@Override
	public void addMensajeEnBuzon(Mensaje mensaje, String emailPaciente, String emailMedico) {
		
		EntityManager em = EMFService.get().createEntityManager();
		
		Buzon buzon = em.find(Buzon.class, emailPaciente + "," + emailMedico);
		
		if (buzon == null) {
			
			// No existe aun un buzon. Lo creamos !!
			
			buzon = new Buzon (emailPaciente, emailMedico);
			
			buzon.addMensajeEnBuzon(mensaje);
			
			em.persist(buzon);
			
		} else {
			
			// Ya hay un buzon existente. Añadimos el mensaje
			
			buzon.addMensajeEnBuzon(mensaje);
			
		}

		em.close();
		
	}

	@Override
	public List<Mensaje> eliminarMensajes(String emailPaciente, String emailMedico) {
		
		EntityManager em = EMFService.get().createEntityManager();
		
		Buzon buzon = em.find(Buzon.class, emailPaciente + "," + emailMedico);
		
		if (buzon == null) {
			
			// No existe aun un buzon. Lo creamos !!
			
			buzon = new Buzon (emailPaciente, emailMedico);
			
			em.persist(buzon);
						
		} else {
			buzon.eliminarMensajes();
		}

		em.close();
		
		return buzon.getMensajes();
	}
	
	
}	
