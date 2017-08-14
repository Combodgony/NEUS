
package com.segvek.terminal.dao;

import com.segvek.terminal.model.DrainLocation;
import com.segvek.terminal.model.Estakada;
import java.util.List;


public interface EstakadaDAO  extends DAO{

    public List<Estakada> getAllEstacads() throws DAOException;

    public Estakada getEstakadaByDrainLocation(DrainLocation drainLocation) throws DAOException;
    
}
