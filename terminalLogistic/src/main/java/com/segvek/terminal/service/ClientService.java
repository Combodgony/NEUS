package com.segvek.terminal.service;

import com.segvek.terminal.Loader;
import com.segvek.terminal.dao.ClientDAO;
import com.segvek.terminal.dao.DAOException;
import com.segvek.terminal.model.Client;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;


public class ClientService {
    private ClientDAO clientDao;

    public ClientService() {
        clientDao=Loader.getContext().getBean("clientDAO",ClientDAO.class);
    }
    
    public List<Client> getAllClients() throws ServiceException{
        List<Client> res=null;
        try {
            res = clientDao.getAll();
        } catch (DAOException ex) {
            Logger.getLogger(ClientService.class.getName()).log(Level.SEVERE, null, ex);
            throw new ServiceException();
        }
        return res;
    }
}
