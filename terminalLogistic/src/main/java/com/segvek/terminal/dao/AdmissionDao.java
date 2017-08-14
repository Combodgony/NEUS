package com.segvek.terminal.dao;

import com.segvek.terminal.model.Admission;
import java.util.List;

public interface AdmissionDao extends DAO{

    public List<Admission> getAllAdmission() throws DAOException;

    public void update(Admission a) throws DAOException;

}
