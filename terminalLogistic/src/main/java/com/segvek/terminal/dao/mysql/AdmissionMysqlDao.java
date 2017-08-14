package com.segvek.terminal.dao.mysql;

import com.segvek.terminal.dao.AdmissionDao;
import com.segvek.terminal.dao.DAOException;
import com.segvek.terminal.model.Admission;
import com.segvek.terminal.model.lazy.AdmissionLazy;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;


public class AdmissionMysqlDao implements AdmissionDao{

    @Autowired
    private JdbcTemplate jdbcTemplate;
    
    @Override
    public List<Admission> getAllAdmission() throws DAOException {
        String request="SELECT * FROM admission";
        if(DEBUG)
            Logger.getLogger(AdmissionMysqlDao.class.getName()).info(request);
        return jdbcTemplate.query(request, new AdmissionRowMapper());
    }
    
    private static final class AdmissionRowMapper implements RowMapper<Admission>{
        @Override
        public Admission mapRow(ResultSet res, int rowNum) throws SQLException {
            return new AdmissionLazy(res.getLong("id"), null, null
                        , res.getInt("volume"), res.getDate("planBeginDate"), null, null
                        , res.getDate("factBeginDate"), res.getDate("factEndDate"), res.getBoolean("plan"));
        }
    }
}
