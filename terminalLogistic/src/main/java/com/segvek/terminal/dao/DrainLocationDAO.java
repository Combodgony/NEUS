
package com.segvek.terminal.dao;

import com.segvek.terminal.model.Admission;
import com.segvek.terminal.model.DrainLocation;
import com.segvek.terminal.model.Estakada;
import java.util.List;


public interface DrainLocationDAO  extends DAO{

    public DrainLocation getDrainLocationByAdmission(Admission admission) throws DAOException;

    public List<DrainLocation> getDrainLocationsByEstacada(Estakada estakada) throws DAOException;
    
}
