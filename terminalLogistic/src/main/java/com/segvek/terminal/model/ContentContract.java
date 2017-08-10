package com.segvek.terminal.model;


public class ContentContract {
    private Cargo cargo;
    private Contract contract;
    private int volume;

    public ContentContract(Cargo cargo, Contract contract, int volume) {
        this.cargo = cargo;
        this.contract = contract;
        this.volume = volume;
    }

    public int getVolume() {
        return volume;
    }

    public void setVolume(int volume) {
        this.volume = volume;
    }

    public Cargo getCargo() {
        return cargo;
    }

    public void setCargo(Cargo cargo) {
        this.cargo = cargo;
    }

    public Contract getContract() {
        return contract;
    }

    public void setContract(Contract contract) {
        this.contract = contract;
    }

    @Override
    public String toString() {
        return cargo.getName();
    }
    
    
}
