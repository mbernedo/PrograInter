
package edu.ulima.resource;

import edu.ulima.beans.Respuesta;
import edu.ulima.beans.Usuario;
import edu.ulima.dao.ServiceDAO;
import edu.ulima.interfaces.ServiceIF;
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
@Path("/practica3")
public class MessageResource {
    
    @POST
    @Consumes({MediaType.APPLICATION_JSON})
    @Produces({MediaType.APPLICATION_JSON})
    @Path("/registro")
    public Respuesta registrarUsuario(Usuario usuario) throws Exception{
        ServiceIF servicio = new ServiceDAO();
        Respuesta respuesta = new Respuesta();
        Usuario user = new Usuario();
        user.setUsuario(usuario.getUsuario());
        user.setClave(usuario.getClave());
        respuesta = servicio.registro(usuario);
        return respuesta;
    }
    
    @POST
    @Consumes({MediaType.APPLICATION_JSON})
    @Produces({MediaType.APPLICATION_JSON})
    @Path("/login")
    public Respuesta loginUsuario(Usuario usuario) throws Exception{
        ServiceIF servicio = new ServiceDAO();
        Respuesta respuesta = new Respuesta();
        Usuario user = new Usuario();
        user.setUsuario(usuario.getUsuario());
        user.setClave(usuario.getClave());
        if(servicio.login(user)){
            respuesta.setCod(1);
            respuesta.setMsg("Login correcto");
            respuesta.getUsuario().setUsuario(usuario.getUsuario());
        }else{
            respuesta.setCod(0);
            respuesta.setMsg("Login incorrecto");
        }
        return respuesta;
    }
}
