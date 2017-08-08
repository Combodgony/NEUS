
package com.segvek.terminal.dao;

import com.segvek.terminal.model.Client;
import java.util.List;


public interface ClientDAO {
    List<Client> getAll() throws DAOException;
}
