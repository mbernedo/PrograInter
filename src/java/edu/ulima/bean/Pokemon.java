/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.ulima.bean;

/**
 *
 * @author migue_000
 */
public class Pokemon {
    private int id;
    private String nombre;
    private Tipo tipo= new Tipo();

    public Pokemon(int id, String nombre, Tipo tipo) {
        this.id = id;
        this.nombre = nombre;
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

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Tipo getTipo() {
        return tipo;
    }

    public void setTipo(Tipo tipo) {
        this.tipo = tipo;
    }

    
    
}
