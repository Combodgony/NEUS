package com.segvek.terminal.model;

import com.segvek.terminal.service.AdmissionService;
import com.segvek.terminal.service.ServiceException;
import java.util.List;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class AdmissionServiceTest {
    
    AdmissionService service;
    
  
    @Before
    public void setUp() {
        service = new AdmissionService();
    }
    
    
    @Test
    public void testGetAllNotNull() throws ServiceException {
        List<Admission> list = service.getAllAdmission();
        assertNotNull(list);
    }
    
    @Test
    public void testGetAllSize() throws ServiceException {
        List<Admission> list = service.getAllAdmission();
        assertNotEquals(list.size(), 0);
    }
    
    @Test
    public void testLazyLoadContract() throws ServiceException {
        List<Admission> list = service.getAllAdmission();
        assertNotEquals(list.size(), 0);
        Admission a = list.get(1);
        assertNotNull(a);
        assertNotNull(a.getContract());
    }
    
    @Test
    public void testLazyLoadTank() throws ServiceException {
        List<Admission> list = service.getAllAdmission();
        assertNotEquals(list.size(), 0);
        Admission a = list.get(1);
        assertNotNull(a);
        assertNotNull(a.getTank());
    }
    
    @Test
    public void testLazyLoadDrainLocation() throws ServiceException {
        List<Admission> list = service.getAllAdmission();
        assertNotEquals(list.size(), 0);
        Admission a = list.get(1);
        assertNotNull(a);
        assertNotNull(a.getDrainLocation());
    }
    
    @Test
    public void testAdmissionGetBeginNull() throws ServiceException {
        List<Admission> list = service.getAllAdmission();
        assertNotEquals(list.size(), 0);
        Admission a = list.get(1);
        assertNotNull(a);
        assertNotNull(a.getBegin());
    }
    
//    @Test
//    public void testAdmissionGetBeginNull() throws ServiceException {
//        List<Admission> list = service.getAllAdmission();
//        assertNotEquals(list.size(), 0);
//        Admission a = list.get(1);
//        assertNotNull(a);
//        assertNotNull(a.getDrainLocation());
//    }
    
    

    
}
