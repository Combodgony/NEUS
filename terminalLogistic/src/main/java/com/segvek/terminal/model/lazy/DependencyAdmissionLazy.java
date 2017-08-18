package com.segvek.terminal.model.lazy;

import com.segvek.terminal.Loader;
import com.segvek.terminal.dao.AdmissionDAO;
import com.segvek.terminal.dao.DAOException;
import com.segvek.terminal.model.Admission;
import com.segvek.terminal.model.DependencyAdmission;
import java.util.logging.Level;
import java.util.logging.Logger;


public class DependencyAdmissionLazy extends DependencyAdmission{
    
    private AdmissionDAO admissionDAO;
	    
    
    
    public DependencyAdmissionLazy(Long id, Admission admission, Admission independet) {
	super(id, admission, independet);
	admissionDAO = Loader.getContext().getBean("admissionDAO", AdmissionDAO.class);
    }

    @Override
    public Admission getDepend() {
	Admission a = super.getDepend();
	if(a==null){
	    try {
		a=admissionDAO.getDependAdmissinByDependencyAdmission(this);
	    } catch (DAOException ex) {
		Logger.getLogger(DependencyAdmissionLazy.class.getName()).log(Level.SEVERE, null, ex);
	    }
	}
	return a;
    }

    @Override
    public Admission getIndependnet() {
	Admission a = super.getDepend();
	if(a==null){
	    try {
		a=admissionDAO.getIndependentAdmissinByDependencyAdmission(this);
	    } catch (DAOException ex) {
		Logger.getLogger(DependencyAdmissionLazy.class.getName()).log(Level.SEVERE, null, ex);
	    }
	}
	return a;
    }
    
    
    
}
