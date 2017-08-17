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
import com.segvek.terminal.dao.AdmissionDAO;
import com.segvek.terminal.model.DependencyAdmission;
import java.util.logging.Level;

public class AdmissionMysqlDAO implements AdmissionDAO{

    @Autowired
    private JdbcTemplate jdbcTemplate;
    
    @Override
    public List<Admission> getAllAdmission() throws DAOException {
        String request="SELECT * FROM admission";
        if(DEBUG)
            Logger.getLogger(AdmissionMysqlDAO.class.getName()).info(request);
        return jdbcTemplate.query(request, new AdmissionRowMapper());
    }

    @Override
    public void update(Admission a) throws DAOException {
        String request="UPDATE admission SET `idContract`=?, `idCargo`=?, `idDraionLocation`=?, `idTank`=?, volume=?, "
                + "`factBeginDate`=?,`factEndDate`=?, `idStationaryStorage`=?,`planBeginDate`=?, plan=? WHERE id=?;";
        if(DEBUG)
            Logger.getLogger(AdmissionMysqlDAO.class.getName()).info(request);
        jdbcTemplate.update(request,new Object[]{a.getContract().getId(),a.getCargo().getId(),a.getDrainLocation().getId(),a.getTank().getId(),a.getVolume()
                ,a.getFactBegin(),a.getFactEnd(),null,a.getBegin(),a.isPlan(),a.getId()},
                new int[]{Types.INTEGER,Types.INTEGER,Types.INTEGER,Types.INTEGER,Types.INTEGER,Types.TIMESTAMP,Types.TIMESTAMP,Types.INTEGER,Types.TIMESTAMP,Types.TINYINT,Types.INTEGER});
    }

    @Override
    public Admission getAdmissionById(Long id) throws DAOException {
        String request = "SELECT * FROM admission WHERE id=?;";
        return jdbcTemplate.queryForObject(request, new Object[]{id}, new AdmissionRowMapper());
    }

    @Override
    public List<Admission> getIndependedAdmissionByAdmission(Admission admission) throws DAOException {
        String request = "SELECT a.* FROM dependencyadmission d INNER JOIN admission a ON a.id=d.`idIndependent` WHERE d.`idDependent`=?;";
        if(DEBUG)
            Logger.getLogger(AdmissionMysqlDAO.class.getName()).info(request);
        return jdbcTemplate.query(request, new Object[]{admission.getId()} , new AdmissionRowMapper());
    }

    @Override
    public List<Admission> getDependAdmissionByAdmission(Admission admission) throws DAOException {
        String request = "SELECT a.* FROM dependencyadmission d INNER JOIN admission a ON a.id=d.idDependent WHERE d.idIndependent=?;";
        if(DEBUG)
            Logger.getLogger(AdmissionMysqlDAO.class.getName()).info(request);
        return jdbcTemplate.query(request, new Object[]{admission.getId()} , new AdmissionRowMapper());
    }
    
    
     @Override
    public void addDependencyAdmission(DependencyAdmission dependencyAdmission) throws DAOException {
        //todo add code for getting id new row 
        String request = "INSERT INTO dependencyadmission (idDependent, idIndependent) VALUES(?,?)";
        if(DEBUG)
            Logger.getLogger(AdmissionMysqlDAO.class.getName()).info(request);
        jdbcTemplate.update(request, dependencyAdmission.getAdmission().getId(), dependencyAdmission.getIndependet().getId());
    }

    @Override
    public List<DependencyAdmission> getAllDependencyAdmissions() throws DAOException {
        String request = "SELECT * FROM dependencyadmission";
        if(DEBUG)
            Logger.getLogger(AdmissionMysqlDAO.class.getName()).info(request);
        return jdbcTemplate.query(request, new DependencyAdmissionRowMapper(this));
    }

    @Override
    public List<DependencyAdmission> getDependencyAdmissionsByAdmission(Admission admission) throws DAOException {
        String request = "SELECT * FROM dependencyadmission WHERE `idDependent`=? OR `idIndependent`=?";
        if(DEBUG)
            Logger.getLogger(AdmissionMysqlDAO.class.getName()).info(request);
        return jdbcTemplate.query(request, new Object[]{admission.getId(),admission.getId()},new DependencyAdmissionRowMapper(this));
    }
    
    private static final class AdmissionRowMapper implements RowMapper<Admission>{
        @Override
        public Admission mapRow(ResultSet res, int rowNum) throws SQLException {
            return new AdmissionLazy(res.getLong("id"), null, null
                        , res.getInt("volume"), res.getTimestamp("planBeginDate"), null, null
                        , res.getTimestamp("factBeginDate"), res.getTimestamp("factEndDate"), null, res.getBoolean("plan"));
        }
    }

    private static final class DependencyAdmissionRowMapper implements RowMapper<DependencyAdmission> {

        AdmissionDAO admissionDAO;

        public DependencyAdmissionRowMapper(AdmissionDAO admissionDAO) {
            this.admissionDAO = admissionDAO;
        }

        @Override
        public DependencyAdmission mapRow(ResultSet rs, int rowNum) throws SQLException {
            try {
                Admission depend = admissionDAO.getAdmissionById(rs.getLong("idDependent"));
                Admission independed = admissionDAO.getAdmissionById(rs.getLong("idIndependent"));
                return new DependencyAdmission(rs.getLong("id"),depend, independed);
            } catch (DAOException ex) {
                Logger.getLogger(AdmissionMysqlDAO.class.getName()).log(Level.SEVERE, null, ex);
                throw new SQLException(ex.getMessage());
            }
        }

    }
}
