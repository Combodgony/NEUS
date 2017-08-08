/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.segvek.terminal.dao;

import com.segvek.terminal.model.Client;
import com.segvek.terminal.model.Contract;
import java.util.List;

/**
 *
 * @author valeriy.lipin
 */
public interface ContractDAO {
    List<Contract> getContractsByClient(Client c) throws DAOException;  
}
