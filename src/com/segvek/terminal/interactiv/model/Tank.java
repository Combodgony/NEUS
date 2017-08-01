package com.segvek.terminal.interactiv.model;


public class Tank {
    private Long id;
    private String number;
    private TypeTank typeTank;

    public Tank(Long id, String number, TypeTank typeTank) {
        this.id = id;
        this.number = number;
        this.typeTank = typeTank;
    }

    public TypeTank getTypeTank() {
        return typeTank;
    }

    public void setTypeTank(TypeTank typeTank) {
        this.typeTank = typeTank;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }
    
    
}
