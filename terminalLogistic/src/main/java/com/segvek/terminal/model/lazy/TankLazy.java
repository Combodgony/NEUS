
package com.segvek.terminal.model.lazy;

import com.segvek.terminal.dao.DAOException;
import com.segvek.terminal.dao.TypeTankDAO;
import com.segvek.terminal.dao.mysql.TypeTankMysqlDAO;
import com.segvek.terminal.model.Tank;
import com.segvek.terminal.model.TypeTank;
import java.util.logging.Level;
import java.util.logging.Logger;


public class TankLazy extends Tank{
    TypeTankDAO typeTankDAO = new TypeTankMysqlDAO();
    
    public TankLazy(Long id, String number, TypeTank typeTank) {
        super(id, number, typeTank);
    }

    @Override
    public TypeTank getTypeTank() {
        TypeTank t = super.getTypeTank();
        if(t==null){
            try {
                t = typeTankDAO.getTypeTankByTank(this);
                setTypeTank(t);
            } catch (DAOException ex) {
                Logger.getLogger(TankLazy.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return t;
    }

}
