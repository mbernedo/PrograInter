package edu.ulima.servlet;

import com.mysql.jdbc.exceptions.jdbc4.MySQLSyntaxErrorException;
import edu.ulima.beans.Juego;
import edu.ulima.dao.ServiceDAO;
import edu.ulima.interfaces.ServiceIF;
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
 * @author mbernedo
 */
public class iniciarServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession ses = request.getSession(true);
        ServiceIF serv = new ServiceDAO();
        try {
            serv.uniqueConstraint();
        } catch (MySQLSyntaxErrorException ex) {
            System.out.println("Ya hay unique");
        } catch (Exception e) {
            System.out.println("Error");
        } finally {
            String rpta1 = "/index.html";
            String rpta2 = "/juego.jsp";
            RequestDispatcher rd1 = request.getRequestDispatcher(rpta1);
            RequestDispatcher rd2 = request.getRequestDispatcher(rpta2);
            rd1.forward(request, response);
            rd2.forward(request, response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }
}
