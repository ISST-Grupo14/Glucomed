<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>

<style type="text/css">
div.clearfix {
	position: relative;
	top: 40px;
	right: 0;
	width: 0;
	border: 3px solid #73AD21;
}
</style>

<meta charset="UTF-8">
<title>Glucomed</title>
<link rel='stylesheet' href='css/dashboardStyle.css' />
<link rel='stylesheet'
	href='https://maxcdn.bootstrapcdn.com/font-awesome/4.6.1/css/font-awesome.min.css' />
<meta name="viewport"
	content="width=device-width; initial-scale=1.0; user-scalable=no" />
<link href="js/dist/vis.css" rel="stylesheet" type="text/css" />

<!-- SCRIPTS PARA GRAFICA -->
<script src="js/dist/vis.js"></script>
<script>
	(function(i, s, o, g, r, a, m) {
		i['GoogleAnalyticsObject'] = r;
		i[r] = i[r] || function() {
			(i[r].q = i[r].q || []).push(arguments)
		}, i[r].l = 1 * new Date();
		a = s.createElement(o), m = s.getElementsByTagName(o)[0];
		a.async = 1;
		a.src = g;
		m.parentNode.insertBefore(a, m)
	})(window, document, 'script', '//www.google-analytics.com/analytics.js',
			'ga');
	ga('create', 'UA-61231638-1', 'auto');
	ga('send', 'pageview');
</script>
<script
	src="http://ajax.googleapis.com/ajax/libs/jquery/1.8.2/jquery.min.js"></script>
<script src="http://jquery-csv.googlecode.com/git/src/jquery.csv.js"></script>
<script type="text/javascript" src="js/drawGraphic.js"></script>
<!-- FIN SCRIPTS PARA GRAFICA -->

</head>

<body>

	<!-- ========= MENU ======== -->

	<jsp:include page="Menu.jsp" />

	<!-- ======= FIN MENU ====== -->

	<div class="container">

		<div class="container-tabla">

			<!-- ========= BANNER ======== -->

			<div class="header">
				<img class="image-header only-movil" src="img/logo-movil.png" /> <img
					class="image-header only-screen" src="img/logo-screen.png" />
			</div>

			<div class="caja-titulo col-12">
				<span class="titulo">Dashboard</span>
			</div>

			<div class="section col-12">

				<!-- ==========AQUI VA TODO======== -->

				<div id="row">
					<div id="col-md-12">
						<div id="inputs" class="clearfix">

							<form name="formDatos">
								<input type="hidden" id="string_datos" name="string_datos"
									value=<c:out value="${datos_string}"/>>
								<!--  <button onclick="mostrarAlert()">Try it</button> -->
							</form>

						</div>
					</div>
				</div>

				<div id="row">
					<div id="col-md-12">
						<div id="visualization"></div>
					</div>
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

