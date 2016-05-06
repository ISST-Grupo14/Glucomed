<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<%@ page import="com.google.appengine.api.blobstore.BlobstoreServiceFactory"%>
<%@ page import="com.google.appengine.api.blobstore.BlobstoreService"%>

<% BlobstoreService blobstoreService = BlobstoreServiceFactory.getBlobstoreService(); %>

<%@ page import="java.util.*" %>


	<link rel='stylesheet' href='css/dashboardStyle.css'/>
   	<link rel='stylesheet' href='https://maxcdn.bootstrapcdn.com/font-awesome/4.6.1/css/font-awesome.min.css' />
	<meta name="viewport" content="width=device-width; initial-scale=1.0; user-scalable=no" />
	
	<script type='text/javascript' src="//ajax.googleapis.com/ajax/libs/jquery/1.9.1/jquery.min.js"></script>
	<script type="text/javascript" src="js/paginacion.js"></script>



	<!-- ========= MENU ======== -->

	<div class="menu">
	
		<div class="container">
		
			<a href="dashboard" class="boton-menu"><span class="only-movil">
			<i class="fa fa-align-left small"></i></span><br class="only-movil" />
			Inicio</a>
			<!-- fa fa-align-left fa fa-home-->
			
			<c:if test="${'medico' == tipoUser}">
				<a href="listMedico" class="boton-menu"><span
				class="only-movil"><i class="fa fa-area-chart small"></i></span><br
				class="only-movil" /> 
				Mis Pacientes</a> 
			</c:if>	
			
			<c:if test="${'paciente' == tipoUser}">
			
				<a href="insertData" class="boton-menu">
				<span class="only-movil">
					<i class="fa fa-pencil-square-o small"></i>
				</span>
				<br class="only-movil" />
				Introducir</a>
				 
				<a href="viewData" class="boton-menu">
				<span class="only-movil">
					<i class="fa fa-area-chart small"></i>
				</span>
				<br class="only-movil" /> 
				Historial</a> 
						
				<a href="listMedico" class="boton-menu">
				<span class="only-movil">
					<i class="fa fa-area-chart small"></i>
				</span>
				<br class="only-movil" /> 
				Mi Medico</a> 
				
				<a href="grafica" class="boton-menu">
				<span class="only-movil">
					<i class="fa fa-area-chart small"></i>
				</span>
				<br class="only-movil" /> 
				Grafica</a>	
			</c:if>	

			<a href="salir" class="boton-menu">
			<span class="only-movil">
				<i class="fa fa fa-hand-spock-o small"></i>
			</span>
			<br class="only-movil" /> 
			Logout</a>
				
		</div>
		
	</div>
	
