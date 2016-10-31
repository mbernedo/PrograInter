<%-- 
    Document   : principal
    Created on : 28/10/2016, 02:16:44 PM
    Author     : mbernedo
--%>

<%@page import="edu.ulima.beans.Juego"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<jsp:useBean id="usuario"
             class="java.lang.String" scope="session"/>
<jsp:useBean id="juegos"
             class="java.util.ArrayList" scope="session"/>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Practica 3 | Mi perfil</title>
        <link href="css/style.css" rel="stylesheet" type="text/css"/>
        <link href="twitter-bootstrap/css/bootstrap-theme.css" rel="stylesheet" type="text/css"/>
        <link href="twitter-bootstrap/css/bootstrap.min.css" rel="stylesheet" type="text/css"/>
        <link href="twitter-bootstrap/css/tether.min.css" rel="stylesheet" type="text/css"/>
        <link href="css/animate.css" rel="stylesheet" type="text/css"/>
        <link href="twitter-bootstrap/css/sweetalert2.min.css" rel="stylesheet" type="text/css"/>
    </head>
    <body>
        <div>
            <h1>Michi Game</h1>
            <h4 class="user"><%=usuario%></h4>
        </div>
        <div class="row">
            <div class="col-xs-6">
                <label>Partidas </label>
                <button class="btn btn-primary" id="btnNuevo">Nuevo</button>
                <div>
                    <% for (int i = 0; i < juegos.size(); i++) {
                            Juego juego = (Juego) juegos.get(i); %>
                    <div style="border-bottom: 1px solid">
                        <label>vs</label>
                        <% if (!juego.getGamer1().equalsIgnoreCase(usuario)) {%>
                        <label><%=juego.getGamer1()%></label>
                        <% } else {%>
                        <label><%=juego.getGamer2()%></label>
                        <% }%>
                        <label><%=juego.getResultado()%></label> 
                    </div>
                    <% }%>
                </div>

            </div>
            <div class="col-xs-6">

            </div>    
        </div>
        <script src="twitter-bootstrap/js/jquery.js" type="text/javascript"></script>
        <script src="twitter-bootstrap/js/bootstrap.min.js" type="text/javascript"></script>
        <script src="twitter-bootstrap/js/tether.min.js" type="text/javascript"></script>
        <script src="twitter-bootstrap/js/sweetalert2.min.js" type="text/javascript"></script>
        <script src="js/usuario.js" type="text/javascript"></script>
    </body>
</html>
