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
				
					<tr>
						<td>Email</td>
						<td><c:out value = "${email}" /></td>
					</tr>
					
					<tr>
						<td>Fecha</td>
						<td><c:out value = "${fecha}" /></td>
					</tr>
					
					<tr>
						<td>Hora</td>
						<td><c:out value = "${hora}" /></td>
					</tr>
					
					<tr>
						<td>Valor de Glucosa</td>
						<td><c:out value = "${valorGlucosa}" /></td>
					</tr>
					
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