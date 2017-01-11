/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

var col = 1;
var fila = 1;
var tabla = [];
var correo = localStorage.getItem("usuario");
var URL = "https://programacion.herokuapp.com/PrograFinal/servicio/Final/";

$(document).ready(function () {
    $("#col").click(function () {
        $("#container").append(getColumna());
        crearEvento();
    });
    $("#fila").click(function () {
        runCol();
        crearEvento();
    });
    $("#nuevo").click(function () {
        window.location.reload();
    });
    $("#guardar").click(function () {
        $("#modalGuardar").modal("show");
    });
    $("#guardarDoc").click(function () {
        var nomArchivo = $("#nomArch").val();
        $(".celda").map(function (item, index) {
            var celda = {
                "pos": $(this).children().attr("id"),
                "val": $(this).children().val()
            };
            tabla.push(celda);
        });
        var user = {
            "usuario": correo,
            "excel": {
                "nomArc": nomArchivo,
                "celdas": tabla
            }
        };
        console.log(user);
        tabla = [];
        $.ajax({
            type: "POST",
            url: URL + "guardar/",
            processData: false,
            contentType: 'application/json',
            data: JSON.stringify(user),
            success: function (res) {
                //0:login incorrecto, 1:login correcto
                if (res.cod === 1) {
                    swal(
                            'Completo',
                            'El documento se guardó correctamente',
                            'success'
                            )
                } else {
                    swal(
                            'Incorrecto',
                            'El documento no pudo guardarse',
                            'error'
                            )
                }
            }});
    });
    $("#operaciones").click(function () {
        $("#modalSum").modal("show");
    });
    $("#abrir").click(function () {
        $("#modalAbrir").modal("show");
        var nomArchivo = $("#nomArch").val();
        var user = {
            "usuario": correo
        };
        console.log(user);
        tabla = [];
        $.ajax({
            type: "POST",
            url: URL + "abrir/",
            processData: false,
            contentType: 'application/json',
            data: JSON.stringify(user),
            success: function (res) {
                //0:login incorrecto, 1:login correcto
                res.forEach(function (item, index) {
                    $("#tablaAbrir").append("<div class='col-xs-6'>" +
                            "<h4>" + item + "</h4>" +
                            "</div>" +
                            "<div class='col-xs-6'>" +
                            "<h4><button class='btn btn-success'>Abrir</button></h4>" +
                            "</div>");
                });
            }});
    });
    $("#quienes").click(function () {
        $("#modalInt").modal("show");
    });
    $("#logout").click(function () {
        localStorage.removeItem("usuario");
        window.location.href = "/PrograFinal/index.html";
    });
});

$(".focus").keypress(function (e) {
    var id = $(this).attr("id");
    var suma = $("#" + id).val();
    if (suma.substr(0, 6) === "=sumar") {
        if (e.which === 13) {
            var rpta = validarSum(suma);
            if (rpta === "error") {
                suma = "error";
            } else {
                suma = rpta;
            }
            $("#" + id).val(suma);
        }
    }
}).focusout(function () {
    var id = $(this).attr("id");
    var suma = $("#" + id).val();
    if (suma.substr(0, 6) === "=sumar") {
        var rpta = validarSum(suma);
        if (rpta === "error") {
            suma = "error";
        } else {
            suma = rpta;
        }
        $("#" + id).val(suma);
    }
});

function sumar(n1, n2) {
    return n1 + n2;
}

function validarSum(cad) {
    var id1, id2;
    if (cad.substr(0, 6) === "=sumar") {
        var puntos = cad.search(":");
        var pos1 = cad.substring(7, puntos);
        var pos2 = cad.substring(puntos + 1);
        if (isNaN(pos1.substring(0, 1)) === true && isNaN(pos1.substring(1)) === false) {
            id1 = pos1;
            console.log("1" + id1);
        }
        if (isNaN(pos2.substring(0, 1)) === true && isNaN(pos2.substring(1)) === false) {
            id2 = pos2;
            console.log("2" + id2);
        }
    }
    var val1 = $("#" + id1).val();
    var val2 = $("#" + id2).val();
    if (isNaN(val1) === false && isNaN(val2) === false) {
        val1 = Number(val1);
        val2 = Number(val2);
        console.log(val1 + " " + val2);
        return sumar(val1, val2);
    } else {
        return "error";
    }

}

function getFilas(text) {
    for (var i = 1; i <= fila; i++) {
        text = text + getcel(i, col);
        if (i === fila) {
            return text;
        }
    }
}

function getColumna() {
    col++;
    return '<div id=' + col + ' class="columna' + col + ' col">'
            + getFilas("") +
            '</div>';
}

function runCol() {
    fila++;
    $(".filaPos").append("<h5 style='margin-right: 20px;'>" + fila + "</h5>");
    $(".col").map(function (ele, dos) {
        $(".columna" + (ele + 1)).append(getcel(fila, (ele + 1)));
    });
}

function getcel(fi, co) {
    if (fi === 1) {
        return '<br><br>' +
                '<div class="celda2"><h5><strong>' + getLetraNum(co) + '</strong></h5></div>' +
                '<div class="celda"><input class="focus" id=' + getLetraNum(co) + "" + fi + ' type="text"/></div>';
    } else {
        return '<div class="celda"><input class="focus" id=' + getLetraNum(co) + "" + fi + ' type="text"/></div>';
    }
}

function getLetraNum(numero) {
    letras = ["A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N", "Ñ", "O", "P", "Q", "R", "S", "T", "U", "V", "W", "X", "Y", "Z"];
    return letras[numero - 1];
}

function crearEvento() {
    $(".focus").bind({
        keypress: function (e) {
            var id = $(this).attr("id");
            var suma = $("#" + id).val();
            if (suma.substr(0, 6) === "=sumar") {
                if (e.which === 13) {
                    var rpta = validarSum(suma);
                    if (rpta === "error") {
                        suma = "error";
                    } else {
                        suma = rpta;
                    }
                    $("#" + id).val(suma);
                }
            }
        },
        focusout: function () {
            var id = $(this).attr("id");
            var suma = $("#" + id).val();
            if (suma.substr(0, 6) === "=sumar") {
                var rpta = validarSum(suma);
                if (rpta === "error") {
                    suma = "error";
                } else {
                    suma = rpta;
                }
                $("#" + id).val(suma);
            }
        }
    });
}