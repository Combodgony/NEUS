package com.segvek.terminal.service;

import com.segvek.terminal.Loader;
import com.segvek.terminal.dao.AdmissionDAO;
import com.segvek.terminal.dao.DAOException;
import com.segvek.terminal.dao.TankDAO;
import com.segvek.terminal.model.DependencyAdmission;
import com.segvek.terminal.model.Tank;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;


public class DependencyService {
    private TankDAO tankDAO;
    private AdmissionDAO admissionDAO;

    public DependencyService() {
        tankDAO = Loader.getContext().getBean("tankDAO", TankDAO.class);
        admissionDAO = Loader.getContext().getBean("admissionDAO", AdmissionDAO.class);
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
            admissionDAO.addDependencyAdmission(dependencyAdmission);
        } catch (DAOException ex) {
            Logger.getLogger(DependencyService.class.getName()).log(Level.SEVERE, null, ex);
            throw new ServiceException("Не удалось сохранить зависимость");
        }
    }

    public List<DependencyAdmission> getAllDependency() throws ServiceException {
        List<DependencyAdmission> list = null;
        try {
            list = admissionDAO.getAllDependencyAdmissions();
        } catch (DAOException ex) {
            Logger.getLogger(DependencyService.class.getName()).log(Level.SEVERE, null, ex);
            throw new ServiceException();
        }
        return list;
    }
    
}
