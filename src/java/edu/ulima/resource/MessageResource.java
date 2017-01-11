package edu.ulima.resource;

import edu.ulima.beans.Archivo;
import edu.ulima.beans.Respuesta;
import edu.ulima.beans.Usuario;
import edu.ulima.beans.Valores;
import edu.ulima.dao.ServiceDAO;
import edu.ulima.interfaces.ServiceIF;
import java.util.ArrayList;
import java.util.List;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

/**
 *
 * @author mbernedo
 */
@Path("/Final")
public class MessageResource {

    @POST
    @Consumes({MediaType.APPLICATION_JSON})
    @Produces({MediaType.APPLICATION_JSON})
    @Path("/registro")
    public Respuesta registrarUsuario(Usuario usuario) throws Exception {
        ServiceIF servicio = new ServiceDAO();
        Respuesta respuesta = new Respuesta();
        Usuario user = new Usuario();
        user.setUsuario(usuario.getUsuario());
        user.setClave(usuario.getClave());
        user.setExcel(usuario.getExcel());
        respuesta = servicio.registro(usuario);
        return respuesta;
    }

    @POST
    @Consumes({MediaType.APPLICATION_JSON})
    @Produces({MediaType.APPLICATION_JSON})
    @Path("/login")
    public Respuesta loginUsuario(Usuario usuario) throws Exception {
        ServiceIF servicio = new ServiceDAO();
        Respuesta respuesta = new Respuesta();
        Usuario user = new Usuario();
        user.setUsuario(usuario.getUsuario());
        user.setClave(usuario.getClave());
        if (servicio.login(user)) {
            respuesta.setCod(1);
            respuesta.setMsg("Login correcto");
            respuesta.getUsuario().setUsuario(usuario.getUsuario());
        } else {
            respuesta.setCod(0);
            respuesta.setMsg("Login incorrecto");
        }
        return respuesta;
    }

    @POST
    @Consumes({MediaType.APPLICATION_JSON})
    @Produces({MediaType.APPLICATION_JSON})
    @Path("/guardar")
    public Respuesta guardarExcel(Usuario usuario) throws Exception {
        ServiceIF servicio = new ServiceDAO();
        Respuesta respuesta = new Respuesta();
        Usuario user = new Usuario();
        if (servicio.guardarExcel(usuario)) {
            respuesta.setCod(1);
            respuesta.setMsg("Se guardó el archivo");
            respuesta.getUsuario().setUsuario(usuario.getUsuario());
        } else {
            respuesta.setCod(0);
            respuesta.setMsg("No se pudo guardar el archivo");
        }
        return respuesta;
    }

    @POST
    @Consumes({MediaType.APPLICATION_JSON})
    @Produces({MediaType.APPLICATION_JSON})
    @Path("/abrir")
    public List<String> getDocs(Usuario usuario) throws Exception {
        ServiceIF serv = new ServiceDAO();
        Usuario user = new Usuario();
        List<String> nomArc = new ArrayList<>();
        user.setUsuario("mbernedo");
        List<Archivo> as = serv.getDocs(user);
        for (Archivo a : as) {
            nomArc.add(a.getNomArc());
        }
        return nomArc;
    }
}
