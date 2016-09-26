/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.ulima.bean;

import edu.ulima.dao.LoginDAO;
import edu.ulima.interfa.LoginIf;
import java.util.List;

/**
 *
 * @author migue_000
 */
public class Test {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        LoginIf login = new LoginDAO();
        List<Pokemon> lista = login.obtenerPokemones("Fuego");
        for (Pokemon tipo : lista) {
            System.out.println(tipo.getNombre());
        }
    }
    
}
