
package com.segvek.terminal.model.lazy;

import com.segvek.terminal.Loader;
import com.segvek.terminal.dao.DAOException;
import com.segvek.terminal.dao.TypeEstakadaDAO;
import com.segvek.terminal.model.TypeEstakada;
import com.segvek.terminal.model.TypeTank;
import java.util.logging.Level;
import java.util.logging.Logger;

public class TypeTankLazy extends TypeTank{
    TypeEstakadaDAO typeEstakadaDAO;
    
    public TypeTankLazy(Long id, int time, int vMax, TypeEstakada typeEstakada) {
        super(id, time, vMax, typeEstakada);
        typeEstakadaDAO = Loader.getContext().getBean("typeEstakadaDAO", TypeEstakadaDAO.class);
    }
    

    @Override
    public TypeEstakada getTypeEstakada() {
        TypeEstakada te = super.getTypeEstakada();
        if(te==null){
            try {
                te=typeEstakadaDAO.getTypeEstacadaByTypeTank(this);
            } catch (DAOException ex) {
                Logger.getLogger(TypeTankLazy.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return te;
    }

}
