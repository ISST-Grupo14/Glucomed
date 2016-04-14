package es.isst.glucomed.dao;

public interface UserDAO {
	
	public boolean createUser(String nombre, String apellidos, String password, String email);
	public boolean SuccessLogin (String email, String password);

}
