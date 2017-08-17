package com.segvek.terminal.service;

import com.segvek.terminal.Loader;
import com.segvek.terminal.dao.DAOException;
import com.segvek.terminal.dao.DrainLocationDAO;
import com.segvek.terminal.model.DrainLocation;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;


public class DrainLocationService {
    private DrainLocationDAO drainLocationDAO;

    public DrainLocationService() {
        drainLocationDAO = Loader.getContext().getBean("drainLocationDAO", DrainLocationDAO.class);
    }
    
    public List<DrainLocation> getAllDrainLocation() throws ServiceException{
        List<DrainLocation> res=null;
        try {
            res = drainLocationDAO.getAllDrainLocation();
        } catch (DAOException ex) {
            Logger.getLogger(DrainLocationService.class.getName()).log(Level.SEVERE, null, ex);
            throw new ServiceException();
        }
        return res;
    }
}
