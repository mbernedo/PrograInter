package org.apache.jsp;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;

public final class juego_jsp extends org.apache.jasper.runtime.HttpJspBase
    implements org.apache.jasper.runtime.JspSourceDependent {

  private static final JspFactory _jspxFactory = JspFactory.getDefaultFactory();

  private static java.util.List<String> _jspx_dependants;

  private org.glassfish.jsp.api.ResourceInjector _jspx_resourceInjector;

  public java.util.List<String> getDependants() {
    return _jspx_dependants;
  }

  public void _jspService(HttpServletRequest request, HttpServletResponse response)
        throws java.io.IOException, ServletException {

    PageContext pageContext = null;
    HttpSession session = null;
    ServletContext application = null;
    ServletConfig config = null;
    JspWriter out = null;
    Object page = this;
    JspWriter _jspx_out = null;
    PageContext _jspx_page_context = null;

    try {
      response.setContentType("text/html;charset=UTF-8");
      pageContext = _jspxFactory.getPageContext(this, request, response,
      			null, true, 8192, true);
      _jspx_page_context = pageContext;
      application = pageContext.getServletContext();
      config = pageContext.getServletConfig();
      session = pageContext.getSession();
      out = pageContext.getOut();
      _jspx_out = out;
      _jspx_resourceInjector = (org.glassfish.jsp.api.ResourceInjector) application.getAttribute("com.sun.appserv.jsp.resource.injector");

      out.write("\n");
      out.write("\n");
      out.write("\n");
      java.lang.String usuario = null;
      synchronized (session) {
        usuario = (java.lang.String) _jspx_page_context.getAttribute("usuario", PageContext.SESSION_SCOPE);
        if (usuario == null){
          usuario = new java.lang.String();
          _jspx_page_context.setAttribute("usuario", usuario, PageContext.SESSION_SCOPE);
        }
      }
      out.write("\n");
      out.write("<!DOCTYPE html>\n");
      out.write("<html>\n");
      out.write("    <head>\n");
      out.write("        <meta http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\">\n");
      out.write("        <link href=\"twitter-bootstrap/css/bootstrap-theme.css\" rel=\"stylesheet\" type=\"text/css\"/>\n");
      out.write("        <link href=\"twitter-bootstrap/css/bootstrap.min.css\" rel=\"stylesheet\" type=\"text/css\"/>\n");
      out.write("        <link rel=\"stylesheet\" href=\"https://fonts.googleapis.com/icon?family=Material+Icons\">\n");
      out.write("        <link rel=\"stylesheet\" href=\"https://code.getmdl.io/1.2.1/material.indigo-pink.min.css\">\n");
      out.write("        <link href=\"twitter-bootstrap/css/tether.min.css\" rel=\"stylesheet\" type=\"text/css\"/>\n");
      out.write("        <link href=\"css/animate.css\" rel=\"stylesheet\" type=\"text/css\"/>\n");
      out.write("        <link href=\"twitter-bootstrap/css/sweetalert2.min.css\" rel=\"stylesheet\" type=\"text/css\"/>\n");
      out.write("        <link href=\"css/michiStyle.css\" rel=\"stylesheet\" type=\"text/css\"/>\n");
      out.write("        <title>Practica3 | Michi</title>\n");
      out.write("    </head>\n");
      out.write("    <body onload=\"connect();\" onunload=\"disconnect();\">\n");
      out.write("        <div class=\"container\">\n");
      out.write("            <div class=\"row\">\n");
      out.write("                <div>\n");
      out.write("                    <h1>Michi Game</h1>\n");
      out.write("                </div>\n");
      out.write("                <div class=\"col-xs-6 tablero\">\n");
      out.write("                    <div class=\"row\">\n");
      out.write("                        <div class=\"col-xs-4 celda seleccionado\" id=\"celda1\"></div>\n");
      out.write("                        <div class=\"col-xs-4 celda seleccionado\" id=\"celda2\"></div>\n");
      out.write("                        <div class=\"col-xs-4 celda seleccionado\" id=\"celda3\"></div>\n");
      out.write("                    </div>\n");
      out.write("                    <div class=\"row\">\n");
      out.write("                        <div class=\"col-xs-4 celda seleccionado\" id=\"celda4\"></div>\n");
      out.write("                        <div class=\"col-xs-4 celda seleccionado\" id=\"celda5\"></div>\n");
      out.write("                        <div class=\"col-xs-4 celda seleccionado\" id=\"celda6\"></div>\n");
      out.write("                    </div>\n");
      out.write("                    <div class=\"row\">\n");
      out.write("                        <div class=\"col-xs-4 celda seleccionado\" id=\"celda7\"></div>\n");
      out.write("                        <div class=\"col-xs-4 celda seleccionado\" id=\"celda8\"></div>\n");
      out.write("                        <div class=\"col-xs-4 celda seleccionado\" id=\"celda9\"></div>\n");
      out.write("                    </div>\n");
      out.write("                </div>\n");
      out.write("                <div class=\"col-xs-6 datos\">\n");
      out.write("                    <label>Jugador: </label>\n");
      out.write("                    <label>");
      out.print(usuario);
      out.write("</label>\n");
      out.write("                    <p id=\"estado\">Esperando</p>\n");
      out.write("                    <button id=\"btnEnviar\" class=\"btn btn-primary\">Enviar</button>\n");
      out.write("                </div>\n");
      out.write("            </div>\n");
      out.write("        </div>\n");
      out.write("        <script src=\"twitter-bootstrap/js/jquery.js\" type=\"text/javascript\"></script>\n");
      out.write("        <script src=\"twitter-bootstrap/js/bootstrap.min.js\" type=\"text/javascript\"></script>\n");
      out.write("        <script src=\"twitter-bootstrap/js/tether.min.js\" type=\"text/javascript\"></script>\n");
      out.write("        <script defer src=\"https://code.getmdl.io/1.2.1/material.min.js\"></script>\n");
      out.write("        <script src=\"twitter-bootstrap/js/sweetalert2.min.js\" type=\"text/javascript\"></script>\n");
      out.write("        <script src=\"js/michi.js\" type=\"text/javascript\"></script>\n");
      out.write("    </body>\n");
      out.write("</html>\n");
    } catch (Throwable t) {
      if (!(t instanceof SkipPageException)){
        out = _jspx_out;
        if (out != null && out.getBufferSize() != 0)
          out.clearBuffer();
        if (_jspx_page_context != null) _jspx_page_context.handlePageException(t);
        else throw new ServletException(t);
      }
    } finally {
      _jspxFactory.releasePageContext(_jspx_page_context);
    }
  }
}
