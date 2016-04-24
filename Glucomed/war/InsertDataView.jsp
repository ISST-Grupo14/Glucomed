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
			<a href="guardar" class="boton-menu"><span
				class="only-movil"><i class="fa fa-area-chart small"></i></span><br
				class="only-movil" /> 
				Descargar csv</a>
				
			<!--   
			<a href="#" class="boton-menu"><span
				class="only-movil"><i class="fa fa fa-cog small"></i></span><br
				class="only-movil" /> Config. </a> 
			-->
			<a href="salir" class="boton-menu"><span
				class="only-movil"><i class="fa fa fa-hand-spock-o small"></i></span><br
				class="only-movil" /> Logout</a>
		</div>
	</div>

<!-- ========= CONTENEDOR ======== -->

	<div class="container">
		<div class="container-tabla">

			<!-- ========= BANNER ======== -->
			<div class="header">
		    	<img class="image-header only-movil" src="img/logo-movil.png" />
		    	<img class="image-header only-screen" src="img/logo-screen.png" />
    		</div>
		
		    <div class="caja-titulo col-12"><span class="titulo">Introduce Datos</span></div>
		    
		    <div class="section col-12">
		    <!-- ==========AQUI VA TODO======== -->
		    
		    	<div class="col-4"></div>
		    
		    	<div class="col-4">
		    	<form method="post" action="insertData">
		        	<p class="input-titulo"> Fecha:<input type="text" name="fecha" value="" placeholder="dd/mm/aaaa"></p>
					<p class="input-titulo"> Hora: <input type="text" name="hora" value="" placeholder="hh:mm"></p>
					<p class="input-titulo"> Valor(mg/l): <input type="text" name="valorGlucosa" value="" placeholder="105"></p>
		        	<p class="submit">
		        		<input type="submit" name="commit" value="Aceptar">
		        		<input type="button" name="commit" value="Volver" onClick="history.go(-1);return true;">
	        		</p>
	        		<p class="login-forgot" style="font-size:18px; font-family:Arial; font-weight:bold; color:#ff0000;"> 
						<c:out value = "${error_code_registro}" /> 
					</p>
		    	</form>
		    	</div>
				
			<!-- ==========FIN AQUI VA TODO======== -->
		    </div>

		
		    <div class='col-12 paginacion'>
		    <!-- ========= PAGINACION ======== -->
		    <!-- ========= FIN PAGINACION ======== -->
		    </div>
		
		    <div class="footer col-12">
				<p></p>
		    </div>
		
		  
		</div>
	</div>
		
		    	

</body>

</html>