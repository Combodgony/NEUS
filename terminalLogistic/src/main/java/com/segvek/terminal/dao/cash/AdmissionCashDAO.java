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

    Set<DependencyAdmission> setdep;
    boolean alldep = false;

    Set<Admission> set;
    boolean all = false;

    public AdmissionCashDAO(AdmissionDAO admissionDAO) {
	this.admissionDAO = admissionDAO;
	set = new HashSet<>();
	setdep = new HashSet<>();
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
    public List<Admission> getIndependedAdmissionByAdmission(Admission admission) throws DAOException {
	List<Admission> list = admissionDAO.getIndependedAdmissionByAdmission(admission);
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

    @Override
    public void addDependencyAdmission(DependencyAdmission dependencyAdmission) throws DAOException {
	admissionDAO.addDependencyAdmission(dependencyAdmission);
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
	list = admissionDAO.getAllDependencyAdmissions();
	setdep.addAll(list);
	list.clear();
	setdep.forEach(e -> list.add(e));
	return list;
    }

    @Override
    public List<DependencyAdmission> getDependencyAdmissionsByAdmission(Admission admission) throws DAOException {
//        if (!alldep) {
//            getAllDependencyAdmissions();
//        }
//        List<DependencyAdmission> list = new ArrayList<>();
//        Iterator iter = setdep.iterator();
//        while(iter.hasNext()){
//            DependencyAdmission d = (DependencyAdmission) iter.next();
//            if(d.getAdmission().equals(admission) ||d.getIndependet().equals(admission))
//                list.add(d);
//        }
	//todo edit cash code
	return admissionDAO.getDependencyAdmissionsByAdmission(admission);
    }

    @Override
    public void deleteDependencyAdmissionByAdmission(Admission admission) throws DAOException {
	admissionDAO.deleteDependencyAdmissionByAdmission(admission);
	Iterator iter = setdep.iterator();
	while (iter.hasNext()) {
	    DependencyAdmission d = (DependencyAdmission) iter.next();
	    if (d.getAdmission().equals(admission) || d.getIndependet().equals(admission)) {
		setdep.remove(d);
	    }
	}

    }

}
