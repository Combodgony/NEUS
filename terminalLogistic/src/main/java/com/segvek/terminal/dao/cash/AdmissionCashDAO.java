package com.segvek.terminal.dao.cash;

import com.segvek.terminal.dao.AdmissionDAO;
import com.segvek.terminal.dao.DAOException;
import com.segvek.terminal.model.Admission;
import com.segvek.terminal.model.Cargo;
import com.segvek.terminal.model.Contract;
import com.segvek.terminal.model.DependencyAdmission;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

public class AdmissionCashDAO implements AdmissionDAO {

    AdmissionDAO admissionDAO;
    Set<Admission> set;
    boolean all = false;

   

    public AdmissionCashDAO(AdmissionDAO admissionDAO) {
	this.admissionDAO = admissionDAO;
	set = new HashSet<>();
    }

    @Override
    public List<Admission> getAllAdmission() throws DAOException {
	List<Admission> list = new ArrayList<>();
	if (all) {
	    set.forEach(a -> list.add(a));
	    return list;
	}
	List<Admission> dbList = admissionDAO.getAllAdmission();
	set.addAll(dbList);
	list.clear();
	set.forEach(a -> list.add(a));
	all = true;
	return list;
    }

    @Override
    public void update(Admission a) throws DAOException {
	admissionDAO.update(a);
    }

    @Override
    public Admission getAdmissionById(Long id) throws DAOException {
	Admission a = getFromCash(id);
	if (a != null) {
	    return a;
	}
	a = admissionDAO.getAdmissionById(id);
	set.add(a);
	return a;
    }

    @Override
    public void addAdmission(Admission admission) throws DAOException {
	admissionDAO.addAdmission(admission);
	set.add(admission);
    }

    @Override
    public List<Admission> getAdmissionsByContractAndCargo(Contract contract, Cargo cargo) throws DAOException {
	List<Admission> list = admissionDAO.getAdmissionsByContractAndCargo(contract, cargo);
	List<Admission> res = new ArrayList<>();
	for (Admission a : list) {
	    Admission admissionCash = getFromCash(a.getId());
	    if (admissionCash == null) {
		res.add(a);
	    } else {
		res.add(admissionCash);
	    }
	}
	return res;
    }

    @Override
    public Admission getDependAdmissinByDependencyAdmission(DependencyAdmission dependencyAdmission) throws DAOException {
	Admission adb = admissionDAO.getDependAdmissinByDependencyAdmission(dependencyAdmission);
	Admission a = getFromCash(adb.getId());
	if (a != null) {
	    return a;
	}
	a = admissionDAO.getAdmissionById(adb.getId());
	set.add(a);
	return a;
    }

    @Override
    public Admission getIndependentAdmissinByDependencyAdmission(DependencyAdmission dependencyAdmission) throws DAOException {
	Admission adb = admissionDAO.getIndependentAdmissinByDependencyAdmission(dependencyAdmission);
	Admission a = getFromCash(adb.getId());
	if (a != null) {
	    return a;
	}
	a = admissionDAO.getAdmissionById(adb.getId());
	set.add(a);
	return a;
    }

    @Override
    public List<Admission> getIndependentAdmissionByAdmission(Admission admission) throws DAOException {
	List<Admission> list = admissionDAO.getIndependentAdmissionByAdmission(admission);
	List<Admission> res = new ArrayList<>();
	for (Admission a : list) {
	    res.add(getAdmissionById(a.getId()));
	}
	return res;
    }

    @Override
    public List<Admission> getDependAdmissionByAdmission(Admission admissiona) throws DAOException {
	List<Admission> list = admissionDAO.getDependAdmissionByAdmission(admissiona);
	List<Admission> res = new ArrayList<>();
	for (Admission a : list) {
	    res.add(getAdmissionById(a.getId()));
	}
	return res;
    }
    
    private Admission getFromCash(Long id) {
	Iterator<Admission> iter = set.iterator();
	while (iter.hasNext()) {
	    Admission a = iter.next();
	    if (a.getId().equals(id)) {
		return a;
	    }
	}
	return null;
    }

    
    
    

}
