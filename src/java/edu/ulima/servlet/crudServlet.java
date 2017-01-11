/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.ulima.servlet;

import edu.ulima.bean.Pokemon;
import edu.ulima.dao.PokemonDAO;
import edu.ulima.interfaces.PokemonIf;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author migue_000
 */
public class crudServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession ses = request.getSession();
        Pokemon poke = new Pokemon();
        String accion = request.getParameter("accion");
        PokemonIf pokemon = new PokemonDAO();
        String rpta = "";
        List<Integer> codigos = pokemon.getCodigo();
        if (accion.equalsIgnoreCase("eliminar")) {
            int pos = Integer.parseInt(request.getParameter("id"));
            pokemon.borrarPokemon(pos);
            rpta = "/get";
        } else if (accion.equalsIgnoreCase("cambiar")) {
            int pos = Integer.parseInt(request.getParameter("id"));
            int cod = Integer.parseInt(request.getParameter("codigo"));
            String nombre = request.getParameter("nombre");
            int hp = Integer.parseInt(request.getParameter("hp"));
            int idTipo = Integer.parseInt(request.getParameter("tipo"));
            for (int i = 0; i < codigos.size(); i++) {
                if (codigos.get(i) != cod) {
                    poke.setCod_pokedex(cod);
                    poke.setNombre(nombre);
                    poke.setHp(hp);
                    poke.getTipo().setIdTipo(idTipo);
                    pokemon.modificarPokemon(pos, poke);
                    i++;
                    rpta = "/get";
                } else {
                    String error = "Código ya existe";
                    ses.setAttribute("error", error);
                    rpta = "/index.jsp";
                }
            }

        } else if (accion.equalsIgnoreCase("agregar")) {
            int cod = Integer.parseInt(request.getParameter("codigo"));
            String nombre = request.getParameter("nombre");
            int hp = Integer.parseInt(request.getParameter("hp"));
            int idTipo = Integer.parseInt(request.getParameter("tipo"));
            for (int i = 0; i < codigos.size(); i++) {
                if (codigos.get(i) != cod) {
                    poke.setCod_pokedex(cod);
                    poke.setNombre(nombre);
                    poke.setHp(hp);
                    poke.getTipo().setIdTipo(idTipo);
                    pokemon.insertarPokemon(poke);
                    rpta = "/get";
                } else {
                    ses.setAttribute("error", "error");
                    rpta = "/index.jsp";
                }
            }
        }
        RequestDispatcher rd = request.getRequestDispatcher(rpta);
        rd.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
