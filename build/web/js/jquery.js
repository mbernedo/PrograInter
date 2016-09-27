/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


$('#Filtrar').click(function () {
    var combo = $('#pokemones option:selected').text();
    window.location.href = "http://localhost:8090/PrograInter/pok?tipo=" + combo;
});

$(".editar").click(function () {
    var idTot=$(this).attr("id")
    var id= idTot.substring(1,idTot.length);
    $(".poke"+id).removeAttr("readonly");
    $(this).removeClass("modificar");
    $(this).removeClass("btn-primary");
    $(this).addClass("btn-warning");
    $(this).addClass("editar");
    $(this).css("display", "none");
    $('#m'+id).css("display", "inline-block");
});

$(".modificar").click(function(){
    var idTot=$(this).attr("id")
    var id= idTot.substring(1,idTot.length);
    var nombre=$(".poke"+id).val();
    window.location.href = "http://localhost:8090/PrograInter/mod?id=" +id+"&nombre="+nombre+"&accion=modificar";
});

$(".eliminar").click(function(){
    var idTot=$(this).attr("id")
    var id= idTot.substring(1,idTot.length);
    window.location.href = "http://localhost:8090/PrograInter/mod?id="+id+"&accion=eliminar";
});

$("#Ingresar").click(function(){
    var nombre = $("#nombrePokemon").val();
    var idTipo = $("#pokemones option:selected").val();
    window.location.href = "http://localhost:8090/PrograInter/mod?idTipo="+idTipo+"&nomPoke="+nombre+"&accion=ingresar";
});