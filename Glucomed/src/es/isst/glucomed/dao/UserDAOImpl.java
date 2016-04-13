package es.isst.glucomed.dao;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import es.isst.glucomed.model.User;


public class UserDAOImpl implements UserDAO{
	
	private static UserDAOImpl instance;
	
	
	private UserDAOImpl(){
		
	}
	
	public static  UserDAOImpl getInstance(){
		if(instance==null)
			instance = new UserDAOImpl();
		return instance;
	}
	
	public User creteUser(String nombre, String apellidos, String password, String email) { 
				
		User u = null; 
		EntityManager em = EMFService.get().createEntityManager();
		u = new User (nombre, apellidos, password, email);
		em.persist(u);
		
		em.close(); 
		return u; 
		} 
	
	
	public boolean SuccessLogin (String email, String password){
		EntityManager em = EMFService.get().createEntityManager();
		Query q = em.createQuery("select item from User item where item.email = :email and item.password :=pasword");
		q.setParameter("email", email);
		q.setParameter("pasword", password);
		
		if(q.getResultList().isEmpty()){
			return false;
			}
		return true;
	}
	

}
