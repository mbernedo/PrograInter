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
    var id= $(this).attr("id").substring(1,2);
    $(".poke"+id).removeAttr("readonly");
    $(this).removeClass("modificar");
    $(this).removeClass("btn-primary");
    $(this).addClass("btn-warning");
    $(this).addClass("editar");
    $(this).css("display", "none");
    $('#m'+id).css("display", "inline-block");
});

$(".modificar").click(function(){
    var id =$(this).attr("id").substring(1,2);
    var nombre=$("#poke"+id).val();
    window.location.href = "http://localhost:8090/PrograInter/pok?id=" +id+"&nombre="+nombre;
});