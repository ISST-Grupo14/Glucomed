<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
   <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>Sistema de medición de glucosa Glucomer.</title>
	<link rel="stylesheet" type="text/css" href="css/main.css" /> 
</head>

<body>

	<form method="post" action="insertData">
        
        <p><input type="text" name="fecha" value="" placeholder="Fecha"></p>
		<p><input type="text" name="hora" value="" placeholder="Hora"></p>
		<p><input type="text" name="valorGlucosa" value="" placeholder="valorGlucosa"></p>
        </p>
        <p class="submit"><input type="submit" name="commit" value="Aceptar"></p>
    </form>

</body>



</body>
</html>