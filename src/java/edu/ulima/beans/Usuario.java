
package edu.ulima.beans;

import java.util.List;

/**
 *
 * @author mbernedo
 */
public class Usuario {
    private String usuario, clave;
    private Archivo excel;

    public Usuario() {
    }

    public Archivo getExcel() {
        return excel;
    }

    public void setExcel(Archivo excel) {
        this.excel = excel;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getClave() {
        return clave;
    }

    public void setClave(String clave) {
        this.clave = clave;
    }
    
    
    
}
