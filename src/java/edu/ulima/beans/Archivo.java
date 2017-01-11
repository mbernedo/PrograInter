/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.ulima.beans;

import java.util.List;

/**
 *
 * @author mbernedo
 */
public class Archivo {

    private String nomArc;
    private List<Valores> celdas;

    public Archivo() {
    }

    public String getNomArc() {
        return nomArc;
    }

    public void setNomArc(String nomArc) {
        this.nomArc = nomArc;
    }

    public List<Valores> getCeldas() {
        return celdas;
    }

    public void setCeldas(List<Valores> celdas) {
        this.celdas = celdas;
    }

    
}
