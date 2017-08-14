
package com.segvek.terminal.model.lazy;

import com.segvek.terminal.Loader;
import com.segvek.terminal.dao.DAOException;
import com.segvek.terminal.dao.EstakadaDAO;
import com.segvek.terminal.model.DrainLocation;
import com.segvek.terminal.model.Estakada;
import java.util.logging.Level;
import java.util.logging.Logger;



public class DrainLocationLazy extends DrainLocation{
    EstakadaDAO estakadaDAO;
    
    public DrainLocationLazy(Long id, String number, Estakada estakada) {
        super(id, number, estakada);
        estakadaDAO = Loader.getContext().getBean("estakadDAO", EstakadaDAO.class);
    }

    @Override
    public Estakada getEstakada() {
        Estakada estakada = super.getEstakada();
        if(estakada==null){
            try {
                estakada = estakadaDAO.getEstakadaByDrainLocation(this);
                setEstakada(estakada);
            } catch (DAOException ex) {
                Logger.getLogger(DrainLocationLazy.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return estakada;
    }
    
    
}
