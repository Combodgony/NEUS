
package com.segvek.terminal.dao.mysql;

import com.mysql.jdbc.PreparedStatement;
import com.segvek.terminal.dao.DAOException;
import com.segvek.terminal.dao.TankDAO;
import com.segvek.terminal.db.ConnectionManager;
import com.segvek.terminal.model.Admission;
import com.segvek.terminal.model.Tank;
import com.segvek.terminal.model.lazy.TankLazy;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;


public class TankMysqlDAO implements TankDAO{

    @Override
    public Tank getTankByAdmission(Admission admission) throws DAOException {
        Tank tank = null;
        Connection connection = null;
        PreparedStatement statment = null;
        ResultSet res = null;
        try {
            connection = ConnectionManager.getInstance().instanceConnection();
            String request = "SELECT t.* FROM admission a INNER JOIN tank t ON t.id=a.`idTank` WHERE a.id=?;";
            statment = (PreparedStatement) connection.prepareStatement(request);
            statment.setLong(1, admission.getId());
            res = statment.executeQuery();
            while (res.next()) {
                tank = new TankLazy(res.getLong("id"),res.getString("number"), null);
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
        return tank;
    }
    
}
