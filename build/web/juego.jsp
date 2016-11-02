<%-- 
    Document   : juego
    Created on : 31/10/2016, 10:24:01 AM
    Author     : mbernedo
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<jsp:useBean id="usuario"
             class="java.lang.String" scope="session"/>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link href="twitter-bootstrap/css/bootstrap-theme.css" rel="stylesheet" type="text/css"/>
        <link href="twitter-bootstrap/css/bootstrap.min.css" rel="stylesheet" type="text/css"/>
        <link rel="stylesheet" href="https://fonts.googleapis.com/icon?family=Material+Icons">
        <link rel="stylesheet" href="https://code.getmdl.io/1.2.1/material.indigo-pink.min.css">
        <link href="twitter-bootstrap/css/tether.min.css" rel="stylesheet" type="text/css"/>
        <link href="css/animate.css" rel="stylesheet" type="text/css"/>
        <link href="twitter-bootstrap/css/sweetalert2.min.css" rel="stylesheet" type="text/css"/>
        <link href="css/michiStyle.css" rel="stylesheet" type="text/css"/>
        <title>Practica3 | Michi</title>
    </head>
    <body onload="connect();" onunload="disconnect();">
        <div class="container">
            <div class="row">
                <div>
                    <h1>Michi Game</h1>
                </div>
                <div class="col-xs-6 tablero">
                    <div class="row">
                        <div class="col-xs-4 celda seleccionado" id="celda1"></div>
                        <div class="col-xs-4 celda seleccionado" id="celda2"></div>
                        <div class="col-xs-4 celda seleccionado" id="celda3"></div>
                    </div>
                    <div class="row">
                        <div class="col-xs-4 celda seleccionado" id="celda4"></div>
                        <div class="col-xs-4 celda seleccionado" id="celda5"></div>
                        <div class="col-xs-4 celda seleccionado" id="celda6"></div>
                    </div>
                    <div class="row">
                        <div class="col-xs-4 celda seleccionado" id="celda7"></div>
                        <div class="col-xs-4 celda seleccionado" id="celda8"></div>
                        <div class="col-xs-4 celda seleccionado" id="celda9"></div>
                    </div>
                </div>
                <div class="col-xs-6 datos">
                    <label>Jugador: </label>
                    <label id="usuario"><%=usuario%></label>
                    <p id="estado"></p>
                    <p id="ganador"></p>
                    <button id="btnEnviar" class="btn btn-primary">Enviar</button>
                </div>
            </div>
        </div>
        <script src="twitter-bootstrap/js/jquery.js" type="text/javascript"></script>
        <script src="twitter-bootstrap/js/bootstrap.min.js" type="text/javascript"></script>
        <script src="twitter-bootstrap/js/tether.min.js" type="text/javascript"></script>
        <script defer src="https://code.getmdl.io/1.2.1/material.min.js"></script>
        <script src="twitter-bootstrap/js/sweetalert2.min.js" type="text/javascript"></script>
        <script src="js/michi.js" type="text/javascript"></script>
    </body>
</html>
