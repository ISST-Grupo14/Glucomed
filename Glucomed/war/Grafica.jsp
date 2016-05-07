
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
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
	<link rel='stylesheet' href='css/dashboardStyle.css'/>
   	<link rel='stylesheet' href='https://maxcdn.bootstrapcdn.com/font-awesome/4.6.1/css/font-awesome.min.css' />
	<meta name="viewport" content="width=device-width; initial-scale=1.0; user-scalable=no" />
	 <script src="js/dist/vis.js"></script>
  <link href="js/dist/vis.css" rel="stylesheet" type="text/css"/>
	<script>(function(i,s,o,g,r,a,m){i['GoogleAnalyticsObject']=r;i[r]=i[r]||function(){(i[r].q=i[r].q||[]).push(arguments)},i[r].l=1*new Date();a=s.createElement(o),m=s.getElementsByTagName(o)[0];a.async=1;a.src=g;m.parentNode.insertBefore(a,m)})(window,document,'script','//www.google-analytics.com/analytics.js','ga');ga('create', 'UA-61231638-1', 'auto');ga('send', 'pageview');</script>
	
	
</head>
<body>
<!-- ========= MENU ======== -->
	
	<!-- ========= MENU ======== -->
	<div class="menu">
		<div class="col-md-12">
			<a href="dashboard" class="boton-menu"><span class="only-movil"><i
					class="fa fa-align-left small"></i></span><br class="only-movil" />
				Inicio</a>
			<!-- fa fa-align-left fa fa-home-->
						<c:if test="${'medico' == tipoUser}">						
			<a href="listMedico" class="boton-menu"><span
				class="only-movil"><i class="fa fa-area-chart small"></i></span><br
				class="only-movil" /> 
				Pacientes</a> 
			</c:if>	
			
			<c:if test="${'paciente' == tipoUser}">
			<a href="insertData" class="boton-menu"><span class="only-movil"><i
					class="fa fa-pencil-square-o small"></i></span><br class="only-movil" />
				Introducir</a> 
			<a href="viewData" class="boton-menu"><span
				class="only-movil"><i class="fa fa-area-chart small"></i></span><br
				class="only-movil" /> 
				Historial</a> 
						
			<a href="listMedico" class="boton-menu"><span
				class="only-movil"><i class="fa fa-area-chart small"></i></span><br
				class="only-movil" /> 
				Medicos</a> 
				
			<a href="grafica" class="boton-menu"><span
				class="only-movil"><i class="fa fa-area-chart small"></i></span><br
				class="only-movil" /> 
				Grafica</a>	
				
			</c:if>	
			
			<a href="download" class="boton-menu"><span
				class="only-movil"><i class="fa fa-area-chart small"></i></span><br
				class="only-movil" /> 
				Descargar CSV</a>
				
				
			<a href="pruebaUser" class="boton-menu"><span
				class="only-movil"><i class="fa fa-area-chart small"></i></span><br
				class="only-movil" /> 
				pruebaUser</a>
			
			<!--   
			<a href="#" class="boton-menu"><span
				class="only-movil"><i class="fa fa fa-cog small"></i></span><br
				class="only-movil" /> Config. </a> 
			-->
			<a href="salir" class="boton-menu"><span
				class="only-movil"><i class="fa fa fa-hand-spock-o small"></i></span><br
				class="only-movil" /> Logout</a>
		</div>
	</div>
	
	<div id = "row">
  <div id = "col-md-12">
  <div id="inputs" class="clearfix">
    <input type="file" id="files" name="files[]" multiple />
  </div>
 </div>
</div>

 <div id = "row">
  <div id = "col-md-12">
 <div id="visualization"></div>

  <script src="http://ajax.googleapis.com/ajax/libs/jquery/1.8.2/jquery.min.js"></script>
  <script src="http://jquery-csv.googlecode.com/git/src/jquery.csv.js"></script>
  <script>
    $(document).ready(function() {
      if(isAPIAvailable()) {
        $('#files').bind('change', handleFileSelect);
      }
    });

    function isAPIAvailable() {
      if (window.File && window.FileReader && window.FileList && window.Blob) {
        return true;
      } else {
        document.writeln('The HTML5 APIs used in this form are only available in the following browsers:<br />');       
        return false;
      }
    }

    function handleFileSelect(evt) {
      var files = evt.target.files; // FileList object
      var file = files[0];

      // read the file metadata
//       var output = ''
//           output += '<span style="font-weight:bold;">' + escape(file.name) + '</span><br />\n';
//           output += ' - FileType: ' + (file.type || 'n/a') + '<br />\n';
//           output += ' - FileSize: ' + file.size + ' bytes<br />\n';
//           output += ' - LastModified: ' + (file.lastModifiedDate ? file.lastModifiedDate.toLocaleDateString() : 'n/a') + '<br />\n';

      // read the file contents
      printTable(file);

      // post the results
     // $('#list').append(output);
    }

    function printTable(file) {
        var reader = new FileReader();
        reader.readAsText(file);
        reader.onload = function(event){
        var csv = event.target.result;
        var data = $.csv.toArrays(csv);
        var total = [];
        var fechas = [];
        var horas = [];
        var valores = [];
        for(var row in data) {
          for(var item in data[row]) {
            total.push(data[row][item]);
          }
        }
        
       for(var x=0;x<total.length;x++) {
           horas.push(total[(2*x)+1]);
           total.splice((2*x)+1, 1);
           //document.writeln(total[x]);
           if(((x+1)%2) ==0){
            valores.push(total[x]);
           }
           else{
            fechas.push(total[x]);
           }
            
        }
       
       

        var container = document.getElementById('visualization');
        var dataset = new vis.DataSet();
         for (var i = 0; i <fechas.length-1; i++) {
             // alert(fechas[i]+"T"+horas[i].replace(/\s/g, ''));
              dataset.add({x: fechas[i]+"T"+horas[i].replace(/\s/g, ''), y: valores[i]});
              
        }
         
       
        var options = {
          start: fechas[0],
          end: fechas[fechas.length - 2],
          graphHeight: true,
          sort: false ,
          dataAxis: {
            left:{
              range:{
                min: 5,
                max: 130
            }
          }
          },
          //timeAxis: {scale: 'minute', step: 5},
          drawPoints: {
          size: 15,
          style: 'circle',
          }

        };
        var graph2d = new vis.Graph2d(container, dataset, options);
         
        };


      reader.onerror = function(){ alert('Unable to read ' + file.fileName); };
    }
  </script>
</body>
</html>

