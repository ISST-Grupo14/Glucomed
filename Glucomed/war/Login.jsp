<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
   <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>Sistema de medición de glucosa Glucomer.</title>
	<link rel="stylesheet" type="text/css" href="css/main.css" /> 
</head>

<body>
  <section class="container">
    <div class="login">
      <h1>GLUCOMED</h1>
      <h2>Sistema de medición de glucosa</h2>
      <h3>Credenciales</h3>
      <form method="post" action="/">
        <p><input type="text" name="login" value="" placeholder="Usuario/e-mail"></p>
        <p><input type="password" name="contraseña" value="" placeholder="Contraseña"></p>
        <p class="recordar">
          <label>
            <input type="checkbox" name="recordar" id="remember_me">Recordar mis datos en este navegador
   		</label>
        </p>
        <p class="submit"><input type="submit" name="commit" value="Aceptar"></p>
        <p class="registro"><a href="/Registro.jsp"> Registro <a/></p>
     

   	 <p class="login-forgot"> <a href="Login.jsp">He olvidado la contraseña</a> </p>
    </form>
    </div>
  </section>

  <section class="about">
    <p class="about-author">
      &copy; 2015&ndash;2016 <a>ISST-Grupo 14</a>
  </section>
</body>



</body>
</html>