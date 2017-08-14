package com.segvek.terminal.dao.mysql;

import com.segvek.terminal.dao.DAOException;
import com.segvek.terminal.dao.TypeEstakadaDAO;
import com.segvek.terminal.model.Estakada;
import com.segvek.terminal.model.TypeEstakada;
import com.segvek.terminal.model.TypeTank;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

public class TypeEstakadaMysqlDAO implements TypeEstakadaDAO{

    @Autowired
    private JdbcTemplate jdbcTemplate;
    
    @Override
    public TypeEstakada getTypeEstacadaByEstakada(Estakada estakada) throws DAOException {
        String request="SELECT t.* FROM estakada e INNER JOIN typeestakada t ON t.id=e.idTypeEstakada WHERE e.id=?;";
        return jdbcTemplate.queryForObject(request, new Object[]{estakada.getId()} , new TypeEstakadaRowMapper());
    }

    @Override
    public TypeEstakada getTypeEstacadaByTypeTank(TypeTank typeTank) throws DAOException {
        String request="SELECT e.* FROM typetank t INNER JOIN typeestakada e ON e.id=t.idTypeEstakada WHERE t.id=?;";
        return jdbcTemplate.queryForObject(request, new Object[]{typeTank.getId()} , new TypeEstakadaRowMapper());
    }
    
    private static final class TypeEstakadaRowMapper implements RowMapper<TypeEstakada>{
        @Override
        public TypeEstakada mapRow(ResultSet res, int rowNum) throws SQLException {
            return  new TypeEstakada(res.getLong("id"), res.getString("name"));
        }
    }
}
