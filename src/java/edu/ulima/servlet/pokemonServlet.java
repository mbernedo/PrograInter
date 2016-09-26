/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.ulima.servlet;

import edu.ulima.bean.Pokemon;
import edu.ulima.bean.Tipo;
import edu.ulima.dao.LoginDAO;
import edu.ulima.interfa.LoginIf;
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
public class pokemonServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession ses = request.getSession(true);
        LoginIf login = new LoginDAO();
        List<Pokemon> lista;
        String var = request.getParameter("tipo");
        if (request.getParameter("tipo") == null) {
            lista = login.obtenerPokemones("");
        }else{
            lista = login.obtenerPokemones(var);
        }
        List<Tipo> lista2 = login.obtenerTipos();
        ses.setAttribute("pokemones", lista);
        ses.setAttribute("tipos", lista2);
        String rpta = "/index.jsp";
        RequestDispatcher rd = request.getRequestDispatcher(rpta);
        rd.forward(request, response);
    }
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession ses = request.getSession(true);
        LoginIf login = new LoginDAO();
        
    }
    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
