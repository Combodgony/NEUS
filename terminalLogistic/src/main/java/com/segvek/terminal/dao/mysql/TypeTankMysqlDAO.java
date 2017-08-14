package com.segvek.terminal.dao.mysql;

import static com.segvek.terminal.dao.DAO.DEBUG;
import com.segvek.terminal.dao.DAOException;
import com.segvek.terminal.dao.TypeTankDAO;
import com.segvek.terminal.model.Tank;
import com.segvek.terminal.model.TypeTank;
import com.segvek.terminal.model.lazy.TypeTankLazy;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

public class TypeTankMysqlDAO implements TypeTankDAO{

    @Autowired
    private JdbcTemplate jdbcTemplate;
    
    @Override
    public TypeTank getTypeTankByTank(Tank tank) throws DAOException {
        String request="SELECT * FROM tank t INNER JOIN typetank tt ON t.idTypeTank=tt.id WHERE t.id=?;";
        if(DEBUG)
            Logger.getLogger(TypeTankMysqlDAO.class.getName()).info(request);
        return jdbcTemplate.queryForObject(request, new Object[]{tank.getId()} , new TypeTankRowMapper());
    }
    
    private static final class TypeTankRowMapper implements RowMapper<TypeTank>{
        @Override
        public TypeTank mapRow(ResultSet res, int rowNum) throws SQLException {
            return new TypeTankLazy(res.getLong("id"), res.getInt("timeshipment"), res.getInt("maxV"),null);
        }
    }
}
