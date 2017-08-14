package com.segvek.terminal.dao;

import com.segvek.terminal.model.Admission;
import com.segvek.terminal.model.Tank;


public interface TankDAO  extends DAO{

    public Tank getTankByAdmission(Admission admission) throws DAOException;
    
}
