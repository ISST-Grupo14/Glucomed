/**
 * 
 */

function checkPassword(){
	
	var pass1 = document.getElementById("password1");
	var pass2 = document.getElementById("password_repear");
	
	if(pass1==pass2){
		return true;
	}else{
		alert("Las contraseñas no coinciden");
	}
}