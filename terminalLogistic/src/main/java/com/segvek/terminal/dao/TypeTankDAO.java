
package com.segvek.terminal.dao;

import com.segvek.terminal.model.Tank;
import com.segvek.terminal.model.TypeTank;


public interface TypeTankDAO  extends DAO{

    public TypeTank getTypeTankByTank(Tank tank) throws DAOException;
    
}
