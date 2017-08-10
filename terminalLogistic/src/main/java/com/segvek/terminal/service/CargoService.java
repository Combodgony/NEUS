package com.segvek.terminal.service;

import com.segvek.terminal.dao.CargoDao;
import com.segvek.terminal.dao.DAOException;
import com.segvek.terminal.dao.mysql.CargoMysqlDAO;
import com.segvek.terminal.model.Cargo;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;


public class CargoService {
    CargoDao cargoDao;

    public CargoService() {
        cargoDao = new CargoMysqlDAO();
    }
    
    public List<Cargo> getAllCargo() throws ServiceException{
        try {
            return cargoDao.getAllCargo();
        } catch (DAOException ex) {
            Logger.getLogger(CargoService.class.getName()).log(Level.SEVERE, null, ex);
            throw new ServiceException();
        }
    }
}
