function checkPassword()
{
    
    var pass1 = document.getElementById('password');
    var pass2 = document.getElementById('password_repeat');
   
    
    if(!(pass1.value == pass2.value)){
         alert("Las contraseñas no coinciden");
       
    }else{
    	return true;
    }
    }