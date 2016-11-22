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
public class Tipo implements Serializable{
    private int idTipo;
    private String nomTipo;

    public Tipo(int idTipo, String nomTipo) {
        this.idTipo = idTipo;
        this.nomTipo = nomTipo;
    }

    public Tipo() {
    }

    public int getIdTipo() {
        return idTipo;
    }

    public void setIdTipo(int idTipo) {
        this.idTipo = idTipo;
    }

    public String getNomTipo() {
        return nomTipo;
    }

    public void setNomTipo(String nomTipo) {
        this.nomTipo = nomTipo;
    }
}
