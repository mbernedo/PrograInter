package edu.ulima.servlet;

import edu.ulima.bean.Pokemon;
import edu.ulima.bean.Tipo;
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
public class getPokemonesServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession ses = request.getSession(true);
        PokemonIf pokemon = new PokemonDAO();
        Pokemon poke = new Pokemon();
        List<Pokemon> lista;
        String var = request.getParameter("combo");
        if (request.getParameter("combo") == null) {
            lista = pokemon.obtenerPokemones("");
        } else {
            lista = pokemon.obtenerPokemones(var);
        }
        List<Tipo> lista2 = pokemon.obtenerTipos();
        List<Integer> codigos = pokemon.getCodigo();
        ses.setAttribute("pokemones", lista);
        ses.setAttribute("tipos", lista2);
        ses.setAttribute("codigos", codigos);
        String rpta = "/index.jsp";
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
