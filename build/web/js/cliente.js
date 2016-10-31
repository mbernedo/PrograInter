/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

var URL = "http://localhost:8080/WebService/servicio/practica3/";

$("#login").click(function () {
    var usuario = $("#usuario").val();
    var pass = $("#password").val();
    var user =
            {
                "usuario": usuario,
                "clave": pass
            };
    $.ajax({
        type: "POST",
        url: URL + "login/",
        processData: false,
        contentType: 'application/json',
        data: JSON.stringify(user),
        success: function (res) {
            //0:login incorrecto, 1:login correcto
            if (res.cod === 1) {
                window.location.href = "http://localhost:8080/WebService/juegos?user=" + res.usuario.usuario;
            } else {
                swal(
                        'Credenciales incorrectas',
                        'Por favor verifique sus datos y vuelva a intentarlo',
                        'error'
                        ).then(function(){
                            window.location.reload();
                        });
                
            }
        }});
});

$("#registrar").click(function () {
    var usuario = $("#usuario").val();
    var pass = $("#password").val();
    var user =
            {
                "usuario": usuario,
                "clave": pass
            };
    $.ajax({
        type: "POST",
        url: URL + "registro/",
        processData: false,
        contentType: 'application/json',
        data: JSON.stringify(user),
        success: function (res) {
            //0:registro incorrecto, 1:registro correcto, 2:usuario repetido
            if (res.cod === 1) {
                window.location.href = "http://localhost:8080/WebService/juegos?user=" + res.usuario.usuario;
            } else if (res.cod === 2) {
                swal({
                    title: '',
                    html: $('<div>')
                            .addClass('some-class')
                            .text('El usuario ya existe'),
                    animation: false,
                    customClass: 'animated tada'
                }). then(function(){
                    window.location.reload();
                });
                
            } else{
                swal(
                        'Credenciales incorrectas',
                        'Por favor verifique sus datos y vuelva a intentarlo',
                        'error'
                        ).then(function(){
                            window.location.reload();
                        });
                
            }
        }});
});