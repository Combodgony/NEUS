/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.segvek.terminal.interactiv.model;

import java.util.Date;

/**
 *
 * @author Panas
 */
public class Admission {
    
    private Long id;
    private Tank tank;
    private Date begin;
    private Date end;
    private DrainLocation drainLocation;
    private String status="План";

    public Admission(Long id,Tank tank, Date begin, Date end, DrainLocation drainLocation) {
        this.id=id;
        this.tank = tank;
        this.begin = begin;
        this.end = end;
        this.drainLocation = drainLocation;
    }

    public Admission(Long id,Tank tank, Date begin, Date end, DrainLocation drainLocation, String status) {
        this.id=id;
        this.tank = tank;
        this.begin = begin;
        this.end = end;
        this.drainLocation = drainLocation;
        this.status = status;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    

    public DrainLocation getDrainLocation() {
        return drainLocation;
    }

    public void setDrainLocation(DrainLocation drainLocation) {
        this.drainLocation = drainLocation;
    }

    public Tank getTank() {
        return tank;
    }

    public void setTank(Tank tank) {
        this.tank = tank;
    }

    public Date getBegin() {
        return begin;
    }

    public void setBegin(Date begin) {
        this.begin = begin;
    }

    public Date getEnd() {
        return end;
    }

    public void setEnd(Date end) {
        this.end = end;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
