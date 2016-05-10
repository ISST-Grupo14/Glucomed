package es.isst.glucomed.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import es.isst.glucomed.model.User;

public class UserDAOImpl implements UserDAO {

	private static UserDAOImpl instance;

	private UserDAOImpl() {
	}

	public static UserDAOImpl getInstance() {
		if (instance == null)
			instance = new UserDAOImpl();
		return instance;

	}

	public boolean createUser(String nombre, String apellidos, String tipoUser,
			String password, String email) {

		User u = new User(nombre, apellidos, tipoUser, password, email);
		boolean testUser = SuccessRegister(email);
		boolean resultado;
		
		EntityManager em = EMFService.get().createEntityManager();
		
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
		Query q = em.createQuery("select item from User item where item.email = :email");

		q.setParameter("email", email);

		if (q.getResultList().isEmpty()) {
			em.close();
			return false;
		} else {
			em.close();
			return true;
		}
	}

	public boolean SuccessLogin(String email, String password) {

		EntityManager em = EMFService.get().createEntityManager();
		Query q = em.createQuery("select item from User item where item.email = :email and item.password = :password");
		q.setParameter("email", email);
		q.setParameter("password", password);

		if (q.getResultList().isEmpty()) {
			
			// si no tiene ninguno de los dos campos devuelve false
			
			em.close();
			
			return false;
			
		}else{
			
			// si alguno de los dos campos esta relleno devuelve true
			
			em.close();
			
			return true; 
		}

	}
	
	public User viewUser (String email) {
		
		EntityManager em = EMFService.get().createEntityManager();
		
		User datosUser = em.find(User.class, email);
		
		em.close();
		
		return datosUser;
		
	}

	public String tipoUser(String email) {

		// buscamos el tipo usuario primero para mostrar una lista o otra
		EntityManager em = EMFService.get().createEntityManager();
		Query q = em.createQuery("SELECT m " + "FROM User m "
				+ "  WHERE m.email = '" + email
				+ "' AND m.tipoUser = 'paciente'");
		
		@SuppressWarnings("unchecked")
		List<User> res = q.getResultList();
		
		em.close();

		if (res.size() > 0) {
			return "paciente";
		} else {
			return "medico";
		}
		
	}
	
	public List<User> viewListaMedicos (String email) {
		
		EntityManager em = EMFService.get().createEntityManager();
		
		Query w = em.createQuery("select m " + "from User m " + "where m.tipoUser LIKE '" + "medico" + "%'");
		
		@SuppressWarnings("unchecked")
		
		List<User> result = w.getResultList();
		
		em.close();
		
		return result;

	}

}
