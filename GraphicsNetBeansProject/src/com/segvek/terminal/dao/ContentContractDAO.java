package com.segvek.terminal.dao;

import com.segvek.terminal.model.ContentContract;
import com.segvek.terminal.model.Contract;
import java.util.List;


public interface ContentContractDAO {
    
    List<ContentContract> getContentByContract(Contract c) throws DAOException;

    void saveContentContract(ContentContract cc) throws DAOException;
}
