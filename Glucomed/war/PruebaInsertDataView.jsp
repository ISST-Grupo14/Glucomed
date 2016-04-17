<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<!DOCTYPE html>

<html>

<head>
	<meta charset="UTF-8">
	<title>Sistema de medición de glucosa Glucomer.</title>
	<meta name="viewport" content="width=device-width; initial-scale=1.0; user-scalable=no" />
   	<link rel='stylesheet' href='css/dashboardStyle.css' />
   	<link rel='stylesheet' href='https://maxcdn.bootstrapcdn.com/font-awesome/4.6.1/css/font-awesome.min.css' />
</head>

<body>

	<div class="container">
		<div class="container-tabla">

			<!-- ========= BANNER ======== -->
			<div class="header">
				<span class="only-movil" style="font-size: 100%; color: #FFFFFF">GLUCOMED</span>
				<span class="only-tablet" style="font-size: 100%; color: #CCCCCC">GLUCOMED</span>
				<span class="only-screen" style="font-size: 100%; color: #CCCCCC">GLUCOMED</span>
			</div>

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
						class="only-movil" /> Historial</a> 
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

		
		    <div class="caja-titulo col-12"><span class="titulo">Inicio</span></div>
		    
		    <div class="col-8">
		    <!-- ==========AQUI VA TODO======== -->
		    
		    	<form method="post" action="insertData">
		        	<p class="input-titulo"><input type="text" name="fecha" value="" placeholder="Fecha"></p>
					<p class="input-titulo"><input type="text" name="hora" value="" placeholder="Hora"></p>
					<p class="input-titulo"><input type="text" name="valorGlucosa" value="" placeholder="valorGlucosa"></p>
		        	<p class="submit">
		        		<input type="submit" name="commit" value="Aceptar">
		        		<input type="submit" name="commit" value="Volver" onClick="history.go(-1);return true;">
	        		</p>
		    	</form>
				
			<!-- ==========FIN AQUI VA TODO======== -->
		    </div>

		
		    <div class='col-12 paginacion'></div>
		
		    <div class="footer col-12">
				<p></p>
		    </div>
		
		  
		</div>
	</div>
		
		    	

</body>

</html>