package com.segvek.terminal.dao.cash;

import com.segvek.terminal.dao.DAOException;
import com.segvek.terminal.dao.TankDAO;
import com.segvek.terminal.model.Admission;
import com.segvek.terminal.model.Tank;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class TankCashDAO implements TankDAO{
    
    private TankDAO tankDAO;

    private List<Tank> list;
    private Map<Long,Tank> admissinMap;
    
    public TankCashDAO(TankDAO tankDAO) {
        this.tankDAO = tankDAO;
        list=new ArrayList<>();
        admissinMap=new HashMap<>();
    }
    
    @Override
    public List<Tank> getAllTank() throws DAOException {
        return tankDAO.getAllTank();
    }
    
    @Override
    public Tank getTankByAdmission(Admission admission) throws DAOException {
        Tank t = admissinMap.get(admission.getId());
        if(t!=null)
            return t;
        t=tankDAO.getTankByAdmission(admission);
        Tank cashTank = getFromCash(t.getId());
        if(cashTank==null){
            list.add(t);
        }else{
            t=cashTank;
        }
        admissinMap.put(admission.getId(), t);
        
        if(t==null)
            throw new DAOException();
        return t;
    }
    
    
    
    private Tank getFromCash(Long id){
        for(Tank t:list){
            if(t.getId().equals(id))
                return t;
        }
        return null;
    }
}
