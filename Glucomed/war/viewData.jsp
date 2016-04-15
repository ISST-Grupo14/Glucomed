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


<p>Valores Introducidos</p>
	<c:if test = "${user != null}">
		<c:out value = "${user}" />
	</c:if>

<c:forEach items="${tfgs}" var="tfg">
	<tr>
		<td> <c:out value = "${tfg.fecha}"/> </td>
		<td> <c:out value = "${tfg.hora}"/> </td>
		<td> <c:out value = "${tfg.valorGlucosa}"/> </td>
	</tr>
</c:forEach>


</body>
</html>