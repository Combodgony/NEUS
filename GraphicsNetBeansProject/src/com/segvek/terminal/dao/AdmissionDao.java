package com.segvek.terminal.dao;

import com.segvek.terminal.model.Admission;
import java.util.List;

public interface AdmissionDao {

    public List<Admission> getAllAdmission() throws DAOException;
    
}
