package com.segvek.terminal.dao;

import com.segvek.terminal.model.Admission;
import com.segvek.terminal.model.DependencyAdmission;
import java.util.List;


public interface DependencyAdmissionDAO extends DAO{
    
    public void addDependencyAdmission(DependencyAdmission dependencyAdmission) throws DAOException;
    
    public List<DependencyAdmission> getAllDependencyAdmissions() throws DAOException;
    
    public List<DependencyAdmission> getDependencyAdmissionsByAdmission(Admission admission) throws DAOException;
    
    public void deleteDependencyAdmissionByAdmission(Admission admission) throws DAOException;

    public void deleteDependencyAdmission(DependencyAdmission da) throws DAOException;
}
