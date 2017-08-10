
package com.segvek.terminal.dao.mysql;

import com.mysql.jdbc.PreparedStatement;
import com.segvek.terminal.dao.DAOException;
import com.segvek.terminal.dao.TypeEstakadaDAO;
import com.segvek.terminal.db.ConnectionManager;
import com.segvek.terminal.model.Estakada;
import com.segvek.terminal.model.TypeEstakada;
import com.segvek.terminal.model.TypeTank;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;


public class TypeEstakadaMysqlDAO implements TypeEstakadaDAO{

    @Override
    public TypeEstakada getTypeEstacadaByEstakada(Estakada estakada) throws DAOException {
        TypeEstakada typeEstacada = null;
        Connection connection = null;
        PreparedStatement statment = null;
        ResultSet res=null;
        try {
            connection = ConnectionManager.getInstance().instanceConnection();
            String request="SELECT t.* FROM estakada e INNER JOIN typeestakada t ON t.id=e.idTypeEstakada WHERE e.id=?;";
            statment = (PreparedStatement) connection.prepareStatement(request);
            statment.setLong(1, estakada.getId());
            res = statment.executeQuery();
            if(res.next()){
                typeEstacada = new TypeEstakada(res.getLong("id"), res.getString("name"));
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
        return typeEstacada;
    }

    @Override
    public TypeEstakada getTypeEstacadaByTypeTank(TypeTank typeTank) throws DAOException {
        TypeEstakada typeEstacada = null;
        Connection connection = null;
        PreparedStatement statment = null;
        ResultSet res=null;
        try {
            connection = ConnectionManager.getInstance().instanceConnection();
            String request="SELECT e.* FROM typetank t INNER JOIN typeestakada e ON e.id=t.idTypeEstakada WHERE t.id=?;";
            statment = (PreparedStatement) connection.prepareStatement(request);
            statment.setLong(1, typeTank.getId());
            res = statment.executeQuery();
            if(res.next()){
                typeEstacada = new TypeEstakada(res.getLong("id"), res.getString("name"));
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
        return typeEstacada;
    }
    
}
