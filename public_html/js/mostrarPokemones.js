/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
function mostrarPokemones(clase){
    imagenes.innerHTML = "";
        for (var i = 0; pokemones.length; i++) {
            if (pokemones[i].tipo == clase) {
                if (pokemones[i].id < 10) {
                    indice = "00" + pokemones[i].id + "." + pokemones[i].nombre.toLowerCase();
                } else {
                    indice = "0" + pokemones[i].id + "." + pokemones[i].nombre.toLowerCase();
                }
                imagenes.innerHTML = imagenes.innerHTML +
                        "<div class=\"col-xs-3\">" +
                        "<img src=\"img/" + indice + ".png\" class=\"tamaño img-thumbnail\"/>" +
                        "<p class=\"" + pokemones[i].tipo + " centrado\">" + pokemones[i].nombre + "</p>" +
                        "</div>";
            }
        }
}

$(document).ready(function () {
    $('button').click(function(){
        var lista=$("#pokemones");
        mostrarPokemones(lista.val());
    });
    $('#pokemones').change(function(){
        var lista=$("#pokemones");
        mostrarPokemones();
    });
});
