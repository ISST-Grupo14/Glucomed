<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<%@ page import="com.google.appengine.api.blobstore.BlobstoreServiceFactory"%>
<%@ page import="com.google.appengine.api.blobstore.BlobstoreService"%>

<% BlobstoreService blobstoreService = BlobstoreServiceFactory.getBlobstoreService(); %>

<%@ page import="java.util.*" %>

<!DOCTYPE html>

<html>

<head>
	<meta charset="UTF-8">
	<title>Glucomed</title>
	<link rel='stylesheet' href='css/dashboardStyle.css'/>
   	<link rel='stylesheet' href='https://maxcdn.bootstrapcdn.com/font-awesome/4.6.1/css/font-awesome.min.css' />
	<meta name="viewport" content="width=device-width; initial-scale=1.0; user-scalable=no" />
	
	<script type='text/javascript' src="//ajax.googleapis.com/ajax/libs/jquery/1.9.1/jquery.min.js"></script>
	<script type="text/javascript" src="js/paginacion.js"></script>

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
		
		    <div class="caja-titulo col-12"><span class="titulo">Historial de Datos</span></div>
		    
		    <div class="section col-12">
		    
		    <!-- ==========AQUI VA TODO======== -->
		    
		    	<div class="col-4 view-data"></div>
		    	
		    	<div class="col-4 view-data">
		    	
		    	<table>
					<thead>
						<!--  <tr>
							<td>Usuario: <c:out value = "${email}"/></td>
						</tr> -->
			            <tr>             
							<td><strong>Fecha</strong></td>
							<td><strong>Hora</strong></td>
							<td><strong>Valor</strong></td>
			            </tr>
					</thead>
		
					<tbody id="myTable">
					
						<c:forEach items="${pacienteDatos}" var="datos">
							<tr>
								<td> <c:out value = "${datos.fecha}"/> </td>
								<td> <c:out value = "${datos.hora}"/> </td>
								<td> <c:out value = "${datos.valorGlucosa}"/> </td>
							</tr>
						</c:forEach>
					
					</tbody>
		        </table>
		        	
	      			<p class="submit">
	      				<br>
	      				<input type="button" value="Guardar" onclick="window.location.href = 'saveFile.jsp';"/> 
		   				<input type="button" name="commit" value="Descargar CSV" onclick="window.location.href = '/guardar';"/ >
				    </p>
					    
		        	<form action="<%= blobstoreService.createUploadUrl("/subir") %>" method="post" enctype="multipart/form-data">
		        						    
						<p class="submit">
			       		<br>
						<input type="file" name="myFile">
						</p>
						
						<p class="submit">
						<br>
						<input type="submit" value="Subir CSV">
						</p>
						
					</form>

		    	</div>
				
				<!-- ==========FIN AQUI VA TODO======== -->

		    </div>

		
		    <div class='col-12 paginacion'>
		    
		    	<!-- ========= PAGINACION ======== -->
		    	<ul class="pagination pagination-lg pager" id="myPager"></ul>
		    	<!-- ========= FIN PAGINACION ======== -->
		    	
		    </div>
		
		    <div class="footer col-12">
				<p></p>
			</div>

		</div>
		
	</div>

</body>

</html>
