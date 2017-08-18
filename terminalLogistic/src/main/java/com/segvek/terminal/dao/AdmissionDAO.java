package com.segvek.terminal.dao;

import com.segvek.terminal.model.Admission;
import com.segvek.terminal.model.Cargo;
import com.segvek.terminal.model.Contract;
import com.segvek.terminal.model.DependencyAdmission;
import java.util.List;

public interface AdmissionDAO extends DAO{

    public List<Admission> getAllAdmission() throws DAOException;

    public void update(Admission a) throws DAOException;

    public Admission getAdmissionById(Long id) throws DAOException;
    
    public List<Admission> getIndependedAdmissionByAdmission(Admission admission) throws DAOException;

    public List<Admission> getDependAdmissionByAdmission(Admission admissiona) throws DAOException;
    
    public void addDependencyAdmission(DependencyAdmission dependencyAdmission) throws DAOException;

    public List<DependencyAdmission> getAllDependencyAdmissions() throws DAOException; 
    
    public List<DependencyAdmission> getDependencyAdmissionsByAdmission(Admission admission) throws DAOException;

    public void addAdmission(Admission admission) throws DAOException;
    
    public List<Admission> getAdmissionsByContractAndCargo(Contract contract,Cargo cargo) throws DAOException;

    public void deleteDependencyAdmissionByAdmission(Admission admission) throws DAOException;

}
