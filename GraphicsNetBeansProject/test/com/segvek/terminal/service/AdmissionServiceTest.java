package com.segvek.terminal.service;

import com.segvek.terminal.model.Admission;
import java.util.List;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class AdmissionServiceTest {
    
    AdmissionService service;
    
    @Before
    public void setUp() {
        service=new AdmissionService();
    }

    @Test
    public void testGetAllAdmissionNull() throws Exception {
        assertNotNull(service.getAllAdmission());
    }
    
    @Test
    public void testGetAllAdmissionSize() throws Exception {
        assertNotEquals(service.getAllAdmission().size(),0);
        List<Admission> admission = service.getAllAdmission();
        admission.forEach(a -> {
            System.out.println(a);});
    }
    
}
