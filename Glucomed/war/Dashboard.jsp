
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
   		<script type="text/javascript" src="/js/chart.js"></script>
		<meta charset="utf-8">
	</head>
	<body>				
			<!--  <h1>Hola <c:out value="${name}" /></h1>-->



		<section class="row-nav">
		<nav class="menu">
		<ul>
			<li><a href ="/Login.jsp">Login</a></li>
			<li><a href ="/Registro.jsp">Registro</a></li>
			<li class="active" href="/dashboard"><a href="" >DashBoard</a></li>
			<li><a href="/insertData.jsp">Introduce datos</a></li>
			<li><a href="/viewData.jsp">Ver datos</a></li>
			<li><a href="/salir.jsp">Ver datos</a></li>
		</ul>
		</nav>
		</section>
