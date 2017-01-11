/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.ulima.interfaces;

import edu.ulima.beans.Archivo;
import edu.ulima.beans.Respuesta;
import edu.ulima.beans.Usuario;
import java.util.List;

/**
 *
 * @author mbernedo
 */
public interface ServiceIF {
     
    public boolean login(Usuario usuario);
    
    public Respuesta registro(Usuario usuario);
    
    public boolean guardarExcel(Usuario usuario);
    
    public List<Archivo> getDocs(Usuario usuario);
}
