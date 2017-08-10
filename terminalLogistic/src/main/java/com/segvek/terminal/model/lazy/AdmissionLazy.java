
package com.segvek.terminal.model.lazy;

import com.segvek.terminal.dao.ContractDAO;
import com.segvek.terminal.dao.DAOException;
import com.segvek.terminal.dao.DrainLocationDAO;
import com.segvek.terminal.dao.TankDAO;
import com.segvek.terminal.dao.mysql.ContractMysqlDAO;
import com.segvek.terminal.dao.mysql.DrainLocationMysqlDAO;
import com.segvek.terminal.dao.mysql.TankMysqlDAO;
import com.segvek.terminal.model.Admission;
import com.segvek.terminal.model.Contract;
import com.segvek.terminal.model.DrainLocation;
import com.segvek.terminal.model.StationaryStorage;
import com.segvek.terminal.model.Tank;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

public class AdmissionLazy extends Admission{
    
    ContractDAO contractDAO = new ContractMysqlDAO();
    TankDAO tankDAO = new TankMysqlDAO();
    DrainLocationDAO drainLocationDAO = new DrainLocationMysqlDAO();
    
    public AdmissionLazy(Long id, Contract contract, Tank tank, int volume, Date planBegin, StationaryStorage storage, DrainLocation drainLocation, Date factBegin, Date factEnd, boolean plan) {
        super(id, contract, tank, volume, planBegin, storage, drainLocation, factBegin, factEnd, plan);
    }

    @Override
    public Contract getContract() {
        Contract contract = super.getContract();
        if(contract==null){
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
        if(tank==null){
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
        if(location==null){
            try {
                location=drainLocationDAO.getDrainLocationByAdmission(this);
                setDrainLocation(location);
            } catch (DAOException ex) {
                Logger.getLogger(AdmissionLazy.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return location; 
    }
    
    //todo: add lazy load storage
    
    //todo: add lazy load depend
    
    //todo: add lazy load independet
    
}
