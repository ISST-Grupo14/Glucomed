package es.isst.glucomed.dao;

import java.util.List;

import es.isst.glucomed.model.User;

public interface UserDAO {
	
	// Registro y Login
	
	public boolean createUser (String nombre, String apellidos,String tipoUser, String password, String email);
	
	public boolean successRegister (String email);
	public boolean successLogin (String email, String password);
	
	// Carcateristicas User
	
	public User viewUser(String email);
	public String tipoUser(String email) ;
	
	// Carcateristicas Medicos
	
	public List<User> viewListaMedicos (String email);
	
}
