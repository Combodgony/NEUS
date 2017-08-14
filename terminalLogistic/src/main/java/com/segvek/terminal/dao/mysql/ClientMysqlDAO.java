package com.segvek.terminal.dao.mysql;

import com.segvek.terminal.model.lazy.ClientLazy;
import com.mysql.jdbc.PreparedStatement;
import com.segvek.terminal.dao.ClientDAO;
import com.segvek.terminal.dao.DAOException;
import com.segvek.terminal.db.ConnectionManager;
import com.segvek.terminal.model.Client;
import com.segvek.terminal.model.Contract;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;


public class ClientMysqlDAO implements ClientDAO{
    
//    @Autowired
//    private JdbcTemplate jdbcTemplate;
    
    @Override
    public List<Client> getAll() throws DAOException {   
        List<Client> list = new ArrayList<Client>();

        Connection connection = null;
        PreparedStatement statment = null;
        ResultSet res=null;
        try {
            connection = ConnectionManager.getInstance().instanceConnection();
            String request="SELECT * FROM client";
            statment = (PreparedStatement) connection.prepareStatement(request);
            res = statment.executeQuery();
            while(res.next()){
                ClientLazy c = new ClientLazy(res.getLong("id"), res.getString("name"), res.getString("adress"));
                list.add(c);
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

    @Override
    public Client getClientByContract(Contract contract) throws DAOException {
        String request="SELECT cl.* FROM client cl INNER JOIN contract con ON con.`idClient`=cl.id WHERE con.id=?;";
        ClientLazy client = null;
        Connection connection = null;
        PreparedStatement statment = null;
        ResultSet res=null;
        try {
            connection = ConnectionManager.getInstance().instanceConnection();         
            statment = (PreparedStatement) connection.prepareStatement(request);
            statment.setLong(1, contract.getId());
            res = statment.executeQuery();
            if(res.next()){
                client = new ClientLazy(res.getLong("id"), res.getString("name"), res.getString("adress"));
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
        return client;
    }
//    
//    private static final class ClientRowMapper implements RowMapper<Client>{
//        @Override
//        public Client mapRow(ResultSet res, int i) throws SQLException {
//            return new ClientLazy(res.getLong("id"), res.getString("name"), res.getString("adress"));
//        }
//        
//    }
    
    
}
