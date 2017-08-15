
package com.segvek.terminal.dao;

import com.segvek.terminal.model.DependencyAdmission;


public interface DependencyAdmissionDAO extends DAO{

    public void addDependencyAdmission(DependencyAdmission dependencyAdmission) throws DAOException;
    
}
