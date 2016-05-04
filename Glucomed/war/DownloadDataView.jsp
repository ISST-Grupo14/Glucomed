<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<!DOCTYPE html>

<html>

<head>
	<meta charset="UTF-8">
	<title>Glucomed</title>
	<link rel='stylesheet' href='css/dashboardStyle.css'/>
   	<link rel='stylesheet' href='https://maxcdn.bootstrapcdn.com/font-awesome/4.6.1/css/font-awesome.min.css' />
	<meta name="viewport" content="width=device-width; initial-scale=1.0; user-scalable=no" />
	
	<script type='text/javascript' src="//ajax.googleapis.com/ajax/libs/jquery/1.9.1/jquery.min.js"></script>
	<script type="text/javascript" src="js/download.js"></script>

</head>

<body>

	<!-- ========= MENU ======== -->
	<div class="menu">
		<div class="container">
			<a href="dashboard" class="boton-menu"><span class="only-movil"><i
					class="fa fa-align-left small"></i></span><br class="only-movil" />
				Inicio</a>
			<!-- fa fa-align-left fa fa-home-->
			<a href="insertData" class="boton-menu"><span class="only-movil"><i
					class="fa fa-pencil-square-o small"></i></span><br class="only-movil" />
				Introducir</a> 
			<a href="viewData" class="boton-menu"><span
				class="only-movil"><i class="fa fa-area-chart small"></i></span><br
				class="only-movil" /> 
				Historial</a> 
			<a href="listMedico" class="boton-menu"><span
				class="only-movil"><i class="fa fa-area-chart small"></i></span><br
				class="only-movil" /> 
				Medico</a>
			<a href="download" class="boton-menu"><span
				class="only-movil"><i class="fa fa-area-chart small"></i></span><br
				class="only-movil" /> 
				Descargar CSV</a>
				
				<a href="grafica" class="boton-menu"><span
				class="only-movil"><i class="fa fa-area-chart small"></i></span><br
				class="only-movil" /> 
				Grafica</a>	
				
			<!--  <a href="#" class="boton-menu"><span
				class="only-movil"><i class="fa fa fa-cog small"></i></span><br
				class="only-movil" /> Config. </a> 	-->
			<a href="salir" class="boton-menu"><span
				class="only-movil"><i class="fa fa fa-hand-spock-o small"></i></span><br
				class="only-movil" /> Logout</a>
		</div>
	</div>

	<!-- =========FIN MENU ======== -->
		
	  
			    <div class="caja-titulo col-12"><span class="titulo">Historial de Datos</span></div>
		    
		    <div class="section col-12">
		    <!-- ==========AQUI VA TODO======== -->
		    	<div class="col-4 view-data"></div>
		    	
		    	<div class="col-4 view-data">

		<h2>Type the data you want to save as csv:</h2>

		<form name="csv_form" method="post" action="csv.jsp">
			<textarea name="csv_data" cols="80" rows="10">		    	
				                      
			Fecha,Hora,Valor,
			<c:forEach items="${pacienteDatos}" var="datos">
			<c:out value = "${datos.fecha}"/>,<c:out value = "${datos.hora}"/>,<c:out value = "${datos.valorGlucosa}"/> 
			</c:forEach>
							
			</textarea>
			<br>
			Filename:<input type="text" name="file_name" value="data.csv"/>
			<br>
			<input type="submit"> </input>
		</form>
	</div>
	</div>
	<!--  
		<h2>Test Simulating a hidden form with jQuery</h2>
		<input type="button" value="Using Hidden Form" id="xx"></input>
	-->
	
	<div class="footer col-12">
				<p></p>
		    </div>
 	

</body>

</html>