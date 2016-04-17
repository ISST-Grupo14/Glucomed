
<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@page isELIgnored="false"%>

<html>

	<head>
		<title>Dashboard</title>
		<link rel="stylesheet" type="text/css" href="css/main.css" />
		<script type="text/javascript" src="https://www.google.com/jsapi"></script>
	  	
		<meta charset="utf-8">
	</head>
	
	<body>
					
		<!--  <h1>Hola <c:out value="${name}" /></h1>-->
		
		<section class="row-nav">
			<div class="main_menu">
			
				<h1>GLUCOMED</h1>
				<h2>Sistema de medición de glucosa</h2>
				<h3>Menu de control</h3>
				
				<ul>
					<!--<li><a href ="/login">Login</a></li>-->
					<!--<li><a href ="/registro">Registro</a></li>-->
					<p><a href="/dashboard">Dashboard</a></p>
					<p><a href="/insertData">Introduce datos</a></p>
					<p><a href="/viewData">Ver datos</a></p>
					<p><a href="/salir">Salir</a></p>
				</ul>
			</div>
		</section>
		
		<section class="about">
			<p class="about-author"> &copy; 2015&ndash;2016 <a>ISST-Grupo 14</a>
		</section>
		
	</body>
	
</html>