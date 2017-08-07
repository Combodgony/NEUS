package com.segvek.terminal.model;

import java.util.ArrayList;
import java.util.List;

public class Estakada {
    private Long id;
    private TypeEstakada typeEstakada;
    private String name; 
    private List<DrainLocation> drainLocations;

    public Estakada(Long id, TypeEstakada typeEstakada, String name, List<DrainLocation> drainLocations) {
        this.id = id;
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

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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
