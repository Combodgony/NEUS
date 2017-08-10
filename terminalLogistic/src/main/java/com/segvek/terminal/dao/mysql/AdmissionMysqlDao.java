
package com.segvek.terminal.dao.mysql;

import com.mysql.jdbc.PreparedStatement;
import com.segvek.terminal.dao.AdmissionDao;
import com.segvek.terminal.dao.DAOException;
import com.segvek.terminal.db.ConnectionManager;
import com.segvek.terminal.model.Admission;
import com.segvek.terminal.model.lazy.AdmissionLazy;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;


public class AdmissionMysqlDao implements AdmissionDao{

    @Override
    public List<Admission> getAllAdmission() throws DAOException {
        List<Admission> list = new ArrayList<>();
        Connection connection = null;
        PreparedStatement statment = null;
        ResultSet res=null;
        try {
            connection = ConnectionManager.getInstance().instanceConnection();
            String request="SELECT * FROM admission";
            statment = (PreparedStatement) connection.prepareStatement(request);
            res = statment.executeQuery();
            while(res.next()){
                AdmissionLazy a = new AdmissionLazy(res.getLong("id"), null, null
                        , res.getInt("volume"), res.getDate("planBeginDate"), null, null
                        , res.getDate("factBeginDate"), res.getDate("factEndDate"), res.getBoolean("plan"));
                list.add(a);
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
