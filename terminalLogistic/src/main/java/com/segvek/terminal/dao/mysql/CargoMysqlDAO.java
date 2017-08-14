package com.segvek.terminal.dao.mysql;

import com.segvek.terminal.dao.CargoDao;
import static com.segvek.terminal.dao.DAO.DEBUG;
import com.segvek.terminal.dao.DAOException;
import com.segvek.terminal.model.Cargo;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

public class CargoMysqlDAO implements CargoDao{

    @Autowired
    private JdbcTemplate jdbcTemplate;
    
    @Override
    public List<Cargo> getAllCargo() throws DAOException {
        String request="SELECT * FROM cargo";
        if(DEBUG)
            Logger.getLogger(CargoMysqlDAO.class.getName()).info(request);
        return jdbcTemplate.query(request, new CargoRowMapper());
    }
    
    private static final class CargoRowMapper implements RowMapper<Cargo>{
        @Override
        public Cargo mapRow(ResultSet res, int rowNum) throws SQLException {
            return new Cargo(res.getLong("id"), res.getString("name"));
        }
    }
}
