/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.ulima.beans;


import edu.ulima.dao.ServiceDAO;
import edu.ulima.interfaces.ServiceIF;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.json.simple.JSONObject;

/**
 *
 * @author mbernedo
 */
public class Test {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        ServiceIF serv = new ServiceDAO();
        Usuario user = new Usuario();
        user.setUsuario("mbernedo");
        List<Archivo> as = serv.getDocs(user);
        for (Archivo a : as) {
            System.out.println(a.getNomArc());
        }
    }
}
