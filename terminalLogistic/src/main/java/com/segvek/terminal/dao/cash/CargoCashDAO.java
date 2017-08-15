
package com.segvek.terminal.dao.cash;

import com.segvek.terminal.dao.DAOException;
import com.segvek.terminal.model.Admission;
import com.segvek.terminal.model.Cargo;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import com.segvek.terminal.dao.CargoDAO;


public class CargoCashDAO implements CargoDAO{
    private CargoDAO cargoDAO;

    private List<Cargo> list;
    
    private Map<Long,Cargo> admissionMap;
    
    private boolean all=false;
    
    
    
    public CargoCashDAO(CargoDAO cargoDAO) throws DAOException {
        this.cargoDAO = cargoDAO;
        list=new ArrayList<>();
        admissionMap=new HashMap<>();
        
        getAllCargo();
    }

    @Override
    public List<Cargo> getAllCargo() throws DAOException {
        if(!all){
            list = cargoDAO.getAllCargo();
            all=true;
        }
        return list;
        
    }

    @Override
    public Cargo getCargoByAdmission(Admission admission) throws DAOException {
        Cargo c = admissionMap.get(admission.getId());
        if(c==null){
            c=cargoDAO.getCargoByAdmission(admission);
            Cargo cashCargo = getFromCash(c.getId());
            if(cashCargo!=null)
                admissionMap.put(admission.getId(), cashCargo);
            else
                admissionMap.put(admission.getId(), c);
        }
        return c;
    }
    
    
    private Cargo getFromCash(Long id){
        for(Cargo c:list){
            if(c.getId()==id)
                return c;
        }
        return null;
    }
}
