package com.segvek.terminal.dao.cash;

import com.segvek.terminal.dao.AdmissionDAO;
import com.segvek.terminal.dao.DAOException;
import com.segvek.terminal.dao.DependencyAdmissionDAO;
import com.segvek.terminal.model.Admission;
import com.segvek.terminal.model.DependencyAdmission;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;


public class DependencyAdmissionCashDAO implements DependencyAdmissionDAO{
    private DependencyAdmissionDAO dependencyAdmissionDAO;
    private AdmissionDAO admissionDAO;
    
    Set<DependencyAdmission> setdep;
    boolean alldep = false;
    
    public DependencyAdmissionCashDAO(DependencyAdmissionDAO dependencyAdmissionDAO,AdmissionDAO admissionDAO) {
	this.dependencyAdmissionDAO = dependencyAdmissionDAO;
	this.admissionDAO=admissionDAO;
	setdep = new HashSet<>();
    }

    
    
    @Override
    public void addDependencyAdmission(DependencyAdmission dependencyAdmission) throws DAOException {
	dependencyAdmissionDAO.addDependencyAdmission(dependencyAdmission);
	setdep.add(dependencyAdmission);
    }

    @Override
    public List<DependencyAdmission> getAllDependencyAdmissions() throws DAOException {
	List<DependencyAdmission> list;
	if (alldep) {
	    list = new ArrayList<>();
	    setdep.forEach(d -> list.add(d));
	    return list;
	}
	list = dependencyAdmissionDAO.getAllDependencyAdmissions();
	setdep.addAll(list);
	list.clear();
	setdep.forEach(e -> list.add(e));
	alldep = true;
	return list;
    }

    @Override
    public List<DependencyAdmission> getDependencyAdmissionsByAdmission(Admission admission) throws DAOException {
	//todo edit cash code
	return dependencyAdmissionDAO.getDependencyAdmissionsByAdmission(admission);
    }

    @Override
    public void deleteDependencyAdmissionByAdmission(Admission admission) throws DAOException {
	dependencyAdmissionDAO.deleteDependencyAdmissionByAdmission(admission);
	Iterator iter = setdep.iterator();
	while (iter.hasNext()) {
	    DependencyAdmission d = (DependencyAdmission) iter.next();
	    if (d.getDepend().equals(admission) || d.getIndependnet().equals(admission)) {
		setdep.remove(d);
	    }
	}

    }

    @Override
    public void deleteDependencyAdmission(DependencyAdmission da) throws DAOException {
	dependencyAdmissionDAO.deleteDependencyAdmission(da);
	Iterator iter = setdep.iterator();
	while (iter.hasNext()) {
	    DependencyAdmission chashdep = (DependencyAdmission) iter.next();
	    if (da.getId().compareTo(chashdep.getId()) == 0) {
		setdep.remove(chashdep);
		break;
	    }
	}
    }

    
    
    
}
