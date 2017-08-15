package com.segvek.terminal.dao.mysql;

import com.segvek.terminal.dao.DAOException;
import com.segvek.terminal.dao.DependencyAdmissionDAO;
import com.segvek.terminal.model.DependencyAdmission;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;


public class DependencyAdmissionMysqlDAO implements DependencyAdmissionDAO{
    
    @Autowired
    private JdbcTemplate jdbcTemplate;
    
    @Override
    public void addDependencyAdmission(DependencyAdmission dependencyAdmission) throws DAOException {
        String request = "INSERT INTO dependencyadmission (idDependent, idIndependent) VALUES(?,?)";
        jdbcTemplate.update(request, dependencyAdmission.getAdmission().getId(),dependencyAdmission.getIndependet().getId());
    }
    
}
