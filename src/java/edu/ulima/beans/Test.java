/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.ulima.beans;

import com.mysql.jdbc.exceptions.jdbc4.MySQLSyntaxErrorException;
import edu.ulima.dao.ServiceDAO;
import edu.ulima.interfaces.ServiceIF;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

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
        List<Juego> lista = serv.getJuegos("chachi");
        for (int i=0; i<lista.size(); i++) {
            System.out.println(lista.get(i).getGamer1() + " 2: " + lista.get(i).getGamer2());
        }
    }
    
}
