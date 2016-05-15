<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<%@ page import="com.google.appengine.api.blobstore.BlobstoreServiceFactory"%>
<%@ page import="com.google.appengine.api.blobstore.BlobstoreService"%>

<% BlobstoreService blobstoreService = BlobstoreServiceFactory.getBlobstoreService(); %>

<%@ page import="java.util.*" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta charset="UTF-8">
	<title>Glucomed</title>
	<link rel='stylesheet' href='css/dashboardStyle.css'/>
   	<link rel='stylesheet' href='https://maxcdn.bootstrapcdn.com/font-awesome/4.6.1/css/font-awesome.min.css' />
	<meta name="viewport" content="width=device-width; initial-scale=1.0; user-scalable=no" />
	
	<!--  ========= CSS PARTICULAR ========= -->
	<link rel='stylesheet' href='css/MensajesStyle.css'/>
	
	<script type='text/javascript' src="//ajax.googleapis.com/ajax/libs/jquery/1.9.1/jquery.min.js"></script>
	<script type="text/javascript" src="js/paginacion.js"></script>
	
	<script type='text/javascript'>//<![CDATA[
		$(window).load(function(){
			
			setTimeout(function(){
				window.location.reload(1);
			}, 10000);
			
			$(".ver_mensajes").animate({ scrollTop: $(document).height() }, 0);
			
			<c:if test="${comment_done == false}">
		        var message= sessionStorage.getItem('comment_data');
		        if (message!== null) $('#commentdata').val(message);
		        $('#commentdata').focus();
			</c:if>	
			
			<c:if test="${comment_done == true}">
				<c:set var="comment_done" scope="session" value="${false}"/>
			</c:if>	
			
		  	return false;
		  	
		});//]]> 
	
	    // Before refreshing the page, save the form data to sessionStorage
	    window.onbeforeunload = function() {
	        sessionStorage.setItem("comment_data", $('#commentdata').val());
	    }
	
	
	</script>

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

		    <!-- ==========AQUI VA TODO======== -->
			<br>
			<div class="content">
			
				<div class="background listado_pacientes"></div>
				
				<div class="left_block listado_pacientes">
					<div class="content">
						<div class="top_block usuario_origen">
							<div class="content">
								<p><br><c:out value = "${origenNombre}"/></p>
							</div>
						</div>
						<br>
						
						<c:forEach items="${listaPacientes}" var="paciente">

								<p> <a href="buzon?eMailPaciente=${paciente.email}"><c:out value = "${paciente.email}"/> </a> </p>

						</c:forEach>
						
						
					</div>
				</div>
				
				<!-- <div class="background mensajes"></div> -->
				
				<div class="center_block mensajes">
					<div class="content">
					
						<div class="top_block usuario_destino">
							<div class="content">
								<p><br><a href="buzon?accionEspecial=borrar&eMailPaciente=${destino}">
								<c:out value = "Chateando con: "/>
								<c:out value = "${destino}"/> </a></p>
							</div>
						</div>
						
						<div class="center_block ver_mensajes">
							<div class="content">
							
								<!-- Mensajes -->
														
								<c:forEach items="${mensajes}" var="mensaje">
									
									<c:if test="${mensaje.origen == origen}">
										<div class="mensaje_derecha">
										
											
											<div class="mensaje_derecha">
											<p> 
												<c:out value = "${mensaje.contenido}"/> 
												<c:out value = "${mensaje.fecha}"/> 
											</p>
	 										
	 										<br>
										</div>
									</c:if>
									
									<c:if test="${mensaje.origen != origen}">
										<div class="mensaje_izquierda">
							
											
											<p> 
												<c:out value = "${mensaje.contenido}"/> 
												<c:out value = "${mensaje.fecha}"/> 
											</p>
											<br> 

										</div>
									</c:if>	

								</c:forEach>

							</div>
						</div>
						
						<div class="bottom_block redactar_mensaje">
							<div class="content">
								<form action="buzon" method="post" id="mensajeform">
									<!-- <p><br>bla bla bla </p>  -->
									<br>
									<textarea name="comment" id="commentdata" form="mensajeform"></textarea>

									<div class="background enviar_mensaje">
									</div>
									<div class="right_block enviar_mensaje">
										
											<!-- <p> Enviar </p> -->
											<input type="hidden" name="origen" value="${origen}">
											<input type="hidden" name="destino" value="${destino}">
											<input type=image src="/img/icon-send.png" value="enviar" width="30" height="30">
										
									</div>
								</form>
							</div>
						</div>
						
					</div>
				</div>
				
			</div>
				
			<!-- ==========FIN AQUI VA TODO======== -->
			
			<br><br>
			
		   
	</div>
	</div>

</body>