
package com.segvek.terminal.dao;

import com.segvek.terminal.model.Client;
import com.segvek.terminal.model.Contract;
import java.util.List;


public interface ClientDAO  extends DAO{
    List<Client> getAll() throws DAOException;
    Client getClientByContract(Contract contract) throws DAOException;
}
