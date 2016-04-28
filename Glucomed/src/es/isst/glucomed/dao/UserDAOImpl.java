package es.isst.glucomed.dao;

import java.util.List;

import javax.persistence.*;

import es.isst.glucomed.model.User;


public class UserDAOImpl implements UserDAO{
	


	private static UserDAOImpl instance;

	
	private UserDAOImpl(){}
	
	public static  UserDAOImpl getInstance(){
		if(instance==null)
			instance = new UserDAOImpl();
		return instance;
		
	}
	
	
	public boolean createUser(String nombre, String apellidos, String tipoUser, String password, String email) { 
				
		EntityManager em = EMFService.get().createEntityManager();
		User u = new User(nombre, apellidos, tipoUser, password, email);
		boolean testUser = SuccessRegister(email);
		boolean resultado;
		// boolean testUser = false;

		if (testUser == false) {
			em.persist(u);
			resultado = true;

		} else {
			resultado = false;
		}
		em.close();
		return resultado;

	} 

	public boolean SuccessRegister(String email) {

		EntityManager em = EMFService.get().createEntityManager();
		Query q = em
				.createQuery("select item from User item where item.email = :email");

		q.setParameter("email", email);

		if (q.getResultList().isEmpty()) {
			return false;
		} else {
			return true;
		}
	}

	public boolean SuccessLogin(String email, String password) {

		EntityManager em = EMFService.get().createEntityManager();
		Query q = em
				.createQuery("select item from User item where item.email = :email and item.password = :password");
		q.setParameter("email", email);
		q.setParameter("password", password);

		if (q.getResultList().isEmpty()) {
			return false; // si no tiene ninguno de los dos campos devuelve
							// false
		} else {
			
			return true; //si alguno de los dos campos esta relleno devuelve true
			}
	}


	public List<User> viewMedico(String email) {

		
	//buscamos el tipo usuario primero para mostrar una lista o otra
		EntityManager em = EMFService.get().createEntityManager();
		Query q = em.createQuery("SELECT m " + "FROM User m " + "  WHERE m.email = '" + email  + "' AND m.tipoUser = 'paciente'" );			
		List<User> res = q.getResultList();
		
			if(res.size() > 0){
			//res es mayor que cero se ha encontrado un paciente con ese email, asi que mostramos medicos
			Query w = em.createQuery("select m " + "from User m " + "where m.tipoUser LIKE '" + "medico" + "%'");
			List<User> result = w.getResultList();
			em.close();
			return result;
	}
		else{
			// como es un medico mostramos los pacientes que tiene asignados
			Query w = em.createQuery("select m " + "from User m " + "  WHERE m.medicoAsociado = '" + email  + "'");
			List<User> result = w.getResultList();
			em.close();
			return result;
		}
	}

	public boolean addMedico(String medicoMail, String emailSession) {
		
		EntityManager em = EMFService.get().createEntityManager();
		Query w = em.createQuery("select item from User item where item.email = :email ");
		w.setParameter("email", medicoMail);
			if (w.getResultList().isEmpty()){
				//El médico no esta en la lista o se introducido mal el mail
				return false;
				
			}else{
				//El medico esta registrado procedemos al cambio
				Query q = em.createQuery("select m " + "from User m " + "where m.email LIKE '" + emailSession + "%'");
				List<User> res = q.getResultList();
				User update = null;
					if(res.size() > 0){
					update = (User) (q.getResultList().get(0));
					}
				update.setmedicoAsociado(medicoMail);
				em.merge(update);
				em.close();
				return true;
			}
	}
	


			//Prueba de lista de usuarios
			public List<User> viewUser() {
				//String tipoUser = User.getTipoUser();
				//hay que usar el email para filtrar en la query
				EntityManager em = EMFService.get().createEntityManager();

				Query q = em.createQuery("select m " + "from User m " + "where m.tipoUser LIKE '" + "paciente" + "%'");
				List<User> res = q.getResultList();
				em.close();
				return res;
				}

	public String tipoUser(String email) {

		//buscamos el tipo usuario primero para mostrar una lista o otra
			EntityManager em = EMFService.get().createEntityManager();
			Query q = em.createQuery("SELECT m " + "FROM User m " + "  WHERE m.email = '" + email  + "' AND m.tipoUser = 'paciente'" );			
			List<User> res = q.getResultList();
			
				if(res.size() > 0){
				return "paciente";
			}else{
				return "medico";
			}
		}	
	
	
	
	@Override
	public void addFilePath(String email, String filePath) {

		EntityManager em = EMFService.get().createEntityManager();
		Query q = em.createQuery("select m " + "from User m "
				+ "where m.email LIKE '" + email + "%'");
		List<User> res = q.getResultList();
		User update = null;
		if (res.size() > 0) {
			update = (User) (q.getResultList().get(0));
		}
		update.setFilePath(filePath);
		em.merge(update);
		em.close();
	}
	
	
	
}
