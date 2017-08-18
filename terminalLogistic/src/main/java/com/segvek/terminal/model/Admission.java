package com.segvek.terminal.model;

import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

public class Admission extends MainModel {

    public static Admission newInstance() {
	return new Admission(-1L, null, null, 0, null, null, null, null, null, null, true);
    }

    private Contract contract;
    private Tank tank;
    private int volume;
    private Date planBegin;
    private StationaryStorage storage;
    private DrainLocation drainLocation;
    private Date factBegin;
    private Date factEnd;
    private boolean plan = true;
    private Cargo cargo;

    private List<Admission> indepented;
    private List<Admission> depend;

    public Admission(Long id, Contract contract, Tank tank, int volume, Date planBegin, StationaryStorage storage, DrainLocation drainLocation, Date factBegin, Date factEnd, Cargo cargo, boolean plan) {
	super(id);
	this.contract = contract;
	this.tank = tank;
	this.volume = volume;
	this.planBegin = planBegin;
	this.storage = storage;
	this.drainLocation = drainLocation;
	this.factBegin = factBegin;
	this.factEnd = factEnd;
	this.cargo = cargo;
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

    public boolean isPlan() {
	return plan;
    }

    public void setPlan(boolean plan) {
	this.plan = plan;
    }

    public List<Admission> getIndepented() {
	return indepented;
    }

    public void setIndepented(List<Admission> indepented) {
	this.indepented = indepented;
    }

    public List<Admission> getDepend() {
	return depend;
    }

    public void setDepend(List<Admission> depend) {
	this.depend = depend;
    }

    public boolean addTime(int TypeTime, int time) {
	boolean result = true;
	Date now = new Date();
	GregorianCalendar c = new GregorianCalendar();
	c.setTime(getBegin());
	c.add(TypeTime, time);
	if (time < 0 && c.getTime().getTime() < now.getTime()) {
	    return false;
	}
	planBegin = c.getTime();
	if (time > 0) {
	    if (getIndepented() != null) {
		c.add(GregorianCalendar.MINUTE, getTank().getTypeTank().getTime());
		for (Admission a : getIndepented()) {
		    if (a.getBegin().getTime() < c.getTime().getTime()) {
			result &= a.addTime(TypeTime, time);
		    }
		}
	    }
	} else {
	    if (getDepend() != null) {
		for (Admission a : getDepend()) {
		    c.setTime(a.getBegin());
		    c.add(GregorianCalendar.MINUTE, a.getTank().getTypeTank().getTime());
		    if (planBegin.getTime() < c.getTime().getTime()) {
			result &= a.addTime(TypeTime, time);
		    }
		}
	    }
	}
	if (!result) {
	    c.setTime(getBegin());
	    c.add(TypeTime, -time);
	    planBegin = c.getTime();
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

    public Cargo getCargo() {
	return cargo;
    }

    public void setCargo(Cargo cargo) {
	this.cargo = cargo;
    }

    @Override
    public int hashCode() {
	return id.intValue();
    }

    public boolean isNewInstance() {
	return id.equals(-1L);
    }

    public void removeIndepented(Admission independet) {
	if (this.indepented == null) {
	    return;
	}
	System.out.println("==================== in " + independet);
	indepented.forEach(a -> System.out.println("in 1 " + a));

	this.indepented.remove(independet);
	indepented.forEach(a -> System.out.println("in 2 " + a));
    }

    public void removeDepend(Admission admission) {
	if (depend == null) {
	    return;
	}
	System.out.println("==================== dep " + admission);
	depend.forEach(a -> System.out.println("dep 1 " + a));

	this.depend.remove(admission);
	depend.forEach(a -> System.out.println("dep 1 " + a));
    }

}
