package com.segvek.terminal.dao.mysql;

import com.segvek.terminal.dao.DAOException;
import com.segvek.terminal.model.Admission;
import com.segvek.terminal.model.lazy.AdmissionLazy;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.List;
import java.util.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import com.segvek.terminal.dao.AdmissionDao;


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

    @Override
    public void update(Admission a) throws DAOException {
        String request="UPDATE admission SET `idContract`=?, `idCargo`=?, `idDraionLocation`=?, `idTank`=?, volume=?, "
                + "`factBeginDate`=?,`factEndDate`=?, `idStationaryStorage`=?,`planBeginDate`=?, plan=? WHERE id=?;";
        if(DEBUG)
            Logger.getLogger(AdmissionMysqlDao.class.getName()).info(request);
        jdbcTemplate.update(request,new Object[]{a.getContract().getId(),null,a.getDrainLocation().getId(),a.getTank().getId(),a.getVolume()
                ,a.getFactBegin(),a.getFactEnd(),null,a.getBegin(),a.isPlan(),a.getId()},
                new int[]{Types.INTEGER,Types.INTEGER,Types.INTEGER,Types.INTEGER,Types.INTEGER,Types.TIMESTAMP,Types.TIMESTAMP,Types.INTEGER,Types.TIMESTAMP,Types.TINYINT,Types.INTEGER});
    }
    
    private static final class AdmissionRowMapper implements RowMapper<Admission>{
        @Override
        public Admission mapRow(ResultSet res, int rowNum) throws SQLException {
            return new AdmissionLazy(res.getLong("id"), null, null
                        , res.getInt("volume"), res.getTimestamp("planBeginDate"), null, null
                        , res.getTimestamp("factBeginDate"), res.getTimestamp("factEndDate"), null, res.getBoolean("plan"));
        }
    }
}
