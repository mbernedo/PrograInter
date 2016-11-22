<%-- 
    Document   : index
    Created on : 01/10/2016, 05:21:59 PM
    Author     : migue_000
--%>

<%@page import="edu.ulima.bean.GestorPokemon"%>
<%@page import="edu.ulima.bean.Pokemon"%>
<%@page import="edu.ulima.bean.Tipo"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<jsp:useBean id="tipos" 
             class="java.util.ArrayList" scope="session" />
<jsp:useBean id="pokemones" 
             class="java.util.ArrayList" scope="session" />
<jsp:useBean id="codigos"
             class="java.util.ArrayList" scope="session"/>
<!DOCTYPE html>
<html>
    <head>
        <title>TODO supply a title</title>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">      
        <link href="twitter-bootstrap/css/bootstrap-theme.css" rel="stylesheet" type="text/css"/> 
        <link href="twitter-bootstrap/css/bootstrap.css" rel="stylesheet" type="text/css"/>
        <link href="css/sweetalert2.min.css" rel="stylesheet" type="text/css"/>
        <link href="css/estilo01.css" rel="stylesheet" type="text/css"/>
    </head>
    <body>
        <!--Modal error -->
        <% if (request.getParameter("error") != null) { %>
        <div class="modal fade" id="errorModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
            <div class="modal-dialog" role="document">
                <div class="modal-content">
                    <div class="modal-header">
                        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                        <h4 class="modal-title" id="myModalLabel">Ver pokemon</h4>
                    </div>
                    <div class="modal-body">
                        <div class="row">
                            <div class="col-xs-6">
                                <h1>El código ya existe</h1>
                            </div> 
                            <div class="col-xs-6">
                                <img class="sizeImagen" src="img/error.png"/>
                            </div>
                        </div>
                    </div>
                    <div class="modal-footer">
                        <button type="button" class="btn btn-primary" data-dismiss="modal">
                            <span class="glyphicon glyphicon-backward"></span>
                            <span>Regresar</span>
                        </button>
                    </div>
                </div>
            </div>
        </div>
        <% } %>
        <!--Modal ver -->
        <% if (request.getParameter("ver") != null) {
                int pos = Integer.parseInt(request.getParameter("id"));
                Pokemon pokemonVer = GestorPokemon.getPokemon(pos, pokemones);
        %>
        <div class="modal fade" id="verModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
            <div class="modal-dialog" role="document">
                <div class="modal-content">
                    <div class="modal-header">
                        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                        <h4 class="modal-title" id="myModalLabel">Ver pokemon</h4>
                    </div>
                    <div class="modal-body">
                        <div class="row">
                            <div class="col-xs-6">
                                <p>Código de Pokedex</p>
                                <input type="text" value="<%= pokemonVer.getCod_pokedex()%>" readonly class="form-control"/>
                                <p>Nombre</p>
                                <input type="text" value="<%= pokemonVer.getNombre()%>" readonly class="form-control"/>
                                <p>HP</p>
                                <input type="text" value="<%= pokemonVer.getHp()%>" readonly class="form-control"/>
                                <p>Tipo</p>
                                <select id="pokemones1" class="form-control" name="pokemon" disabled>
                                    <option value=""><%= pokemonVer.getTipo().getNomTipo()%></option>                       
                                </select>
                            </div> 
                            <div class="col-xs-6">
                                <img class="sizeImagen" src="img/<%= pokemonVer.getId()%>.png"/>
                            </div>
                        </div>
                    </div>
                    <div class="modal-footer">
                        <button type="button" class="btn btn-primary" data-dismiss="modal">
                            <span class="glyphicon glyphicon-backward"></span>
                            <span>Regresar</span>
                        </button>
                    </div>
                </div>
            </div>
        </div>
        <% } %>
        <!-- Modal editar -->
        <% if (request.getParameter("editar") != null) {
                int pos = Integer.parseInt(request.getParameter("id"));
                Pokemon pokemonVer = GestorPokemon.getPokemon(pos, pokemones);
        %>
        <div class="modal fade" id="editarModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
            <div class="modal-dialog" role="document">
                <div class="modal-content">
                    <div class="modal-header">
                        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                        <h4 class="modal-title" id="myModalLabel">Editar pokemon</h4>
                    </div>
                    <div class="modal-body">
                        <p>Código de Pokedex</p>
                        <input type="number" value="<%= pokemonVer.getCod_pokedex()%>" id="c<%=pokemonVer.getId()%>" class="form-control"/>
                        <p>Nombre</p>
                        <input type="text" value="<%= pokemonVer.getNombre()%>" id="n<%=pokemonVer.getId()%>" class="form-control"/>
                        <p>HP</p>
                        <input type="number" value="<%= pokemonVer.getHp()%>" id="h<%=pokemonVer.getId()%>" class="form-control"/>
                        <p>Tipo</p>
                        <input type="text" id="input" hidden="hidden" value="<%= pokemonVer.getTipo().getIdTipo()%>"/>
                        <select id="pokemones2" class="form-control" name="pokemon">
                            <% for (int i = 0; i < tipos.size(); i++) {
                                    Tipo tipo = (Tipo) tipos.get(i);%>
                            <option value="<%= tipo.getIdTipo()%>"><%= tipo.getNomTipo()%></option>
                            <% }%>
                        </select>
                    </div>
                    <div class="modal-footer">
                        <button type="button" class="btn btn-primary" data-dismiss="modal">
                            <span class="glyphicon glyphicon-backward"></span>
                            <span>Regresar</span>
                        </button>
                        <button type="button" class="btn btn-success cambiar" id="e<%= pokemonVer.getId()%>">
                            <span class="glyphicon glyphicon-saved"></span>
                            <span>Guardar cambios</span>
                        </button>
                    </div>
                </div>
            </div>
        </div>
        <% } %>
        <!-- Modal crear -->
        <% if (request.getParameter("crear") != null) {
        %>
        <div class="modal fade" id="crearModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
            <div class="modal-dialog" role="document">
                <div class="modal-content">
                    <div class="modal-header">
                        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                        <h4 class="modal-title" id="myModalLabel">Nuevo pokemon</h4>
                    </div>
                    <div class="modal-body">
                        <p>Código de Pokedex</p>
                        <input type="number" id="codigo" class="form-control"/>
                        <p>Nombre</p>
                        <input type="text" id="nombre" class="form-control"/>
                        <p>HP</p>
                        <input type="number" id="hp" class="form-control"/>
                        <p>Tipo</p>
                        <select id="pokemones3" class="form-control" name="pokemon">
                            <option value="0"></option>
                            <% for (int i = 0; i < tipos.size(); i++) {
                                    Tipo tipo = (Tipo) tipos.get(i);%>
                            <option value="<%= tipo.getIdTipo()%>"><%= tipo.getNomTipo()%></option>
                            <% }%>
                        </select>
                    </div>
                    <div class="modal-footer">
                        <button type="button" class="btn btn-primary" data-dismiss="modal">
                            <span class="glyphicon glyphicon-backward"></span>
                            <span>Regresar</span>
                        </button>
                        <button type="button" class="btn btn-success agregar">
                            <span class="glyphicon glyphicon-saved"></span>
                            <span>Guardar cambios</span>
                        </button>
                    </div>
                </div>
            </div>
        </div>
        <% } %>
        <!-- Pantalla principal -->
        <div class="container">
            <h1>Lista Pokemones</h1>
            <div class="row">
                <div class="col-xs-3">
                    <select id="pokemones4" class="form-control" name="pokemon">
                        <option value="0"></option>
                        <% for (int i = 0; i < tipos.size(); i++) {
                                Tipo tipo = (Tipo) tipos.get(i);%>
                        <option value="<%= tipo.getIdTipo()%>"><%= tipo.getNomTipo()%></option>
                        <% }%>
                    </select>
                </div>
                <button class="btn btn-success filtrar">
                    <span class="glyphicon glyphicon-filter" aria-hidden="true"></span>
                    <span>FILTRAR</span>
                </button>
                <button class="btn btn-primary crear" data-toggle="modal" data-target="#crearModal">
                    <span class="glyphicon glyphicon-plus-sign" aria-hidden="true"></span>
                    <span>ANAÑIR POKEMON</span>
                </button>
            </div>
            <br>
            <table class="table table-hover">
                <thead>
                    <tr>
                        <th class="centrado">Imagen</th>
                        <th class="centrado">Código Pokedex</th>
                        <th class="centrado">Nombre</th>
                        <th class="centrado">HP</th>
                        <th class="centrado">Tipo</th>
                        <th class="centrado">Acciones</th>
                    </tr>
                </thead>
                <tbody>
                    <% for (int i = 0; i < pokemones.size(); i++) {
                            Pokemon pokemon = (Pokemon) pokemones.get(i);
                    %>
                    <tr>
                        <% if (pokemon.getId() > 150) { %>
                        <td class="centrado"><img src="img/151.png"/></td>
                            <% } else {%>
                        <td class="centrado"><img src="img/<%= pokemon.getId()%>.png"/></td>
                            <% }%>
                        <td class="centrado"><%= pokemon.getCod_pokedex()%></td>
                        <td class="centrado"><%= pokemon.getNombre()%></td>
                        <td class="centrado"><%= pokemon.getHp()%></td>
                        <% if (pokemon.getTipo().getIdTipo() == 1) {%>
                        <td class="centrado">GRASS</td>
                        <%} else if (pokemon.getTipo().getIdTipo() == 2) { %>
                        <td class="centrado">FIRE</td>
                        <%} else if (pokemon.getTipo().getIdTipo() == 3) { %>
                        <td class="centrado">WATER</td>
                        <%} else if (pokemon.getTipo().getIdTipo() == 4) { %>
                        <td class="centrado">BUG</td>
                        <%} else if (pokemon.getTipo().getIdTipo() == 5) { %>
                        <td class="centrado">NORMAL</td>
                        <%} else if (pokemon.getTipo().getIdTipo() == 6) { %>
                        <td class="centrado">POISON</td>
                        <%} else if (pokemon.getTipo().getIdTipo() == 7) { %>
                        <td class="centrado">ELECTRIC</td>
                        <%} else if (pokemon.getTipo().getIdTipo() == 8) { %>
                        <td class="centrado">GROUND</td>
                        <%} else if (pokemon.getTipo().getIdTipo() == 9) { %>
                        <td class="centrado">FIGHTING</td>
                        <%} else if (pokemon.getTipo().getIdTipo() == 10) { %>
                        <td class="centrado">PSYCHIC</td>
                        <%} else if (pokemon.getTipo().getIdTipo() == 11) { %>
                        <td class="centrado">ROCK</td>
                        <%} else if (pokemon.getTipo().getIdTipo() == 12) { %>
                        <td class="centrado">GHOST</td>
                        <%} else if (pokemon.getTipo().getIdTipo() == 13) { %>
                        <td class="centrado">ICE</td>
                        <%} else if (pokemon.getTipo().getIdTipo() == 14) {%>
                        <td class="centrado">DRAGON</td>
                        <%}%>
                        <td class="centrado">
                            <a class="btn btn-primary editar"  id="e<%= pokemon.getId()%>">
                                <span class="glyphicon glyphicon-pencil" aria-hidden="true"></span>
                            </a>
                            <a class="btn btn-warning ver" id="v<%= pokemon.getId()%>"> 
                                <span class="glyphicon glyphicon-search" aria-hidden="true"></span>
                            </a>
                            <a class="btn btn-danger eliminar" id="d<%= pokemon.getId()%>"> 
                                <span class="glyphicon glyphicon-remove-sign" aria-hidden="true"></span>
                            </a>
                        </td>
                    </tr>
                    <% }%>
                </tbody>
            </table>
            <script src="jquery/jquery.min.js" type="text/javascript"></script>
            <script src="js/sweetalert2.min.js" type="text/javascript"></script>
            <script src="twitter-bootstrap/js/bootstrap.js" type="text/javascript"></script>
            <script src="js/jQuery.js" type="text/javascript"></script>
        </div>
    </body> 
</html>
