package com.segvek.terminal.interactiv.model;


public class TypeTank {
    private Long id;
    private int time;
    private int vMax;

    public TypeTank(Long id, int time, int vMax) {
        this.id = id;
        this.time = time;
        this.vMax = vMax;
    }

    public int getvMax() {
        return vMax;
    }

    public void setvMax(int vMax) {
        this.vMax = vMax;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getTime() {
        return time;
    }

    public void setTime(int time) {
        this.time = time;
    }
   
}
