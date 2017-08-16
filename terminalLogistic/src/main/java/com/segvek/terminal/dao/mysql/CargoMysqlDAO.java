package com.segvek.terminal.dao.mysql;

import static com.segvek.terminal.dao.DAO.DEBUG;
import com.segvek.terminal.dao.DAOException;
import com.segvek.terminal.model.Admission;
import com.segvek.terminal.model.Cargo;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import com.segvek.terminal.dao.CargoDAO;

public class CargoMysqlDAO implements CargoDAO{

    @Autowired
    private JdbcTemplate jdbcTemplate;
    
    @Override
    public List<Cargo> getAllCargo() throws DAOException {
        String request="SELECT * FROM cargo";
        if(DEBUG)
            Logger.getLogger(CargoMysqlDAO.class.getName()).info(request);
        return jdbcTemplate.query(request, new CargoRowMapper());
    }

    @Override
    public Cargo getCargoByAdmission(Admission admission) throws DAOException {
        String request="SELECT c.* FROM admission a INNER JOIN cargo c ON c.id=a.`idCargo` WHERE a.id=?;";
        if(DEBUG)
            Logger.getLogger(CargoMysqlDAO.class.getName()).info(request);
        return jdbcTemplate.queryForObject(request, new Object[]{admission.getId()},new CargoRowMapper());
    }
    
    private static final class CargoRowMapper implements RowMapper<Cargo>{
        @Override
        public Cargo mapRow(ResultSet res, int rowNum) throws SQLException {
            return new Cargo(res.getLong("id"), res.getString("name"));
        }
    }
}
