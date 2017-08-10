package com.segvek.terminal.model.lazy;

import com.segvek.terminal.dao.ClientDAO;
import com.segvek.terminal.dao.ContentContractDAO;
import com.segvek.terminal.dao.DAOException;
import com.segvek.terminal.dao.mysql.ClientMysqlDAO;
import com.segvek.terminal.dao.mysql.ContentContractMysqlDAO;
import com.segvek.terminal.model.*;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;


public class ContractLazy extends Contract{
    
    private ContentContractDAO contentContractDAO = new ContentContractMysqlDAO();
    private ClientDAO clientDAO = new ClientMysqlDAO();
    
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

    @Override
    public Client getClient() {
        Client client = super.getClient();
        if(client==null){
            try {
                client=clientDAO.getClientByContract(this);
                setClient(client);
            } catch (DAOException ex) {
                Logger.getLogger(ContractLazy.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return client;
    }
}
