package com.segvek.terminal.service;

import com.segvek.terminal.Loader;
import com.segvek.terminal.dao.DAOException;
import com.segvek.terminal.model.Admission;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import com.segvek.terminal.dao.AdmissionDAO;
import com.segvek.terminal.model.Cargo;
import com.segvek.terminal.model.Contract;

public class AdmissionService {

    AdmissionDAO admissionDao;

    public AdmissionService() {
        admissionDao = Loader.getContext().getBean("admissionDAO", AdmissionDAO.class);
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

    public void saveAdmission(Admission admission) throws ServiceException{
        if(admission.isNewInstance()){
            try {
                //todo добавить проверки заполнения критически важных полей
                admissionDao.addAdmission(admission);
            } catch (DAOException ex) {
                Logger.getLogger(AdmissionService.class.getName()).log(Level.SEVERE, null, ex);
                throw  new ServiceException("Не удалось сохранить новий завоз!");
            }
        }else{
            try {
                admissionDao.update(admission);
		if(!admission.isPlan()){
		    admissionDao.deleteDependencyAdmissionByAdmission(admission);
		}
            } catch (DAOException ex) {
                Logger.getLogger(AdmissionService.class.getName()).log(Level.SEVERE, null, ex);
                throw  new ServiceException("Не удалось сохранить изменения завоза!");
            }
        }
    }

    public List<Admission> getAdmissionsByConractAndCargo(Contract contract,Cargo cargo) throws ServiceException{
	List<Admission>  list=null;
	try {
	    list = admissionDao.getAdmissionsByContractAndCargo(contract, cargo);
	} catch (DAOException ex) {
	    Logger.getLogger(AdmissionService.class.getName()).log(Level.SEVERE, null, ex);
	    throw  new ServiceException();
	}
	return list;
    }
}
