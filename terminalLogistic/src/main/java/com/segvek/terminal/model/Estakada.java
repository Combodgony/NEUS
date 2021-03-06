package com.segvek.terminal.model;

import java.util.List;

public class Estakada extends MainModel{
    private TypeEstakada typeEstakada;
    private String name; 
    private List<DrainLocation> drainLocations;

    public Estakada(Long id, TypeEstakada typeEstakada, String name, List<DrainLocation> drainLocations) {
        super(id);
        this.typeEstakada = typeEstakada;
        this.name = name;
        this.drainLocations = drainLocations;
    }

   

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public TypeEstakada getTypeEstakada() {
        return typeEstakada;
    }

    public void setTypeEstakada(TypeEstakada typeEstakada) {
        this.typeEstakada = typeEstakada;
    }

    public List<DrainLocation> getDrainLocations() {
        return drainLocations;
    }

    public void setDrainLocations(List<DrainLocation> drainLocations) {
        this.drainLocations = drainLocations;
    }
    
    
    
    
}
