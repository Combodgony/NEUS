package com.segvek.terminal.model;


public class TypeTank extends MainModel{
    private int time;
    private int vMax;
    private TypeEstakada typeEstakada;

    public TypeTank(Long id, int time, int vMax, TypeEstakada typeEstakada) {
        super(id);
        this.time = time;
        this.vMax = vMax;
        this.typeEstakada = typeEstakada;
    }

    

    public int getvMax() {
        return vMax;
    }

    public void setvMax(int vMax) {
        this.vMax = vMax;
    }

    public int getTime() {
        return time;
    }

    public void setTime(int time) {
        this.time = time;
    }

    public TypeEstakada getTypeEstakada() {
        return typeEstakada;
    }

    public void setTypeEstakada(TypeEstakada typeEstakada) {
        this.typeEstakada = typeEstakada;
    }
    
    
   
}
