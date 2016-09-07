var srch=document.getElementById("busqueda")
srch.addEventListener("input", buscar);
var rep=new XMLHttpRequest();
function buscar(){
	req.open('GET','http://localhost:8090/pokemones.json',true);
	req.onreadystatechange = function (aEvt){
		if(req.readyState == 4){
			if(req.status == 200)
				muestraRptas(req.responseText);
				else 
					console.log("Error cargando datos\n");
		}
	};
	req.send(null);
}
function muestraRptas(cad){
	var cadenaBuscar=document.getElementById("busqueda").value;
	console.log("cadena a buscar" + cadenaBuscar);
	if(cadenaBuscar == "")
		return;
	document.getElementById("resultados").innerHTML = "";
	var output = '<ul class= "searchresult" id="lista">';
	JSON.parse(cad,function(k,v){
		if(k =='name' && v.startsWith(cadenaBuscar)) {
			output +='<li>' + v + '</li>'; 
		}	
	});
	output += '</ul>';
	document.getElementById("resultados").innerHTML = output;
	var link = document.getElementById("resultados");
	attachEven(link,"click",EventHandler);
	
}



function attachEvent(element, type, handler){
    if(element.addEventListener)
        element.addEventListener(type,handler,false);
    else
        elemento.attachEvent("on"+type, handler);
}
function EventHandler(e){
    var cad= e.target.innerText || e.target.textContent;
    console.log(cad);
    document.getElementById("busqueda").value=cad;
    document.getElementById("resultados").innerHTML = " ";
}
