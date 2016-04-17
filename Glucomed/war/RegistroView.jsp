<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>    
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
				<h3>Registro de usuario</h3>
      
				<form method="post" action="registro">
				
					<p>Nombre: <input type="text" name="nombre" value="" placeholder="Nombre"> </p>
					<p>Apellidos: <input type="text" name="apellidos" value="" placeholder="Apellidos"></p>
        
					<!--   <p>Fecha de nacimiento: <input type="text" name="fecha" placeholder="dd/mm/aaaa"></input></p>
					<p>Tipo de usuario: <input type="text" name="usuario" placeholder="Médico/Paciente"></input></p>-->
        
					<p>Correo electrónico: <input type="text" name="email" value="" placeholder="ejemplo@ejemplo.com"></p>
					<p>Contraseña: <input type="password" name="password" value="" placeholder=""></p>
					<p>Repita la contraseña: <input type="password" name="password_repeat" value="" placeholder=""></p>
        
					<p class="submit">
						<input type="submit" name="commit" value="Aceptar">
						<input type="button" name="registro" value="Volver" onClick="history.go(-1);return true;" >
					</p>
				</form>

				
			</div>
			
		</section>
		
		<div class="error">
			<p> <c:out value = "${error_code_registro}" /> </p>
		</div>
		
		<section class="about">
			<p class="about-author">
			&copy; 2015&ndash;2016 <a>ISST-Grupo 14</a>
		</section>
		
	</body>

</html>
