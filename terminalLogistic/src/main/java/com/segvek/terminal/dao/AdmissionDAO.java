package com.segvek.terminal.dao;

import com.segvek.terminal.model.Admission;
import java.util.List;

public interface AdmissionDAO extends DAO{

    public List<Admission> getAllAdmission() throws DAOException;

    public void update(Admission a) throws DAOException;

    public Admission getAdmissionById(Long id) throws DAOException;
    
    public List<Admission> getIndependedAdmissionByAdmission(Admission admission) throws DAOException;

    public List<Admission> getDependAdmissionByAdmission(Admission admissiona) throws DAOException;

}
