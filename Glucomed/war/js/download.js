			$(document).ready(function(){
				$("#csv_form").submit(function(event) {
					event.preventDefault();
					var csv_data = jQuery("#csv_data").val();
					var file_name = jQuery("#file_name").val();
					jQuery.post( "csv.jsp", {
						csv_data: csv_data,
						file_name: file_name
					})
				})
				$("#xx").click(function(){
					var hidden_form =[
						'<form action="csv.jsp" method="POST">',
							'<input type="hidden" name="csv_data" value="AA,BBB,CCCC"></input>',
							'<input type="hidden" name="file_name" value="some.csv"></input>',
						'</form>'
					].join("");
					$(hidden_form).submit();
					console.log("done")
				})
			})
        