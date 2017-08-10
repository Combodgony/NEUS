
package com.segvek.terminal.dao;

import com.segvek.terminal.model.Cargo;
import java.util.List;


public interface CargoDao {
    List<Cargo> getAllCargo() throws DAOException;
}
