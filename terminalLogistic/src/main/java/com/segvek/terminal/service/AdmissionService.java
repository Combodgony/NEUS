package com.segvek.terminal.service;

import com.segvek.terminal.Loader;
import com.segvek.terminal.dao.AdmissionDao;
import com.segvek.terminal.dao.DAOException;
import com.segvek.terminal.model.Admission;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class AdmissionService {

    AdmissionDao admissionDao;

    public AdmissionService() {
        admissionDao = Loader.getContext().getBean("admissionDao", AdmissionDao.class);
    }

    public List<Admission> getAllAdmission() throws ServiceException {
        List<Admission> list = null;
        try {
            list = admissionDao.getAllAdmission();
        } catch (DAOException ex) {
            Logger.getLogger(AdmissionService.class.getName()).log(Level.SEVERE, null, ex);
            throw new ServiceException("Не удалось получить список завоза");
        }
        return list;
    }

    public void saveAllAdmissions(List<Admission> list) throws ServiceException {
        //todo: может созникнуть опасномть опасность многопоточного доступа
        //необходимо для того что бі не тормозил интерФЕЙС
        new Thread() {
            @Override
            public void run() {
                try {
                    for (Admission a : list) {
                        admissionDao.update(a);
                    }
                } catch (DAOException ex) {
                    Logger.getLogger(AdmissionService.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }.start();

    }

}
