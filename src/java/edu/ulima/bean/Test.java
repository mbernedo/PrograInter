/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.ulima.bean;

import edu.ulima.dao.PokemonDAO;
import edu.ulima.interfaces.PokemonIf;
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
        PokemonIf login = new PokemonDAO();
        Pokemon poke = login.getPokemon(2);
        System.out.println(poke.getNombre());
    }
    
}
