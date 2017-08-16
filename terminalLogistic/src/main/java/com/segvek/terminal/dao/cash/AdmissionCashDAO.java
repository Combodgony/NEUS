package com.segvek.terminal.dao.cash;

import com.segvek.terminal.dao.AdmissionDAO;
import com.segvek.terminal.dao.DAOException;
import com.segvek.terminal.model.Admission;
import com.segvek.terminal.model.DependencyAdmission;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;
import java.util.Set;


public class AdmissionCashDAO implements AdmissionDAO{
    AdmissionDAO admissionDAO;

    Set<Admission> set;
    boolean all=false;
    
    public AdmissionCashDAO(AdmissionDAO admissionDAO) {
        this.admissionDAO = admissionDAO;
        set=new HashSet<>();
    }
    
    
    @Override
    public List<Admission> getAllAdmission() throws DAOException {
        List<Admission> list = new ArrayList<>();
        if(all){
            set.forEach(a->list.add(a));
            return list;
        }
        List<Admission> dbList = admissionDAO.getAllAdmission();
        set.addAll(dbList);
        list.clear();
        set.forEach(a->list.add(a));
        all=true;
        return list;
    }

    @Override
    public void update(Admission a) throws DAOException {
        admissionDAO.update(a);
    }

    @Override
    public Admission getAdmissionById(Long id) throws DAOException {
        Admission a = getFromCash(id);
        if(a!=null)
            return a;
        a=admissionDAO.getAdmissionById(id);
        set.add(a);
        return a;
    }

    @Override
    public List<Admission> getIndependedAdmissionByAdmission(Admission admission) throws DAOException {
        List<Admission> list=admissionDAO.getIndependedAdmissionByAdmission(admission);
        List<Admission> res = new ArrayList<>();
        for(Admission a:list){
            res.add(getAdmissionById(a.getId()));
        }
        return res;
    }

    @Override
    public List<Admission> getDependAdmissionByAdmission(Admission admissiona) throws DAOException {
        List<Admission> list=admissionDAO.getDependAdmissionByAdmission(admissiona);
        List<Admission> res = new ArrayList<>();
        for(Admission a:list){
            res.add(getAdmissionById(a.getId()));
        }
        return res;
    }
    
    private Admission getFromCash(Long id){
        Iterator<Admission> iter = set.iterator();
        while(iter.hasNext()){
            Admission a = iter.next();
            if(a.getId().equals(id))
                return a;
        }
        return null;
    }

    @Override
    public void addDependencyAdmission(DependencyAdmission dependencyAdmission) throws DAOException {
        admissionDAO.addDependencyAdmission(dependencyAdmission);
        
    }

    @Override
    public List<DependencyAdmission> getAllDependencyAdmissions() throws DAOException {
        List<DependencyAdmission> da =  admissionDAO.getAllDependencyAdmissions();
        List<DependencyAdmission> res = new ArrayList<>();
        for(DependencyAdmission d:da){
            res.add(new DependencyAdmission(getAdmissionById(d.getAdmission().getId()), getAdmissionById(d.getIndependet().getId())));
        }
        return res;
    }
}
