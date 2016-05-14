$(document).ready(function() {
	var datosString = document.formDatos.string_datos.value;
	printTable2(datosString);
});

function printTable2(datos) {
	var data = datos.split(";");
	var prueba = [];
	var total = [];
	var fechas = [];
	var horas = [];
	var valores = [];

	// parseamos los datos
	for ( var row in data) {
		prueba = data[row].split(",");
		for ( var item in prueba) {
			total.push(prueba[item]);
		}
	}

	for (var x = 0; x < total.length; x++) {
		horas.push(total[(2 * x) + 1]);
		total.splice((2 * x) + 1, 1);
		// document.writeln(total[x]);
		if (((x + 1) % 2) == 0) {
			valores.push(total[x]);
		} else {
			fechas.push(total[x]);
		}

	}

	var container = document.getElementById('visualization');
	var dataset = new vis.DataSet();
	for (var i = 0; i < fechas.length - 1; i++) {
		// alert(fechas[i]+"T"+horas[i].replace(/\s/g, ''));
		dataset.add({
			x : fechas[i] + "T" + horas[i].replace(/\s/g, ''),
			y : valores[i]
		});

	}

	var options = {
		start : fechas[0],
		end : fechas[fechas.length - 2],
		graphHeight : true,
		sort : false,
		dataAxis : {
			left : {
				range : {
					min : 5,
					max : 130
				}
			}
		},
		// timeAxis: {scale: 'minute', step: 5},
		drawPoints : {
			size : 15,
			style : 'circle',
		}

	};
	var graph2d = new vis.Graph2d(container, dataset, options);

}

// PRUEBAS PARSEO
/*
 * function mostrarAlert() { var datosString =
 * document.formDatos.string_datos.value; //var array =
 * datosString.split(";").map(String); var array = datosString.split(";"); var
 * prueba = []; var prueba = array[2].split(","); window.alert(prueba[1]);
 * printTable2(datosString); }
 */
