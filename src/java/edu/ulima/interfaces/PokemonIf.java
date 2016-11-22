/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.ulima.interfaces;

import edu.ulima.bean.Pokemon;
import edu.ulima.bean.Tipo;
import java.util.List;

/**
 *
 * @author migue_000
 */
public interface PokemonIf {
    public List<Pokemon> obtenerPokemones(int combo);
    public List<Tipo> obtenerTipos();
    public Pokemon getPokemon(int id);
    //public List<Integer> getCodigo();
    public void borrarPokemon(int id);
    public void insertarPokemon(Pokemon pok);
    public void modificarPokemon(int id, Pokemon pok);
}
