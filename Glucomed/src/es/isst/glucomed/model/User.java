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
	private String tipoUser;
	private String password;
	private String email;
	private String medicoAsociado;
		
public User (String nombre, String apellidos, String tipoUser, String password, String email){
	this.nombre=nombre;
	this.apellidos=apellidos;
	this.tipoUser = tipoUser;
	this.password=password;
	this.email=email;
	this.medicoAsociado = " Sin asignar";
		
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

public String getTipoUser() {
	return tipoUser;
}

public void setTipoUser (String tipoUser){
	this.nombre = tipoUser;
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

public void setmedicoAsociado(String medicoAsociado) {
	this.medicoAsociado = medicoAsociado ;
}

public String getmedicoAsociado() {
	return medicoAsociado;
}
}
