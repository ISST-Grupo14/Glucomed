package es.isst.glucomed.dao;

import javax.persistence.EntityManager;
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
	
	public boolean createUser(String nombre, String apellidos, String password, String email) { 
				
		EntityManager em = EMFService.get().createEntityManager();
		User u = new User (nombre, apellidos, password, email);
		boolean testUser = SuccessLogin (email,password);
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
	
	
	public boolean SuccessLogin (String email, String password){
				
		EntityManager em = EMFService.get().createEntityManager();
		Query q = em.createQuery("select item from User item where item.email = :email and item.password = :password");
		q.setParameter("email", email);
		q.setParameter("password", password);
		
		if(q.getResultList().isEmpty()){
			return false;
		} else {
			return true;	
		}
	}
		
	
}
