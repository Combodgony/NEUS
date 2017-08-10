package com.segvek.terminal.dao.mysql;

import com.mysql.jdbc.PreparedStatement;
import com.segvek.terminal.dao.DAOException;
import com.segvek.terminal.dao.DrainLocationDAO;
import com.segvek.terminal.db.ConnectionManager;
import com.segvek.terminal.model.Admission;
import com.segvek.terminal.model.DrainLocation;
import com.segvek.terminal.model.Estakada;
import com.segvek.terminal.model.lazy.DrainLocationLazy;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;


public class DrainLocationMysqlDAO implements DrainLocationDAO{

    @Override
    public DrainLocation getDrainLocationByAdmission(Admission admission) throws DAOException {
        DrainLocation location = null;
        Connection connection = null;
        PreparedStatement statment = null;
        ResultSet res = null;
        try {
            connection = ConnectionManager.getInstance().instanceConnection();
            String request = "SELECT l.* FROM admission a INNER JOIN drainlocation l ON l.id=a.idDraionLocation WHERE a.id=?;";
            statment = (PreparedStatement) connection.prepareStatement(request);
            statment.setLong(1, admission.getId());
            res = statment.executeQuery();
            while (res.next()) {
                location = new DrainLocationLazy(res.getLong("id"),res.getString("number"), null);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ClientMysqlDAO.class.getName()).log(Level.SEVERE, null, ex);
            throw new DAOException();
        } finally {
            try {
                if (res != null) {
                    res.close();
                }
            } catch (SQLException ex) {
                Logger.getLogger(ClientMysqlDAO.class.getName()).log(Level.SEVERE, null, ex);
                throw new DAOException();
            } finally {
                try {
                    if (statment != null) {
                        statment.close();
                    }
                } catch (SQLException ex) {
                    Logger.getLogger(ClientMysqlDAO.class.getName()).log(Level.SEVERE, null, ex);
                    throw new DAOException();
                } finally {
                    if (connection != null) {
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
        return location;
    }

    @Override
    public List<DrainLocation> getDrainLocationsByEstacada(Estakada estakada) throws DAOException {
        List<DrainLocation> list=new ArrayList<>();
        Connection connection = null;
        PreparedStatement statment = null;
        ResultSet res=null;
        try {
            connection = ConnectionManager.getInstance().instanceConnection();
            String request="SELECT l.* FROM estakada e INNER JOIN drainlocation l ON l.idEstakada=e.id WHERE e.id=?;";
            statment = (PreparedStatement) connection.prepareStatement(request);
            statment.setLong(1, estakada.getId());
            res = statment.executeQuery();
            while(res.next()){
                DrainLocationLazy c = new DrainLocationLazy(res.getLong("id"), res.getString("number"), estakada);
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
    
}
