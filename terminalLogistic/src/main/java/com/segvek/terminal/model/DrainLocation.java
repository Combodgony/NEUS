/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.segvek.terminal.model;


public class DrainLocation extends MainModel{
    String number;
    Estakada estakada;

    public DrainLocation(Long id, String number, Estakada estakada) {
        super(id);
        this.number = number;
        this.estakada = estakada;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public Estakada getEstakada() {
        return estakada;
    }

    public void setEstakada(Estakada estakada) {
        this.estakada = estakada;
    }

    
    
    
}
