package com.segvek.terminal.dao.mysql;

import com.segvek.terminal.dao.DAOException;
import com.segvek.terminal.dao.DrainLocationDAO;
import com.segvek.terminal.model.Admission;
import com.segvek.terminal.model.DrainLocation;
import com.segvek.terminal.model.Estakada;
import com.segvek.terminal.model.lazy.DrainLocationLazy;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;


public class DrainLocationMysqlDAO implements DrainLocationDAO{

    @Autowired
    private JdbcTemplate jdbcTemplate;
    
    @Override
    public DrainLocation getDrainLocationByAdmission(Admission admission) throws DAOException {
        String request = "SELECT l.* FROM admission a INNER JOIN drainlocation l ON l.id=a.idDraionLocation WHERE a.id=?;";
        return jdbcTemplate.queryForObject(request, new Object[]{admission.getId()}, new DrainLocationRowMapper());
    }

    @Override
    public List<DrainLocation> getDrainLocationsByEstacada(Estakada estakada) throws DAOException {
        String request="SELECT l.* FROM estakada e INNER JOIN drainlocation l ON l.idEstakada=e.id WHERE e.id=?;";
        List<DrainLocation> list=jdbcTemplate.query(request, new Object[]{estakada.getId()}, new DrainLocationRowMapper());
        list.forEach((d)->d.setEstakada(estakada));
        return list;
    }
    private static final class DrainLocationRowMapper implements RowMapper<DrainLocation>{
        @Override
        public DrainLocation mapRow(ResultSet res, int rowNum) throws SQLException {
            return new DrainLocationLazy(res.getLong("id"), res.getString("number"), null);
        }
    }
}
