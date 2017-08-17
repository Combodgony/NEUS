package com.segvek.terminal.model;


public class Tank extends MainModel{
    private String number;
    private TypeTank typeTank;

    public Tank(Long id, String number, TypeTank typeTank) {
        super(id);
        this.number = number;
        this.typeTank = typeTank;
    }

    public TypeTank getTypeTank() {
        return typeTank;
    }

    public void setTypeTank(TypeTank typeTank) {
        this.typeTank = typeTank;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    @Override
    public String toString() {
        return number;
    }
    
    
    
    
}
