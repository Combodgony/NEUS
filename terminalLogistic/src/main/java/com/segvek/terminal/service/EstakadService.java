package com.segvek.terminal.service;

import com.segvek.terminal.Loader;
import com.segvek.terminal.dao.DAOException;
import com.segvek.terminal.model.Estakada;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import com.segvek.terminal.dao.EstakadaDAO;


public class EstakadService {
    
    EstakadaDAO estakadDAO;

    public EstakadService() {
        estakadDAO = Loader.getContext().getBean("estakadDAO", EstakadaDAO.class);
    }
    
    
    
    public List<Estakada> getAllEstacad() throws ServiceException{
        List<Estakada> list=null;
        try {
            list = estakadDAO.getAllEstacads();
        } catch (DAOException ex) {
            Logger.getLogger(EstakadService.class.getName()).log(Level.SEVERE, null, ex);
            throw new ServiceException("Не удалось получить список естакад");
        }
        return list;
    }
    
}
