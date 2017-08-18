package com.segvek.terminal.service;

import com.segvek.terminal.Loader;
import com.segvek.terminal.dao.DAOException;
import com.segvek.terminal.dao.DependencyAdmissionDAO;
import com.segvek.terminal.model.Admission;
import com.segvek.terminal.model.DependencyAdmission;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;


public class DependencyService {
    
    private DependencyAdmissionDAO dependencyAdmissionDAO;

    public DependencyService() {
        dependencyAdmissionDAO = Loader.getContext().getBean("dependencyAdmissionDAO", DependencyAdmissionDAO.class);
    }
    
    
    public void save(DependencyAdmission dependencyAdmission) throws ServiceException{
        try {
            dependencyAdmissionDAO.addDependencyAdmission(dependencyAdmission);
            if(dependencyAdmission.getDepend().getIndepented()!=null){
                dependencyAdmission.getDepend().getIndepented().add(dependencyAdmission.getIndependnet());
            }
            if(dependencyAdmission.getIndependnet().getDepend()!=null){
                dependencyAdmission.getIndependnet().getDepend().add(dependencyAdmission.getDepend());
            }
        } catch (DAOException ex) {
            Logger.getLogger(DependencyService.class.getName()).log(Level.SEVERE, null, ex);
            throw new ServiceException("Не удалось сохранить зависимость");
        }
    }
    
    public List<DependencyAdmission> getDependencyAdmissionsByAdmission(Admission admission)throws ServiceException{
        List<DependencyAdmission> list = null;
        try {
            list = dependencyAdmissionDAO.getDependencyAdmissionsByAdmission(admission);
        } catch (DAOException ex) {
            Logger.getLogger(DependencyService.class.getName()).log(Level.SEVERE, null, ex);
            throw new ServiceException();
        }
        return list;
    }

    public List<DependencyAdmission> getAllDependency() throws ServiceException {
        List<DependencyAdmission> list = null;
        try {
            list = dependencyAdmissionDAO.getAllDependencyAdmissions();
        } catch (DAOException ex) {
            Logger.getLogger(DependencyService.class.getName()).log(Level.SEVERE, null, ex);
            throw new ServiceException();
        }
        return list;
    }

    public void deleteDependencyAdmission(DependencyAdmission da) throws ServiceException{
	try {
	    
	    da.getDepend().removeDepend(da.getDepend());
	    da.getDepend().removeIndepented(da.getIndependnet());
	    da.getDepend().removeIndepented(da.getDepend());
	    da.getIndependnet().removeDepend(da.getIndependnet());
	    da.getIndependnet().removeDepend(da.getDepend());
	    da.getIndependnet().removeIndepented(da.getIndependnet());
	    da.getIndependnet().removeIndepented(da.getDepend());
	    dependencyAdmissionDAO.deleteDependencyAdmission(da);
	} catch (DAOException ex) {
	    Logger.getLogger(DependencyService.class.getName()).log(Level.SEVERE, null, ex);
	    throw new ServiceException("Не удалось удалить зависимость");
	}
    }
    
}
