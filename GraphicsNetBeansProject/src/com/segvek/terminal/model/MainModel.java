
package com.segvek.terminal.model;

public class MainModel {
    protected Long id;

    public MainModel(Long id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object obj) {
        return ((MainModel)obj).getId()==getId();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    
    
    
}
