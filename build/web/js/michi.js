/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

var endPointURL = "ws://192.168.130.22:8080/WebService/michi";
var michiClient = null;
var celda;
var turno = 1;
var id;
var marca = "X";
var usuario;
var tres = [];
var win;

function connect() {
    michiClient = new WebSocket(endPointURL);
    michiClient.onmessage = function (event) {
        var jsonObj = JSON.parse(event.data);
        turno = jsonObj.turno;
        $("#" + jsonObj.id).html(jsonObj.marca);
        $("#" + jsonObj.id).css("pointer-events", "none");
        if (jsonObj.usuario !== usuario) {
            $("#estado").html("Tu turno");
        } else {
            $("#estado").html("Esperando");
        }
        $("#ganador").html("El ganador es: " + jsonObj.win);
        //$("#estado").html("Acaba de jugar " + jsonObj.usuario);
        var message = jsonObj.cod + ", " + jsonObj.msg + ", " + jsonObj.celda + ", " + turno;
        console.log(event.data);
    };
}

$(".seleccionado").click(function () {
    id = $(this).attr("id");
    if (turno === 1) {
        $(this).html("X");
        marca = "X";
    } else if (turno === 2) {
        $(this).html("O");
        marca = "O";
    }
    celda = id.substring(5);

});

$(document).ready(function () {
    usuario = $("#usuario").text();
});

$("#btnEnviar").click(function () {
    if (turno === 1) {
        turno = 2;
    } else if (turno === 2) {
        turno = 1;
    }
    tres.push(celda);
    if (ganar(tres) === 1) {
        win = usuario;
    }
    sendMessage();
    console.log(tres);
});

function disconnect() {
    michiClient.close();
}

function sendMessage() {
    var jsonObj = {"cod": 1, "msg": "message", "celda": celda, "turno": turno, "marca": marca, "id": id, "usuario": usuario, "win": win};
    michiClient.send(JSON.stringify(jsonObj));
}

function ganar(arreglo) {
    for (var i = 0; i < arreglo.length; i++) {
        if (jQuery.inArray(1, arreglo) >= 0) {
            if (jQuery.inArray(2, arreglo) >= 0 && jQuery.inArray(3, arreglo) >= 0) {
                $("#celda1").css("background", "#9a9a9a");
                $("#celda2").css("background", "#9a9a9a");
                $("#celda3").css("background", "#9a9a9a");
                return 1;
            } else if (jQuery.inArray(4, arreglo) >= 0 && jQuery.inArray(7, arreglo) >= 0) {
                $("#celda1").css("background", "#9a9a9a");
                $("#celda4").css("background", "#9a9a9a");
                $("#celda7").css("background", "#9a9a9a");
                return 1;
            } else if (jQuery.inArray(5, arreglo) >= 0 && jQuery.inArray(9, arreglo) >= 0) {
                $("#celda1").css("background", "#9a9a9a");
                $("#celda5").css("background", "#9a9a9a");
                $("#celda9").css("background", "#9a9a9a");
                return 1;
            }
        } else if (jQuery.inArray(2, arreglo) >= 0) {
            if (jQuery.inArray(1, arreglo) >= 0 && jQuery.inArray(3, arreglo) >= 0) {
                $("#celda1").css("background", "#9a9a9a");
                $("#celda2").css("background", "#9a9a9a");
                $("#celda3").css("background", "#9a9a9a");
                return 1;
            } else if (jQuery.inArray(5, arreglo) >= 0 && jQuery.inArray(8, arreglo) >= 0) {
                $("#celda5").css("background", "#9a9a9a");
                $("#celda2").css("background", "#9a9a9a");
                $("#celda8").css("background", "#9a9a9a");
                return 1;
            }
        } else if (jQuery.inArray(3, arreglo) >= 0) {
            if (jQuery.inArray(1, arreglo) >= 0 && jQuery.inArray(2, arreglo) >= 0) {
                $("#celda1").css("background", "#9a9a9a");
                $("#celda2").css("background", "#9a9a9a");
                $("#celda3").css("background", "#9a9a9a");
                return 1;
            } else if (jQuery.inArray(6, arreglo) >= 0 && jQuery.inArray(9, arreglo) >= 0) {
                $("#celda6").css("background", "#9a9a9a");
                $("#celda9").css("background", "#9a9a9a");
                $("#celda3").css("background", "#9a9a9a");
                return 1;
            } else if (jQuery.inArray(5, arreglo) >= 0 && jQuery.inArray(7, arreglo) >= 0) {
                $("#celda5").css("background", "#9a9a9a");
                $("#celda7").css("background", "#9a9a9a");
                $("#celda3").css("background", "#9a9a9a");
                return 1;
            }
        } else if (jQuery.inArray(4, arreglo) >= 0) {
            if (jQuery.inArray(1, arreglo) >= 0 && jQuery.inArray(7, arreglo) >= 0) {
                $("#celda1").css("background", "#9a9a9a");
                $("#celda4").css("background", "#9a9a9a");
                $("#celda7").css("background", "#9a9a9a");
                return 1;
            } else if (jQuery.inArray(5, arreglo) >= 0 && jQuery.inArray(6, arreglo) >= 0) {
                $("#celda4").css("background", "#9a9a9a");
                $("#celda5").css("background", "#9a9a9a");
                $("#celda6").css("background", "#9a9a9a");
                return 1;
            }
        } else if (jQuery.inArray(5, arreglo) >= 0) {
            if (jQuery.inArray(4, arreglo) >= 0 && jQuery.inArray(6, arreglo) >= 0) {
                $("#celda4").css("background", "#9a9a9a");
                $("#celda5").css("background", "#9a9a9a");
                $("#celda6").css("background", "#9a9a9a");
                return 1;
            } else if (jQuery.inArray(2, arreglo) >= 0 && jQuery.inArray(8, arreglo) >= 0) {
                $("#celda5").css("background", "#9a9a9a");
                $("#celda2").css("background", "#9a9a9a");
                $("#celda8").css("background", "#9a9a9a");
                return 1;
            }
        } else if (jQuery.inArray(6, arreglo) >= 0) {
            if (jQuery.inArray(3, arreglo) >= 0 && jQuery.inArray(9, arreglo) >= 0) {
                $("#celda3").css("background", "#9a9a9a");
                $("#celda9").css("background", "#9a9a9a");
                $("#celda6").css("background", "#9a9a9a");
                return 1;
            } else if (jQuery.inArray(4, arreglo) >= 0 && jQuery.inArray(5, arreglo) >= 0) {
                $("#celda4").css("background", "#9a9a9a");
                $("#celda5").css("background", "#9a9a9a");
                $("#celda6").css("background", "#9a9a9a");
                return 1;
            }
        } else if (jQuery.inArray(7, arreglo) >= 0) {
            if (jQuery.inArray(1, arreglo) >= 0 && jQuery.inArray(4, arreglo) >= 0) {
                $("#celda4").css("background", "#9a9a9a");
                $("#celda1").css("background", "#9a9a9a");
                $("#celda7").css("background", "#9a9a9a");
                return 1;
            } else if (jQuery.inArray(8, arreglo) >= 0 && jQuery.inArray(9, arreglo) >= 0) {
                $("#celda7").css("background", "#9a9a9a");
                $("#celda8").css("background", "#9a9a9a");
                $("#celda9").css("background", "#9a9a9a");
                return 1;
            }
        } else if (jQuery.inArray(8, arreglo) >= 0) {
            if (jQuery.inArray(2, arreglo) >= 0 && jQuery.inArray(5, arreglo) >= 0) {
                $("#celda2").css("background", "#9a9a9a");
                $("#celda8").css("background", "#9a9a9a");
                $("#celda5").css("background", "#9a9a9a");
                return 1;
            } else if (jQuery.inArray(7, arreglo) >= 0 && jQuery.inArray(9, arreglo) >= 0) {
                $("#celda7").css("background", "#9a9a9a");
                $("#celda8").css("background", "#9a9a9a");
                $("#celda9").css("background", "#9a9a9a");
                return 1;
            }
        } else if (jQuery.inArray(9, arreglo) >= 0) {
            if (jQuery.inArray(3, arreglo) >= 0 && jQuery.inArray(6, arreglo) >= 0) {
                $("#celda3").css("background", "#9a9a9a");
                $("#celda6").css("background", "#9a9a9a");
                $("#celda9").css("background", "#9a9a9a");
                return 1;
            } else if (jQuery.inArray(7, arreglo) >= 0 && jQuery.inArray(8, arreglo) >= 0) {
                $("#celda7").css("background", "#9a9a9a");
                $("#celda8").css("background", "#9a9a9a");
                $("#celda9").css("background", "#9a9a9a");
                return 1;
            } else if (jQuery.inArray(1, arreglo) >= 0 && jQuery.inArray(5, arreglo) >= 0) {
                $("#celda1").css("background", "#9a9a9a");
                $("#celda5").css("background", "#9a9a9a");
                $("#celda9").css("background", "#9a9a9a");
                return 1;
            }
        } else {
            return 0;
        }

    }
}