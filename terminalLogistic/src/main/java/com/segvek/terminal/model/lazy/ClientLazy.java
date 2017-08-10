package com.segvek.terminal.model.lazy;

import com.segvek.terminal.dao.ContractDAO;
import com.segvek.terminal.dao.DAOException;
import com.segvek.terminal.dao.mysql.ContractMysqlDAO;
import com.segvek.terminal.model.Client;
import com.segvek.terminal.model.Contract;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;


public class ClientLazy extends Client{
    
    private ContractDAO contractDAO = new ContractMysqlDAO();
    
    public ClientLazy(Long id, String name, String adress) {
        super(id, name, adress);
    }

    @Override
    public List<Contract> getContracts() {
        List<Contract> list = super.getContracts();
        if(list==null){
            try {
                list = contractDAO.getContractsByClient(this);
                setContracts(list);
            } catch (DAOException ex) {
                Logger.getLogger(ClientLazy.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return list;
    }
}
