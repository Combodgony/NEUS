package com.segvek.terminal.model;

import java.util.List;


public class Client extends MainModel{
    private String name;
    private String adress;
    
    private List<Contract> contracts;

    public Client(Long id, String name, String adress) {
        super(id);
        this.name = name;
        this.adress = adress;
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
    
}
