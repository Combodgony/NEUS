
package com.segvek.terminal.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.GregorianCalendar;

public class Admission {
    
    private Long id;
    private Contract contract;
    private Tank tank;
    private int volume;
    private Date planBegin;
    private DrainLocation drainLocation;
    private Date factBegin;
    private Date factEnd;  
    
    private String status="План";
    
    private ArrayList<Admission> indepented = new ArrayList<>();
    private ArrayList<Admission> depend = new ArrayList<>();

    public Admission(Long id,Tank tank, Date planBegin, Date factBegin, Date factEnd, DrainLocation drainLocation) {
        this.id=id;
        this.tank = tank;
        this.planBegin = planBegin;
        this.factBegin=factBegin;
        this.factEnd = factEnd;
        this.drainLocation = drainLocation;
    }

    public Admission(Long id,Tank tank, Date begin, Date factBegin, Date factEnd, DrainLocation drainLocation, String status) {
        this.id=id;
        this.tank = tank;
        this.planBegin = begin;
        this.factBegin=factBegin;
        this.factEnd = factEnd;
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
        return planBegin;
    }

    public void setBegin(Date begin) {
        this.planBegin = begin;
    }

  

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
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
    
    
    @Override
    public int hashCode() {
        return id.intValue(); //To change body of generated methods, choose Tools | Templates.
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
    
    
}
