package com.segvek.terminal.service;

import com.segvek.terminal.Loader;
import com.segvek.terminal.dao.DAOException;
import com.segvek.terminal.dao.DependencyAdmissionDAO;
import com.segvek.terminal.dao.TankDAO;
import com.segvek.terminal.model.DependencyAdmission;
import com.segvek.terminal.model.Tank;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;


public class DependencyService {
    private TankDAO tankDAO;
    private DependencyAdmissionDAO dependencyAdmissionDAO;

    public DependencyService() {
        tankDAO = Loader.getContext().getBean("tankDAO", TankDAO.class);
        dependencyAdmissionDAO = Loader.getContext().getBean("dependencyAdmissionDAO", DependencyAdmissionDAO.class);
    }
    
    
    public List<Tank> getAllTank() throws ServiceException{
        List<Tank> list=null;
        try {
            list = tankDAO.getAllTank();
        } catch (DAOException ex) {
            Logger.getLogger(DependencyService.class.getName()).log(Level.SEVERE, null, ex);
            throw new ServiceException("Не удалось получить списо цистерн");
        }
        return list;
    }

    public void save(DependencyAdmission dependencyAdmission) throws ServiceException{
        try {
            dependencyAdmissionDAO.addDependencyAdmission(dependencyAdmission);
        } catch (DAOException ex) {
            Logger.getLogger(DependencyService.class.getName()).log(Level.SEVERE, null, ex);
            throw new ServiceException("Не удалось сохранить зависимость");
        }
    }
    
}
