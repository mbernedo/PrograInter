
package edu.ulima.beans;

/**
 *
 * @author mbernedo
 */
public class Respuesta {
    public int cod;
    public Usuario usuario;
    public String msg;

    public Respuesta() {
        usuario = new Usuario();
    }

    public int getCod() {
        return cod;
    }

    public void setCod(int cod) {
        this.cod = cod;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
    
    
    
}
