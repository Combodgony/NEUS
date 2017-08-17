package com.segvek.terminal.service;

import com.segvek.terminal.Loader;
import com.segvek.terminal.dao.AdmissionDAO;
import com.segvek.terminal.dao.DAOException;
import com.segvek.terminal.model.Admission;
import com.segvek.terminal.model.DependencyAdmission;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;


public class DependencyService {
    
    private AdmissionDAO admissionDAO;

    public DependencyService() {
        admissionDAO = Loader.getContext().getBean("admissionDAO", AdmissionDAO.class);
    }
    
    
    public void save(DependencyAdmission dependencyAdmission) throws ServiceException{
        try {
            admissionDAO.addDependencyAdmission(dependencyAdmission);
            if(dependencyAdmission.getAdmission().getIndepented()!=null){
                dependencyAdmission.getAdmission().getIndepented().add(dependencyAdmission.getIndependet());
            }
            if(dependencyAdmission.getIndependet().getDepend()!=null){
                dependencyAdmission.getIndependet().getDepend().add(dependencyAdmission.getAdmission());
            }
        } catch (DAOException ex) {
            Logger.getLogger(DependencyService.class.getName()).log(Level.SEVERE, null, ex);
            throw new ServiceException("Не удалось сохранить зависимость");
        }
    }
    
    public List<DependencyAdmission> getDependencyAdmissionsByAdmission(Admission admission)throws ServiceException{
        List<DependencyAdmission> list = null;
        try {
            list = admissionDAO.getDependencyAdmissionsByAdmission(admission);
        } catch (DAOException ex) {
            Logger.getLogger(DependencyService.class.getName()).log(Level.SEVERE, null, ex);
            throw new ServiceException();
        }
        return list;
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
