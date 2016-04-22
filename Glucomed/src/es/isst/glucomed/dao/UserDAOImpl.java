package es.isst.glucomed.dao;

import java.util.List;

import javax.persistence.*;

import es.isst.glucomed.model.Paciente;
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
		User u = new User (nombre, apellidos, tipoUser, password, email);
		boolean testUser = SuccessRegister (email);
		boolean resultado;
		//boolean testUser = false;
		
		if (testUser == false) { 
			em.persist(u);
			resultado = true;
			
		} else {
			resultado = false;
		}
		em.close();
		return resultado;
	} 
	
	
	public boolean SuccessRegister (String email){
		
		EntityManager em = EMFService.get().createEntityManager();
		Query q = em.createQuery("select item from User item where item.email = :email");
		q.setParameter("email", email);
		
		if(q.getResultList().isEmpty()){
			return false;
		} else {
			return true;
		}
	}
	
	public boolean SuccessLogin (String email, String password){
				
		EntityManager em = EMFService.get().createEntityManager();
		Query q = em.createQuery("select item from User item where item.email = :email and item.password = :password");
		q.setParameter("email", email);
		q.setParameter("password", password);
		
		if(q.getResultList().isEmpty()){
			return false; //si no tiene ninguno de los dos campos devuelve false
		} else {
			return true; //si alguno de los dos campos esta relleno devuelve true
		}
	}

	public List<User> viewMedico() {
		
		//String tipoUser = User.getTipoUser();
					//hay que usar el email para filtrar en la query
			EntityManager em = EMFService.get().createEntityManager();
			
			Query q = em.createQuery("select m " + "from User m " + "where m.tipoUser LIKE '" + "medico" + "%'");
			System.out.println(q);
			List<User> res = q.getResultList();
			em.close();
			return res;
	}
		
	
}
