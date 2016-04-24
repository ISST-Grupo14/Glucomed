<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>    
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<!DOCTYPE html>

<html>

	<head>
		<meta charset="UTF-8">
		<title>Glucomed</title>
		<meta name="viewport" content="width=device-width; initial-scale=1.0; user-scalable=no" />
		<link rel="stylesheet" type="text/css" href="css/login-register.css" /> 
		<link rel='stylesheet' href='css/dashboardStyle.css' media='none'/>
	</head>

	<body>
	
		<section class="container">
		
			<div class="login">

				<h1>GLUCOMED</h1>
				<h2>Sistema de medición de glucosa</h2>
				<h3>Registro de usuario</h3>
      
				<form method="post" action="registro">
				
					<p class="input-titulo">Nombre: <br><input type="text" name="nombre" value="" placeholder="Nombre"> </p>
					<p class="input-titulo">Apellidos: <br><input type="text" name="apellidos" value="" placeholder="Apellidos"></p>
					<p class="input-titulo">Tipo de usuario: <br>
					<select type="text" name="tipoUser">
    					<option value="medico">Médico</option>
    					<option value="paciente">Paciente</option>
    				</select></p>
  
  <!--        
				    <p>Fecha de nacimiento: <input type="text" name="fecha" placeholder="dd/mm/aaaa"></input></p>
					<p>Tipo de usuario: <input type="text" name="usuario" placeholder="Médico/Paciente"></input></p>-->
        
					<p class="input-titulo">Correo electrónico: <br><input type="text" name="email" value="" placeholder="ejemplo@ejemplo.com"></p>
					<p class="input-titulo">Contraseña: <br><input type="password" name="password" value="" placeholder=""></p>
					<p class="input-titulo">Repita la contraseña: <br><input type="password" name="password_repeat" value="" placeholder=""></p>
        
        			<p class="login-forgot" style="font-size:18px; font-family:Arial; font-weight:bold; color:#ff0000;"> 
						<c:out value = "${error_code_registro}" /> 
					</p>
        
					<p class="submit">
						<input type="submit" name="commit" value="Aceptar">
						<input type="button" name="registro" value="Volver" onClick="history.go(-1);return true;" >
					</p>
				</form>

				
			</div>
			
		</section>
	
		
		<section class="about">
			<p class="about-author">
			&copy; 2015&ndash;2016 <a>ISST-Grupo 14</a>
		</section>
		
	</body>

</html>
