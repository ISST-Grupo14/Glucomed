package es.isst.glucomed.dao;

import java.util.List;

import es.isst.glucomed.model.User;

public interface UserDAO {
	
	public boolean createUser (String nombre, String apellidos,String tipoUser, String password, String email);
	public boolean SuccessLogin (String email, String password);
	public boolean SuccessRegister (String email);
	public List<User> viewListaMedicos (String email);
	public List<User> viewPacientes();
	public User viewUser(String email);
	public String tipoUser(String email) ;
	public void addBlobKey(String email, String blobKey);
	public boolean viewDataFromMedico(String email, String emailPaciente);
	public String readBlobKey(String email);
	
}
