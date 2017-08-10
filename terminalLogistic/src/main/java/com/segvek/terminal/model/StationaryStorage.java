package com.segvek.terminal.model;



public class StationaryStorage extends MainModel{
    private String code;

    public StationaryStorage(Long id, String code) {
        super(id);
        this.code = code;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}
