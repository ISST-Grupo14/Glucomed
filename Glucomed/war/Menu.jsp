
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!-- ========= MENU ======== -->

<div class="menu">

	<div class="container">
	
		<a href="dashboard" class="boton-menu"><span class="only-movil">
		<i class="fa fa-align-left small"></i></span><br class="only-movil" />
		Inicio</a>
		<!-- fa fa-align-left fa fa-home-->
		
		<c:if test="${'medico' == tipoUser}">
			<a href="misPacientes" class="boton-menu"><span
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
					
			<a href="miMedico" class="boton-menu">
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
		
		<a href="buzon" class="boton-menu">
		<span class="only-movil">
			<i class="fa fa-area-chart small"></i>
		</span>
		<br class="only-movil" /> 
		Mensajes</a> 

		<a href="salir" class="boton-menu">
		<span class="only-movil">
			<i class="fa fa fa-hand-spock-o small"></i>
		</span>
		<br class="only-movil" /> 
		Logout</a>
			
	</div>
	
</div>