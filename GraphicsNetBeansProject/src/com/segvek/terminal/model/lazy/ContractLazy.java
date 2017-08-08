package com.segvek.terminal.model.lazy;

import com.segvek.terminal.dao.ContentContractDAO;
import com.segvek.terminal.dao.DAOException;
import com.segvek.terminal.model.*;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;


public class ContractLazy extends Contract{
    
    private ContentContractDAO contentContractDAO;
    
    public ContractLazy(Long id, String number, Date beginDate, Date endDate, Client client) {
        super(id, number, beginDate, endDate, client);
    }

    @Override
    public List<ContentContract> getContent() {
        List<ContentContract> list = super.getContent();
        if(list==null){
            try {
                list=contentContractDAO.getContentByContract(this);
                setContent(list);
            } catch (DAOException ex) {
                Logger.getLogger(ContractLazy.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return list;
    }
    
    

    public void setContentContractDAO(ContentContractDAO contentContractDAO) {
        this.contentContractDAO = contentContractDAO;
    }
    
    
}
