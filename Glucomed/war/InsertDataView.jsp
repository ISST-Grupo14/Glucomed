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
				<h3>Credenciales</h3>

				<form method="post" action="insertData">
		        	<p><input type="text" name="fecha" value="" placeholder="Fecha"></p>
					<p><input type="text" name="hora" value="" placeholder="Hora"></p>
					<p><input type="text" name="valorGlucosa" value="" placeholder="valorGlucosa"></p>
		        	<p class="submit">
		        		<input type="submit" name="commit" value="Aceptar">
		        		<input type="submit" name="commit" value="Volver" onClick="history.go(-1);return true;">
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