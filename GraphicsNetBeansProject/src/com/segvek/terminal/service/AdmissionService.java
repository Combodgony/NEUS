package com.segvek.terminal.service;

import com.segvek.terminal.dao.AdmissionDao;
import com.segvek.terminal.dao.DAOException;
import com.segvek.terminal.dao.mysql.AdmissionMysqlDao;
import com.segvek.terminal.model.Admission;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;


public class AdmissionService {
    
    AdmissionDao admissionDao = new AdmissionMysqlDao();
    
    public List<Admission> getAllAdmission() throws ServiceException{
        List<Admission> list = null;
        try {
            list = admissionDao.getAllAdmission();
        } catch (DAOException ex) {
            Logger.getLogger(AdmissionService.class.getName()).log(Level.SEVERE, null, ex);
            throw new ServiceException("Не удалось получить список завоза");
        }
        return list;
    }
    
}
