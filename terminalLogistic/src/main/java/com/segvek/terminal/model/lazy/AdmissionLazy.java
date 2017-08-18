package com.segvek.terminal.model.lazy;

import com.segvek.terminal.Loader;
import com.segvek.terminal.dao.AdmissionDAO;
import com.segvek.terminal.dao.ContractDAO;
import com.segvek.terminal.dao.DAOException;
import com.segvek.terminal.dao.DrainLocationDAO;
import com.segvek.terminal.dao.TankDAO;
import com.segvek.terminal.model.Admission;
import com.segvek.terminal.model.Cargo;
import com.segvek.terminal.model.Contract;
import com.segvek.terminal.model.DrainLocation;
import com.segvek.terminal.model.StationaryStorage;
import com.segvek.terminal.model.Tank;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import com.segvek.terminal.dao.CargoDAO;
import java.util.List;

public class AdmissionLazy extends Admission {

    private ContractDAO contractDAO;
    private TankDAO tankDAO;
    private DrainLocationDAO drainLocationDAO;
    private CargoDAO cargoDao;
    private AdmissionDAO admissionDAO;

    public AdmissionLazy(Long id, Contract contract, Tank tank, int volume, Date planBegin, StationaryStorage storage, DrainLocation drainLocation, Date factBegin, Date factEnd, Cargo cargo, boolean plan) {
        super(id, contract, tank, volume, planBegin, storage, drainLocation, factBegin, factEnd, cargo, plan);
        contractDAO = Loader.getContext().getBean("contractDAO", ContractDAO.class);
        drainLocationDAO = Loader.getContext().getBean("drainLocationDAO", DrainLocationDAO.class);
        tankDAO = Loader.getContext().getBean("tankDAO", TankDAO.class);
        cargoDao = Loader.getContext().getBean("cargoDAO", CargoDAO.class);
        admissionDAO = Loader.getContext().getBean("admissionDAO", AdmissionDAO.class);
    }

    @Override
    public Contract getContract() {
        Contract contract = super.getContract();
        if (contract == null) {
            try {
                contract = contractDAO.getContractByAdmission(this);
                setContract(contract);
            } catch (DAOException ex) {
                Logger.getLogger(AdmissionLazy.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return contract;
    }

    @Override
    public Tank getTank() {
        Tank tank = super.getTank();
        if (tank == null) {
            try {
                tank = tankDAO.getTankByAdmission(this);
                setTank(tank);
            } catch (DAOException ex) {
                Logger.getLogger(AdmissionLazy.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return tank;
    }

    @Override
    public DrainLocation getDrainLocation() {
        DrainLocation location = super.getDrainLocation();
        if (location == null) {
            try {
                location = drainLocationDAO.getDrainLocationByAdmission(this);
                setDrainLocation(location);
            } catch (DAOException ex) {
                Logger.getLogger(AdmissionLazy.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return location;
    }

    @Override
    public Cargo getCargo() {
        Cargo c = super.getCargo();
        if (c == null) {
            try {
                c = cargoDao.getCargoByAdmission(this);
                setCargo(c);
            } catch (DAOException ex) {
                Logger.getLogger(AdmissionLazy.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return c;
    }

    @Override
    public List<Admission> getIndepented() {
        List<Admission> list = super.getIndepented();
        if (list == null) {
            try {
                list = admissionDAO.getIndependentAdmissionByAdmission(this);
                setIndepented(list);
            } catch (DAOException ex) {
                Logger.getLogger(AdmissionLazy.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return list;
    }
    
    @Override
    public List<Admission> getDepend() {
        List<Admission> list = super.getDepend();
        if (list == null) {
            try {
                list = admissionDAO.getDependAdmissionByAdmission(this);
                setDepend(list);
            } catch (DAOException ex) {
                Logger.getLogger(AdmissionLazy.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return list;
    }
    
    
    
    
    

    //todo: add lazy load storage

    @Override
    public boolean addTime(int TypeTime, int time) {
        getDepend();
        getIndepented();
        getTank();
        return super.addTime(TypeTime, time);
    }
}
