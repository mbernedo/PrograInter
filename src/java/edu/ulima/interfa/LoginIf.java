/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.ulima.interfa;

import edu.ulima.bean.Pokemon;
import edu.ulima.bean.Tipo;
import java.util.List;

/**
 *
 * @author migue_000
 */
public interface LoginIf {
    public List<Pokemon> obtenerPokemones();
    public List<Tipo> obtenerTipos();
    public void insertarPokemon(Pokemon pok);
    public void borrarPokemon(int pos);
    public void modificarPokemon(int id, String nombre);
}
