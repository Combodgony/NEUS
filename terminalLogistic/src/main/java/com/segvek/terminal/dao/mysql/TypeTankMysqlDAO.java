
package com.segvek.terminal.dao.mysql;

import com.mysql.jdbc.PreparedStatement;
import com.segvek.terminal.dao.DAOException;
import com.segvek.terminal.dao.TypeTankDAO;
import com.segvek.terminal.db.ConnectionManager;
import com.segvek.terminal.model.Tank;
import com.segvek.terminal.model.TypeTank;
import com.segvek.terminal.model.lazy.TypeTankLazy;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class TypeTankMysqlDAO implements TypeTankDAO{

    @Override
    public TypeTank getTypeTankByTank(Tank tank) throws DAOException {
        TypeTank typeTank = null;
        Connection connection = null;
        PreparedStatement statment = null;
        ResultSet res=null;
        try {
            connection = ConnectionManager.getInstance().instanceConnection();
            String request="SELECT * FROM tank t INNER JOIN typetank tt ON t.idTypeTank=tt.id WHERE t.id=?;";
            statment = (PreparedStatement) connection.prepareStatement(request);
            statment.setLong(1, tank.getId());
            res = statment.executeQuery();
            if(res.next()){
                typeTank = new TypeTankLazy(res.getLong("id"), res.getInt("timeshipment"), res.getInt("maxV"),null);
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
        return typeTank;
    }
    
}
