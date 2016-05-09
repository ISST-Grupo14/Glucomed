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
		
		    <div class="caja-titulo col-12">
		    	<span class="titulo">Listado de Medicos</span>
	    	</div>

	    	<div class="section col-12">
		    
	    		<!-- ==========AQUI VA TODO======== -->
	    		
				<div class="col-4 view-data"></div>
    			<div class="col-4 view-data">
    			
					<c:if test="${'true' == hayMedico}">
		
	    				<table>
							<thead>
		            			             
								<td colspan="4"><strong>Datos Medico Asignado</strong></td>
		            			
		            			<tr>             
									<td><strong>Nombre</strong></td>
									<td><strong>Apellidos</strong></td>
									<td><strong>Mail</strong></td>
									<td><strong>Accion</strong></td>
		            			</tr>
							</thead>
			
							<tbody id="myTable">

								<tr>
									<td> <c:out value = "${viewMedico.nombre}"/> </td>
									<td> <c:out value = "${viewMedico.apellidos}"/> </td>
									<td> <c:out value = "${viewMedico.email}"/> </td>
									<td>
							        	<form method="post" action="miMedico">
											<input type="hidden" name="emailMedico" value="${user.email}">
											<input type="hidden" name="accion" value="Eliminar">
											<input type="submit" name="commit" value="Eliminar">
										</form>
									</td>

								</tr>
													
							</tbody>
									
	        			</table>
		
					</c:if>	
    			
    				<c:if test="${'false' == hayMedico}">

	    				<table>
							<thead>
		            			<tr>             
									<td><strong>Nombre</strong></td>
									<td><strong>Apellidos</strong></td>
									<td><strong>Mail</strong></td>
									<td><strong>Accion</strong></td>
		            			</tr>
							</thead>
			
							<tbody id="myTable">
				
								<c:forEach items="${viewListaMedico}" var="user">
								
									<tr>
										<td> <c:out value = "${user.nombre}"/> </td>
										<td> <c:out value = "${user.apellidos}"/> </td>
										<td> <c:out value = "${user.email}"/> </td>
										<td>
								        	<form method="post" action="miMedico">
												<input type="hidden" name="emailMedico" value="${user.email}">
												<input type="hidden" name="accion" value="Seleccionar">
												<input type="submit" name="commit" value="Seleccionar">
											</form>
										</td>
										
										
	
										<!-- 
										<c:choose>
											<c:when test="${MedicoPaciente == user.email}">
							    				<td> <input type="button" name="commit" value="Seleccionar" onclick="window.location.href = '/SeleccionarMedico';"/ > </td>
							  				</c:when>
							  				<c:otherwise>
							    				<td> <input type="button" name="commit" value="Eliminar" onclick="window.location.href = '/EliminarMedico';"/ > </td>
							  				</c:otherwise>
										</c:choose>
							 			-->
	
									</tr>
									
								</c:forEach>
				
							</tbody>
									
	        			</table>
	        			
					</c:if>
        			
        			<br>

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