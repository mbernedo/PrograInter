/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.ulima.servlet;

import edu.ulima.bean.Pokemon;
import edu.ulima.dao.LoginDAO;
import edu.ulima.interfa.LoginIf;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author mbernedo
 */
public class modificarServlet extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
        HttpSession ses = request.getSession();
        Pokemon poke;
        String accion = request.getParameter("accion");   
        LoginIf login = new LoginDAO();
        if (accion.equalsIgnoreCase("modificar")) {
            int pos = Integer.parseInt(request.getParameter("id"));
            String nombre = request.getParameter("nombre");
            login.modificarPokemon(pos, nombre);
        } else if (accion.equalsIgnoreCase("eliminar")) {
            int pos = Integer.parseInt(request.getParameter("id"));
            login.borrarPokemon(pos);
        } else if(accion.equalsIgnoreCase("ingresar")){
            String nomPoke = request.getParameter("nomPoke");
            int idTipo = Integer.parseInt(request.getParameter("idTipo"));
            poke = new Pokemon();
            poke.setNombre(nomPoke);
            poke.getTipo().setId(idTipo);
            login.insertarPokemon(poke);
        }
        String rpta = "/pok";
        RequestDispatcher rd = request.getRequestDispatcher(rpta);
        rd.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }

}
