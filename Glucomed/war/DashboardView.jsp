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

</head>

<body>

	<!-- ========= MENU ======== -->
	
	<jsp:include page="Menu.jsp" />

	<!-- ======= FIN MENU ====== -->

	<!-- ========= CONTENEDOR ======== -->

	<div class="container">
	
		<div class="container-tabla">

			<!-- ========= BANNER ======== -->
			
			<div class="header">
		    	<img class="image-header only-movil" src="img/logo-movil.png" />
		    	<img class="image-header only-screen" src="img/logo-screen.png" />
    		</div>
		
		    <div class="caja-titulo col-12"><span class="titulo">Dashboard</span></div>
		    
		    <div class="section col-12">
		    
		    <!-- ==========AQUI VA TODO======== -->
		    
		    	<div class="col-4"></div>
		    
		    	<div class="col-4">
		    	
		    	<h1 style="text-align:center; color:#253A7A">Hola <c:out value = "${nombre}"/>, Bienvenido a: </h1>
				
				<img alt="" src="img/logo.png" style="width:100%;">
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