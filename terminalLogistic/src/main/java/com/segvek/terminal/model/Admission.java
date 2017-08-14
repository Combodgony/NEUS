
package com.segvek.terminal.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.GregorianCalendar;

public class Admission extends MainModel{
    
    private Contract contract;
    private Tank tank;
    private int volume;
    private Date planBegin;
    private StationaryStorage storage;
    private DrainLocation drainLocation;
    private Date factBegin;
    private Date factEnd;  
    private boolean plan=true;
    
    private ArrayList<Admission> indepented = new ArrayList<>();
    private ArrayList<Admission> depend = new ArrayList<>();

    public Admission(Long id, Contract contract, Tank tank, int volume, Date planBegin, StationaryStorage storage, DrainLocation drainLocation, Date factBegin, Date factEnd, boolean plan) {
        super(id);
        this.contract = contract;
        this.tank = tank;
        this.volume = volume;
        this.planBegin = planBegin;
        this.storage = storage;
        this.drainLocation = drainLocation;
        this.factBegin = factBegin;
        this.factEnd = factEnd;
        this.plan = plan;
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
        return planBegin;
    }

    public void setBegin(Date begin) {
        this.planBegin = begin;
    }

  
    public boolean isPlan(){
        return plan;
    }

    public void setStatus(boolean plan) {
        this.plan = plan;
    }

    public ArrayList<Admission> getIndepented() {
        return indepented;
    }

    public void setIndepented(ArrayList<Admission> indepented) {
        this.indepented = indepented;
    }

    public ArrayList<Admission> getDepend() {
        return depend;
    }

    public void setDepend(ArrayList<Admission> depend) {
        this.depend = depend;
    }
    
    public boolean addTime(int TypeTime, int time) {
        boolean result=true;
        Date now = new Date();
        GregorianCalendar c = new GregorianCalendar();
        c.setTime(getBegin());
        c.add(TypeTime, time);
        if(time<0 && c.getTime().getTime()<now.getTime())
            return false;
        planBegin =c.getTime();
        if(time>0){           
            c.add(GregorianCalendar.MINUTE, tank.getTypeTank().getTime());
            for(Admission a:indepented){
                if(a.getBegin().getTime()<c.getTime().getTime()){
                    result &=a.addTime(TypeTime, time);
                }
            }
        }else{
            for(Admission a:depend){
                c.setTime(a.getBegin());
                c.add(GregorianCalendar.MINUTE, a.getTank().getTypeTank().getTime());
                if(planBegin.getTime()<c.getTime().getTime()){
                    result &=a.addTime(TypeTime, time);
                }
            }
            
        } 
        if(!result){
            c.setTime(getBegin());
            c.add(TypeTime, -time);
            planBegin =c.getTime();
        }
            
        return result;
    }

    public Date getFactBegin() {
        return factBegin;
    }

    public void setFactBegin(Date factBegin) {
        this.factBegin = factBegin;
    }

    public Date getFactEnd() {
        return factEnd;
    }

    public void setFactEnd(Date factEnd) {
        this.factEnd = factEnd;
    }

    public Contract getContract() {
        return contract;
    }

    public void setContract(Contract contract) {
        this.contract = contract;
    }

    public int getVolume() {
        return volume;
    }

    public void setVolume(int volume) {
        this.volume = volume;
    }

    public Date getPlanBegin() {
        return planBegin;
    }

    public void setPlanBegin(Date planBegin) {
        this.planBegin = planBegin;
    }

    public StationaryStorage getStorage() {
        return storage;
    }

    public void setStorage(StationaryStorage storage) {
        this.storage = storage;
    }

    @Override
    public String toString() {
        return id.toString();
    }
    
    
    
    
}
