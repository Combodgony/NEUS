package com.segvek.terminal.model;

import com.segvek.terminal.model.lazy.AdmissionLazy;
import java.util.ArrayList;
import java.util.List;
import org.junit.After;
import org.junit.Test;
import static org.junit.Assert.*;

public class AdmissionTest {
    
    
//    @After
//    public void up(){
//	
//    }
    
    @Test
    public void removeDependet(){
	Admission a = new Admission(0L, null, null, 0, null, null, null, null, null, null, true);
	List<Admission> dependet = new ArrayList<>();
	Admission ad = new Admission(1L, null, null, 0, null, null, null, null, null, null, true);
	Admission ad2 = new AdmissionLazy(1L, null, null, 0, null, null, null, null, null, null, true);
	dependet.add(ad);
	a.setDepend(dependet);
	
//	a.removeDepend(ad2);
	boolean flug=false;
	for(Admission admis:a.getDepend())
	    flug|=admis.equals(ad);
	assertTrue(flug);
    }
}
