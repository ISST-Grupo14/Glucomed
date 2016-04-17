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
	<script type='text/javascript' src="//ajax.googleapis.com/ajax/libs/jquery/1.9.1/jquery.min.js"></script>
	<script type='text/javascript' src="//netdna.bootstrapcdn.com/bootstrap/3.0.0/js/bootstrap.min.js"></script>
	
	<!-- JavaScript jQuery code from Bootply.com editor  -->
        
	<script type='text/javascript'>
        
	$.fn.pageMe = function(opts){
    	var $this = this,
        defaults = {
            perPage: 7,
            showPrevNext: false,
            hidePageNumbers: false
        },
        settings = $.extend(defaults, opts);
    
    	var listElement = $this;
    	var perPage = settings.perPage; 
    	var children = listElement.children();
    	var pager = $('.pager');
    
    	if (typeof settings.childSelector!="undefined") {
        	children = listElement.find(settings.childSelector);
    	}
    
    	if (typeof settings.pagerSelector!="undefined") {
	        pager = $(settings.pagerSelector);
    	}
    
    	var numItems = children.size();
    	var numPages = Math.ceil(numItems/perPage);

    	pager.data("curr",0);
    
    	if (settings.showPrevNext){
        	$('<li><a href="#" class="prev_link">«</a></li>').appendTo(pager);
    	}
    
    	var curr = 0;
    	while(numPages > curr && (settings.hidePageNumbers==false)){
        	$('<li><a href="#" class="page_link">'+(curr+1)+'</a></li>').appendTo(pager);
        	curr++;
    	}
    
    	if (settings.showPrevNext){
        	$('<li><a href="#" class="next_link">»</a></li>').appendTo(pager);
    	}
    
    	pager.find('.page_link:first').addClass('active');
    	pager.find('.prev_link').hide();
    	
    	if (numPages<=1) {
        	pager.find('.next_link').hide();
    	}
    	
  		pager.children().eq(1).addClass("active");
    
    	children.hide();
    	children.slice(0, perPage).show();
    
    	pager.find('li .page_link').click(function(){
        	var clickedPage = $(this).html().valueOf()-1;
        	goTo(clickedPage,perPage);
        	return false;
    	});
    	
    	pager.find('li .prev_link').click(function(){
        	previous();
        	return false;
    	});
    	
    	pager.find('li .next_link').click(function(){
        	next();
        	return false;
    	});
    	
    
    function previous(){
        var goToPage = parseInt(pager.data("curr")) - 1;
        goTo(goToPage);
    }
     
    function next(){
        goToPage = parseInt(pager.data("curr")) + 1;
        goTo(goToPage);
    }
    
    function goTo(page){
        var startAt = page * perPage,
            endOn = startAt + perPage;
        
        children.css('display','none').slice(startAt, endOn).show();
        
        if (page>=1) {
            pager.find('.prev_link').show();
        }
        else {
            pager.find('.prev_link').hide();
        }
        
        if (page<(numPages-1)) {
            pager.find('.next_link').show();
        }
        else {
            pager.find('.next_link').hide();
        }
        
        pager.data("curr",page);
      	pager.children().removeClass("active");
        pager.children().eq(page+1).addClass("active");
    
    }
};

$(document).ready(function(){
    
  $('#myTable').pageMe({pagerSelector:'#myPager',showPrevNext:true,hidePageNumbers:false,perPage:4});
    
});
        
        </script>
	
	
	
</head>

<body>


		<section class="viewdata_section">
			<div class="viewdata_div">
			
				<h1>GLUCOMED</h1>
				<h2>Sistema de medición de glucosa</h2>
				<h3>Datos Paciente</h3>
				

        <table class="table table-hover">
          <thead>
  
						<tr>
						<td id="thead_table" colspan="4">Usuario: <c:out value = "${email}"/></td>
						</tr>

            <tr>             
			  <td><strong>Fecha</strong></td>
			  <td><strong>Hora</strong></td>
			  <td><strong>Valor</strong></td>
            </tr>
          </thead>

          <tbody id="myTable">
          
					<c:forEach items="${pacienteDatos}" var="datos">
						<tr>
							<!--  <td> <c:out value = "${datos.email}"/> </td> -->
							<td> <c:out value = "${datos.fecha}"/> </td>
							<td> <c:out value = "${datos.hora}"/> </td>
							<td> <c:out value = "${datos.valorGlucosa}"/> </td>
						</tr>
					</c:forEach>
          
          </tbody>
        </table>
        
        
      <div class="col-md-12 text-center">
      <ul class="pagination pagination-lg pager" id="myPager"></ul>
      </div>
  

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