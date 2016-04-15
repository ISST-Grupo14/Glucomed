package es.isst.glucomed.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class User implements Serializable {
	private static final long serialVersionUID = 01L;
	@Id
	private String nombre;
	private String apellidos;
	private String password;
	private String email;
		
public User (String nombre, String apellidos, String password, String email) {
	
	this.nombre=nombre;
	this.apellidos=apellidos;
	this.password=password;
	this.email=email;
	
}

public String getNombre() {
	return nombre;
}

public void setNombre(String nombre) {
	this.nombre = nombre;
}

public String getApellidos() {
	return apellidos;
}

public void setApellidos(String apellidos) {
	this.apellidos = apellidos;
}

public String getPassword() {
	return password;
}

public void setPassword(String password) {
	this.password = password;
}

public String getEmail() {
	return email;
}

public void setEmail(String email) {
	this.email = email;
}

}
