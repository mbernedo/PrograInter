/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.ulima.bean;

import java.io.Serializable;

/**
 *
 * @author migue_000
 */
public class Pokemon implements Serializable{
    private int id;
    private int cod_pokedex;
    private String nombre;
    private int hp;
    private Tipo tipo = new Tipo();

    public Pokemon(int id, int cod_pokedex, String nombre, int hp, Tipo tipo) {
        this.id = id;
        this.cod_pokedex = cod_pokedex;
        this.nombre = nombre;
        this.hp = hp;
        this.tipo = tipo;
    }

    public Pokemon() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCod_pokedex() {
        return cod_pokedex;
    }

    public void setCod_pokedex(int cod_pokedex) {
        this.cod_pokedex = cod_pokedex;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getHp() {
        return hp;
    }

    public void setHp(int hp) {
        this.hp = hp;
    }

    public Tipo getTipo() {
        return tipo;
    }

    public void setTipo(Tipo tipo) {
        this.tipo = tipo;
    }
    
}
