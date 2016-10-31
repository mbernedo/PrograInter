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
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("cod",1);
        jsonObject.put("msg", "Esperando");
        System.out.println(jsonObject);
        JSONObject obj = (JSONObject) jsonObject;
        String mensaje = (String) obj.get("msg");
        int codigo = (int) obj.get("cod");
        System.out.println(mensaje + codigo);
    }
    
}
