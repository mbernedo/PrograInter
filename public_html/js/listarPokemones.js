/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

function mostrarPokemones(clase) {
    if (clase == null) {
        pokemones.forEach(function (item, index) {
            var imagenes = document.getElementById("imagenes");
            var indice;
            if (item.id < 10) {
                indice = "00" + item.id + "." + item.nombre.toLowerCase();
            } else {
                indice = "0" + item.id + "." + item.nombre.toLowerCase();
            }
            imagenes.innerHTML = imagenes.innerHTML +
                    "<div class=\"col-xs-3\">" +
                    "<img src=\"img/" + indice + ".png\" class=\"tamaño img-thumbnail\"/>" +
                    "<p class=\"" + item.tipo + " centrado\">" + item.nombre + "</p>" +
                    "</div>";
        });
    }
}

$(document).ready(function () {
    mostrarPokemones();
});
