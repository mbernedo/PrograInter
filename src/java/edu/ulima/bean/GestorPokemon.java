/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.ulima.bean;

import java.util.List;

/**
 *
 * @author mbernedo
 */
public class GestorPokemon {

    public GestorPokemon() {
    }
    public  static Pokemon getPokemon(int id, List<Pokemon> lista){
        for (Pokemon pokemon : lista) {
            if(pokemon.getId()==id){
                return pokemon;
            }
        }
        return null;
    }
}
