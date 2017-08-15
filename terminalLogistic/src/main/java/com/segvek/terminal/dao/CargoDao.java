
package com.segvek.terminal.dao;

import com.segvek.terminal.model.Admission;
import com.segvek.terminal.model.Cargo;
import java.util.List;


public interface CargoDao  extends DAO{
    List<Cargo> getAllCargo() throws DAOException;

    Cargo getCargoByAdmission(Admission admission) throws DAOException;
}
