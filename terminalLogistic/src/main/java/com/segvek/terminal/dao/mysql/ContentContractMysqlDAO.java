package com.segvek.terminal.dao.mysql;

import com.mysql.jdbc.PreparedStatement;
import com.segvek.terminal.dao.ContentContractDAO;
import com.segvek.terminal.dao.DAOException;
import com.segvek.terminal.db.ConnectionManager;
import com.segvek.terminal.model.Cargo;
import com.segvek.terminal.model.ContentContract;
import com.segvek.terminal.model.Contract;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ContentContractMysqlDAO implements ContentContractDAO {

    @Override
    public List<ContentContract> getContentByContract(Contract c) throws DAOException {
        List<ContentContract> list = new ArrayList<>();

        Connection connection = null;
        PreparedStatement statment = null;
        ResultSet res=null;
        try {
            connection = ConnectionManager.getInstance().instanceConnection();
            String request="SELECT con.volume, c.id, c.name FROM contentcontract con INNER JOIN cargo c ON con.idCargo=c.id WHERE idContract=?";
            statment = (PreparedStatement) connection.prepareStatement(request);
            statment.setLong(1, c.getId());
            res = statment.executeQuery();
            while(res.next()){
                ContentContract cc = new ContentContract(new Cargo(res.getLong("id"), res.getString("name")), c, res.getInt("volume"));
                list.add(cc);
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
    public void saveContentContract(ContentContract cc) throws DAOException {
        Connection connection = null;
        PreparedStatement statment = null;
        try {
            connection = ConnectionManager.getInstance().instanceConnection();
            String request="INSERT INTO contentcontract (idContract,idCargo,volume) VALUES (?,?,?);";
            statment = (PreparedStatement) connection.prepareStatement(request);
            statment.setLong(1, cc.getContract().getId());
            statment.setLong(2, cc.getCargo().getId());
            statment.setInt(3, cc.getVolume());
            statment.executeUpdate();
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
                if(connection!=null){
                    try {
                        connection.close();
                    } catch (SQLException ex) {
                        Logger.getLogger(ClientMysqlDAO.class.getName()).log(Level.SEVERE, null, ex);
                        throw new DAOException();
                    }
                }
            }
        }
        
    }
    
}
