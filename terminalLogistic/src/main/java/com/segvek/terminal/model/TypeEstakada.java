package com.segvek.terminal.model;

/**
 *
 * @author Panas
 */
public class TypeEstakada extends MainModel{
    
    private String name;

    public TypeEstakada(Long id, String name) {
        super(id);
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
