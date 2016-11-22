/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

$(document).ready(function () {
    $("#pokemones2 option[value=" + 0 + "]").attr("selected", "selected");
    if ($("#verModal").length) {
        $("#verModal").modal("show");
    }
    if ($("#editarModal").length) {
        $("#editarModal").modal("show");
        var cod = $("#input").val();
        $("#pokemones2 option[value=" + cod + "]").attr("selected", "selected");
    }
    if ($("#crearModal").length) {
        $("#crearModal").modal("show");
    }
    if( $("#errorModal").length){
        $("#errorModal").modal("show");
    }
});

$('.filtrar').click(function () {
    var combo = $('#pokemones4 option:selected').val();
    window.location.href = "http://localhost:8080/Proyecto-Parte1/get?combo=" + combo;
});

$(".eliminar").click(function () {
    var idTot = $(this).attr("id")
    var id = idTot.substring(1, idTot.length);
    swal({
        title: '¿Estas seguro?',
        text: "Si borras al pokemon, ya no podrás recuperarlo",
        type: 'warning',
        showCancelButton: true,
        confirmButtonColor: '#3085d6',
        cancelButtonColor: '#d33',
        confirmButtonText: 'Si',
        cancelButtonText: 'No',
        confirmButtonClass: 'btn btn-success',
        cancelButtonClass: 'btn btn-danger',
        buttonsStyling: false
    }).then(function () {

        swal(
                'Borrado',
                'Se borró el pokemon',
                'success'
                ).then(function () {
            window.location.href = "http://localhost:8080/Proyecto-Parte1/crud?id=" + id + "&accion=eliminar";
        });
    }, function (dismiss) {
        if (dismiss === 'cancel') {
            swal(
                    'Cancelado',
                    'El pokemon no fue borrado',
                    'error'
                    );
        }
    })
});

$(".crear").click(function () {
    window.location.href = "http://localhost:8080/Proyecto-Parte1/?crear=crear";
    alert(url);
});

$(".ver").click(function () {
    var idTot = $(this).attr("id")
    var id = idTot.substring(1, idTot.length);
    window.location.href = "http://localhost:8080/Proyecto-Parte1/?ver=ver&id=" + id;
});

$(".editar").click(function () {
    var idTot = $(this).attr("id")
    var id = idTot.substring(1, idTot.length);
    window.location.href = "http://localhost:8080/Proyecto-Parte1/?editar=editar&id=" + id;
});

$(".cambiar").click(function () {
    var idTot = $(this).attr("id")
    var id = idTot.substring(1, idTot.length);
    var cod = $("#c" + id).val();
    var nombre = $("#n" + id).val();
    var hp = $("#h" + id).val();
    var combo = $('#pokemones2 option:selected').val();
    window.location.href = "http://localhost:8080/Proyecto-Parte1/crud?id=" + id + "&codigo=" + cod + "&nombre=" + nombre + "&hp=" + hp + "&tipo=" + combo + "&accion=cambiar";
});

$(".agregar").click(function () {
    var cod = $("#codigo").val();
    var nombre = $("#nombre").val();
    var hp = $("#hp").val();
    var combo = $("#pokemones3 option:selected").val();
    window.location.href = "http://localhost:8080/Proyecto-Parte1/crud?codigo=" + cod + "&nombre=" + nombre + "&hp=" + hp + "&tipo=" + combo + "&accion=agregar";
});