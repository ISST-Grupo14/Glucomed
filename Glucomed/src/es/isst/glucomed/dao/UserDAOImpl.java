package es.isst.glucomed.dao;

import java.util.List;

import javax.persistence.*;

import es.isst.glucomed.model.Paciente;
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
		Query q = em
				.createQuery("select item from User item where item.email = :email");

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
		Query q = em
				.createQuery("select item from User item where item.email = :email and item.password = :password");
		q.setParameter("email", email);
		q.setParameter("password", password);

		if (q.getResultList().isEmpty()) {
			em.close();
			return false; // si no tiene ninguno de los dos campos devuelve
							// false
		}else{
			em.close();
			return true; // si alguno de los dos campos esta relleno devuelve
							// true
		}

	}
	
	public List<User> viewListaMedicos (String email) {

		List<User> listaMedicos;
		
		EntityManager em = EMFService.get().createEntityManager();
		Query w = em.createQuery("select m " + "from User m "
				+ "where m.tipoUser LIKE '" + "medico" + "%'");
		
		@SuppressWarnings("unchecked")
		
		List<User> result = w.getResultList();
		
		em.close();
		
		return result;

	}
	
	/*
	public List<User> viewMedico(String email) {

		// buscamos el tipo usuario primero para mostrar una lista o otra
		EntityManager em = EMFService.get().createEntityManager();
		Query q = em.createQuery("SELECT m " + "FROM User m "
				+ "  WHERE m.email = '" + email
				+ "' AND m.tipoUser = 'paciente'");
		
		@SuppressWarnings("unchecked")
		List<User> res = q.getResultList();

		if (res.size() > 0) {
			// res es mayor que cero se ha encontrado un paciente con ese email,
			// asi que mostramos medicos
			Query w = em.createQuery("select m " + "from User m "
					+ "where m.tipoUser LIKE '" + "medico" + "%'");
			
			@SuppressWarnings("unchecked")
			List<User> result = w.getResultList();
			em.close();
			return result;
		} else {
			// como es un medico mostramos los pacientes que tiene asignados
			Query w = em.createQuery("select m " + "from User m "
					+ "  WHERE m.medicoAsociado = '" + email + "'");
			
			@SuppressWarnings("unchecked")
			List<User> result = w.getResultList();
			em.close();
			return result;
		}
	}
	*/
	
	public User viewUser(String email) {
		
		EntityManager em = EMFService.get().createEntityManager();
		
		User datosUser = em.find(User.class, email);
		
		em.close();
		
		return datosUser;
		
		
	}

	// Prueba de lista de usuarios
	public List<User> viewPacientes() {
		// String tipoUser = User.getTipoUser();
		// hay que usar el email para filtrar en la query
		EntityManager em = EMFService.get().createEntityManager();

		Query q = em.createQuery("select m " + "from User m "
				+ "where m.tipoUser LIKE '" + "paciente" + "%'");
		@SuppressWarnings("unchecked")
		List<User> res = q.getResultList();
		em.close();
		return res;
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

	@Override
	public void addBlobKey(String email, String blobKey) {
		EntityManager em = EMFService.get().createEntityManager();
		User update = em.find(User.class, email);
		update.setBlobKey(blobKey);
		em.merge(update);
		em.close();
	}

	@Override
	public String readBlobKey(String email) {
		EntityManager em = EMFService.get().createEntityManager();
		User findKey = em.find(User.class, email);
		String result = findKey.getBlobKey();
		em.close();
		System.out.println("Blob almacenada(metodo readFile): " + result);
		return result;
	}

	public boolean viewDataFromMedico(String email, String emailPaciente) {
		// email corresponde a la de la sesion

		EntityManager em = EMFService.get().createEntityManager();
		Query q = em.createQuery("SELECT m " + "FROM User m "
				+ "  WHERE m.email = '" + emailPaciente
				+ "' AND m.medicoAsociado= '" + email + "'");
		@SuppressWarnings("unchecked")
		List<User> res = q.getResultList();
		em.close();

		if (res.size() > 0) { // medico autorizado
			return true;
		} else {
			return false;
		}

	}

}
