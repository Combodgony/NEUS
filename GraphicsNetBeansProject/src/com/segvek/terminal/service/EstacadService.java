package com.segvek.terminal.service;

import com.segvek.terminal.dao.DAOException;
import com.segvek.terminal.dao.mysql.EstakadMysqlDAO;
import com.segvek.terminal.model.Estakada;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import com.segvek.terminal.dao.EstakadaDAO;


public class EstacadService {
    
    EstakadaDAO estacadDAO = new EstakadMysqlDAO();
    
    public List<Estakada> getAllEstacad() throws ServiceException{
        List<Estakada> list=null;
        try {
            list = estacadDAO.getAllEstacads();
        } catch (DAOException ex) {
            Logger.getLogger(EstacadService.class.getName()).log(Level.SEVERE, null, ex);
            throw new ServiceException("Не удалось получить список естакад");
        }
        return list;
    }
    
}
