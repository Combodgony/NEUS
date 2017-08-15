package com.segvek.terminal.dao.mysql;

import static com.segvek.terminal.dao.DAO.DEBUG;
import com.segvek.terminal.dao.DAOException;
import com.segvek.terminal.dao.TankDAO;
import com.segvek.terminal.model.Admission;
import com.segvek.terminal.model.Tank;
import com.segvek.terminal.model.lazy.TankLazy;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

public class TankMysqlDAO implements TankDAO{

    @Autowired
    private JdbcTemplate jdbcTemplate;
    
    @Override
    public Tank getTankByAdmission(Admission admission) throws DAOException {
        String request = "SELECT t.* FROM admission a INNER JOIN tank t ON t.id=a.`idTank` WHERE a.id=?;";
        if(DEBUG)
            Logger.getLogger(TankMysqlDAO.class.getName()).info(request);
        return jdbcTemplate.queryForObject(request, new Object[]{admission.getId()}, new TankRowMapper());
    }

    @Override
    public List<Tank> getAllTank() throws DAOException {
        String request = "SELECT * FROM tank;";
        if(DEBUG)
            Logger.getLogger(TankMysqlDAO.class.getName()).info(request);
        return jdbcTemplate.query(request, new TankRowMapper());
    }
    
    private static final class TankRowMapper implements RowMapper<Tank>{
        @Override
        public Tank mapRow(ResultSet res, int rowNum) throws SQLException {
            return new TankLazy(res.getLong("id"),res.getString("number"), null);
        }
    }
}
