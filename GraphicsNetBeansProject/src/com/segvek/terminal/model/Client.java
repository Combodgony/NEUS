package com.segvek.terminal.model;

import java.util.List;


public class Client {
    private Long id;
    private String name;
    private String adress;
    
    private List<Contract> contracts;

    public Client(Long id, String name, String adress) {
        this.id = id;
        this.name = name;
        this.adress = adress;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAdress() {
        return adress;
    }

    public void setAdress(String adress) {
        this.adress = adress;
    }

    public List<Contract> getContracts() {
        return contracts;
    }

    public void setContracts(List<Contract> contracts) {
        this.contracts = contracts;
    }
    
    
    @Override
    public String toString() {
        return name;
    }

    @Override
    public boolean equals(Object obj) {
        return obj instanceof Client && this.id==((Client)obj).getId(); 
    }
    
    
    
}
