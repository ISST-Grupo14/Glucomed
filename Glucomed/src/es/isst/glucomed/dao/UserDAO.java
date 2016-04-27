package es.isst.glucomed.dao;

import java.util.List;

import es.isst.glucomed.model.Paciente;
import es.isst.glucomed.model.User;

public interface UserDAO {
	
	public boolean createUser (String nombre, String apellidos,String tipoUser, String password, String email);
	public boolean SuccessLogin (String email, String password);
	public boolean SuccessRegister (String email);
	public List<User> viewMedico(String email);
	public List<User> viewUser();
	public boolean addMedico(String medicoMail, String emailSession);
	public String tipoUser(String email) ;

}
