package com.segvek.terminal.dao.mysql;

import static com.segvek.terminal.dao.DAO.DEBUG;
import com.segvek.terminal.dao.DAOException;
import com.segvek.terminal.model.Estakada;
import com.segvek.terminal.model.lazy.EstakadaLazy;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import com.segvek.terminal.dao.EstakadaDAO;
import com.segvek.terminal.model.DrainLocation;
import java.util.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

public class EstakadMysqlDAO implements EstakadaDAO{

    @Autowired
    private JdbcTemplate jdbcTemplate;
    
    @Override
    public List<Estakada> getAllEstacads() throws DAOException {
        String request="SELECT * FROM estakada";
        if(DEBUG)
            Logger.getLogger(EstakadMysqlDAO.class.getName()).info(request);
        return jdbcTemplate.query(request, new EstakadaRowMapper());
    }

    @Override
    public Estakada getEstakadaByDrainLocation(DrainLocation drainLocation) throws DAOException {
        String request="SELECT e.* FROM drainlocation l INNER JOIN estakada e ON l.idEstakada=e.id WHERE l.id=?;";
        if(DEBUG)
            Logger.getLogger(EstakadMysqlDAO.class.getName()).info(request);
        return jdbcTemplate.queryForObject(request, new Object[]{drainLocation.getId()}, new EstakadaRowMapper());
    }    
    private static final class EstakadaRowMapper implements RowMapper<Estakada>{
        @Override
        public Estakada mapRow(ResultSet res, int rowNum) throws SQLException {
            return new EstakadaLazy(res.getLong("id"), null, res.getString("number"), null);
        }
    }
}
