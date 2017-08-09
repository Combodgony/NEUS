package com.segvek.terminal.dao;

import com.segvek.terminal.model.Client;
import com.segvek.terminal.model.Contract;
import java.util.List;


public interface ContractDAO {
    List<Contract> getContractsByClient(Client c) throws DAOException; 
    List<Contract> getAllContract() throws DAOException;
    void saveContract(Contract contract) throws DAOException;

    
}
