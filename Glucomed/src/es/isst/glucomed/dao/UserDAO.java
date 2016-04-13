package es.isst.glucomed.dao;

import es.isst.glucomed.model.*;

public interface UserDAO {
	
	public User creteUser(String nombre, String apellidos, String password, String email);
	public boolean SuccessLogin (String email, String password);

}
