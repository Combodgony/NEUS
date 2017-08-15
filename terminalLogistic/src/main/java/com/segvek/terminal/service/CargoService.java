package com.segvek.terminal.service;

import com.segvek.terminal.Loader;
import com.segvek.terminal.dao.DAOException;
import com.segvek.terminal.model.Cargo;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import com.segvek.terminal.dao.CargoDAO;


public class CargoService {
    CargoDAO cargoDao;

    public CargoService() {
        cargoDao = Loader.getContext().getBean("cargoDAO",CargoDAO.class);
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
