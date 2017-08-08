
package com.segvek.terminal.dao.mysql;

import com.mysql.jdbc.PreparedStatement;
import com.segvek.terminal.dao.ContractDAO;
import com.segvek.terminal.dao.DAOException;
import com.segvek.terminal.db.ConnectionManager;
import com.segvek.terminal.model.Client;
import com.segvek.terminal.model.Contract;
import com.segvek.terminal.model.lazy.ContractLazy;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ContractMysqlDAO implements ContractDAO{

    @Override
    public List<Contract> getContractsByClient(Client c) throws DAOException {
        List<Contract> list = new ArrayList<Contract>();

        Connection connection = null;
        PreparedStatement statment = null;
        ResultSet res=null;
        try {
            connection = ConnectionManager.getInstance().instanceConnection();
            String request="SELECT * FROM contract WHERE idClient=?";
            statment = (PreparedStatement) connection.prepareStatement(request);
            statment.setLong(1, c.getId());
            res = statment.executeQuery();
            while(res.next()){
                ContractLazy conrtact = new ContractLazy(res.getLong("id")
                        , res.getString("number")
                        , res.getDate("beginDate")
                        , res.getDate("endDate"), c);
                conrtact.setContentContractDAO(new ContentContractMysqlDAO());
                list.add(conrtact);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ClientMysqlDAO.class.getName()).log(Level.SEVERE, null, ex);
            throw new DAOException();
        }finally{
            try {
                if(res!=null)
                    res.close();
            } catch (SQLException ex) {
                Logger.getLogger(ClientMysqlDAO.class.getName()).log(Level.SEVERE, null, ex);
                throw new DAOException();
            }finally{
                try {
                    if(statment!=null)
                        statment.close();
                } catch (SQLException ex) {
                    Logger.getLogger(ClientMysqlDAO.class.getName()).log(Level.SEVERE, null, ex);
                    throw new DAOException();
                }finally{
                    if(connection!=null)
                        try {
                            connection.close();
                        } catch (SQLException ex) {
                            Logger.getLogger(ClientMysqlDAO.class.getName()).log(Level.SEVERE, null, ex);
                            throw new DAOException();
                        }
                }
            }
        }
        return list;
    }
    
}
