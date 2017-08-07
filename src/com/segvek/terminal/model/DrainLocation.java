/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.segvek.terminal.model;


public class DrainLocation {
    Long id;
    int number;
    Estakada estakada;

    public DrainLocation(Long id, int number, Estakada estakada) {
        this.id = id;
        this.number = number;
        this.estakada = estakada;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public Estakada getEstakada() {
        return estakada;
    }

    public void setEstakada(Estakada estakada) {
        this.estakada = estakada;
    }

    
    
    
}
