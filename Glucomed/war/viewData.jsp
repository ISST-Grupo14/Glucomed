<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
   <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>Sistema de medición de glucosa Glucomed.</title>
	<link rel="stylesheet" type="text/css" href="css/main.css" /> 
</head>

<body>


		<section class="viewdata_section">
			<div class="viewdata_div">
			
				<h1>GLUCOMED</h1>
				<h2>Sistema de medición de glucosa</h2>
				<h3>Datos Paciente</h3>
				
				<table>
					<thead>
						<tr>
						<td colspan="4">Usuario: <c:out value = "${email}"/></td>
						</tr>
					</thead>
					<!--  <tfoot> -->
					<!--  	<tr> -->
					<!--  	<td colspan="4"><c:out value = "${email}"/></td> -->
					<!--  	</tr> -->
					<!--  </tfoot> -->
					
					<tr>
							<!--  <td><strong>Email</strong></td> -->
							<td><strong>Fecha</strong></td>
							<td><strong>Hora</strong></td>
							<td><strong>Valor</strong></td>
					</tr>
					<c:forEach items="${pacienteDatos}" var="datos">
						<tr>
							<!--  <td> <c:out value = "${datos.email}"/> </td> -->
							<td> <c:out value = "${datos.fecha}"/> </td>
							<td> <c:out value = "${datos.hora}"/> </td>
							<td> <c:out value = "${datos.valorGlucosa}"/> </td>
						</tr>
					</c:forEach>
				
				</table>

	        	<p class="submit">
	        		<input type="submit" name="commit" value="Introduce Datos" onclick="location.href='/insertData'">
	        		<input type="submit" name="commit" value="Volver" onClick="history.go(-1);return true;">
        		</p>
				
			</div>
			
		</section>
		
		<section class="about">
			<p class="about-author"> &copy; 2015&ndash;2016 <a>ISST-Grupo 14</a>
		</section>

</body>

</html>