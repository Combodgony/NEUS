package com.segvek.terminal.dao.mysql;

import com.segvek.terminal.dao.ContentContractDAO;
import static com.segvek.terminal.dao.DAO.DEBUG;
import com.segvek.terminal.dao.DAOException;
import com.segvek.terminal.model.Cargo;
import com.segvek.terminal.model.ContentContract;
import com.segvek.terminal.model.Contract;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

public class ContentContractMysqlDAO implements ContentContractDAO {
    
    @Autowired
    private JdbcTemplate jdbcTemplate;
    
    @Override
    public List<ContentContract> getContentByContract(Contract c) throws DAOException {
        String request="SELECT con.volume, c.id, c.name FROM contentcontract con INNER JOIN cargo c ON con.idCargo=c.id WHERE idContract=?";
        List<ContentContract> list =  jdbcTemplate.query(request, new Object[]{c.getId()},new ContentContractRowMapper());
        if(DEBUG)
            Logger.getLogger(ContentContractMysqlDAO.class.getName()).info(request);
        list.forEach((cc)->cc.setContract(c));
        return list;
    }

    @Override
    public void saveContentContract(ContentContract cc) throws DAOException {
        String request="INSERT INTO contentcontract (idContract,idCargo,volume) VALUES (?,?,?);";
        if(DEBUG)
            Logger.getLogger(ContentContractMysqlDAO.class.getName()).info(request);
        jdbcTemplate.update(request,cc.getContract().getId(),cc.getCargo().getId(),cc.getVolume());        
    }
    
    private static final class ContentContractRowMapper implements RowMapper<ContentContract>{
        @Override
        public ContentContract mapRow(ResultSet res, int rowNum) throws SQLException {
            return new ContentContract(new Cargo(res.getLong("id"), res.getString("name")), null, res.getInt("volume"));
        }
    }
}
