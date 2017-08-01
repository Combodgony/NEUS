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
    
    private Tank tank;
    private Date begin;
    private Date end;
    private DrainLocation drainLocation;

    public Admission(Tank tank, Date begin, Date end, DrainLocation drainLocation) {
        this.tank = tank;
        this.begin = begin;
        this.end = end;
        this.drainLocation = drainLocation;
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
}
