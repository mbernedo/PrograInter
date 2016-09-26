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
            <div class="col-md-4">
                <p> Nombre </p>
                <input type="text"/>
            </div>
            <div class="col-md-4">
                <p> Universidad </p>
                <input type="text"/>
            </div>
            <div class="col-md-4">
                <p> Tipo </p>
                <select id="pokemones" name="pokemon">
                <option value=""></option>
                <% for (int i = 0; i < tipos.size(); i++) {
                        Tipo tipo = (Tipo) tipos.get(i); %>
                        <option value="<%= tipo.getTipo() %>"> <%= tipo.getTipo() %></option>
                <% } %>
            </select>
            </div>
        </div>
        <div style="margin-top: 55px">
            <% for (int i = 0; i < pokemones.size(); i++) {
                    Pokemon pokemon = (Pokemon) pokemones.get(i);
            %>
            <div class="row">
                <div class="col-md-3">
                    <label> <%= pokemon.getNombre()%> </label>
                </div>
                <div class="col-md-3">
                    <label> <%= pokemon.getTipo().getTipo()%></label>
                </div>
                <div class="col-md-3">
                    <button class="btn btn-danger" id="<%= pokemon.getId()%>"> Eliminar </button>
                </div>
                <div class="col-md-3">
                    <button class="btn btn-warning" id="<%= pokemon.getId()%>"> Modificar </button>
                </div> 
            </div> 
            <% }%>
        </div>
    </body>
</html>
