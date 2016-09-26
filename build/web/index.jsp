<%-- 
    Document   : index
    Created on : 24/09/2016, 08:16:34 PM
    Author     : migue_000
--%>

<%@page import="edu.ulima.bean.Tipo"%>
<%@page import="edu.ulima.bean.Pokemon"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<jsp:useBean id="pokemones" 
             class="java.util.ArrayList" scope="session" />
<jsp:useBean id="tipos" 
             class="java.util.ArrayList" scope="session" />
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>TODO supply a title</title>
        <meta charset="UTF-8">
        <link href="css/estilo01.css" rel="stylesheet" type="text/css"/>
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link href="twitter-bootstrap/css/bootstrap.css" rel="stylesheet" type="text/css"/>
    </head>
    <body>
        <div class="row">
            <div class="col-md-6">
                <p> Tipo </p>
                <select id="pokemones" name="pokemon">
                    <option value=""></option>
                    <% for (int i = 0; i < tipos.size(); i++) {
                        Tipo tipo = (Tipo) tipos.get(i);%>
                    <option value="<%= tipo.getTipo()%>"><%= tipo.getTipo()%></option>
                    <% } %>
                </select>
            </div>
            <div class="col-md-6">
                <br>
                <button class="btn btn-primary" id="Filtrar"> Filtrar </button>
            </div>
        </div>
        <div style="margin-top: 55px">
            <% for (int i = 0; i < pokemones.size(); i++) {
                    Pokemon pokemon = (Pokemon) pokemones.get(i);
            %>
            <div class="row">
                <div class="col-md-3">
                    <input value="<%= pokemon.getNombre()%>" class="form-control" id="poke<%= pokemon.getId() %>" readonly="true" />
                </div>
                <div class="col-md-3">
                    <input value="<%= pokemon.getTipo().getTipo()%>" class="form-control poke<%= pokemon.getId()%>" readonly="true"/>
                </div>
                <div class="col-md-3">
                    <a class="btn btn-primary eliminar" id="<%= pokemon.getId()%>" href="/PrograInter/pok?id=<%= pokemon.getId()%>"> Eliminar </a>
                </div>
                <div>
                    <div class="col-md-3">
                        <a class="btn btn-primary modificar" style="display: none" id="m<%= pokemon.getId()%>">Modificar</a>
                    </div>
                </div>
                    <div class="col-md-3">
                        <a class="btn btn-primary editar"  id="e<%= pokemon.getId()%>">Editar</a>
                </div> 
            </div> 
            <% }%>
        </div>
        <script src="jquery/jquery.min.js" type="text/javascript"></script>
        <script src="js/jquery.js" type="text/javascript"></script>
    </body>
</html>
