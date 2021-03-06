package com.segvek.terminal.dao.mysql;

import com.mysql.jdbc.PreparedStatement;
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
import static com.segvek.terminal.dao.DAO.DEBUG;
import com.segvek.terminal.model.Cargo;
import com.segvek.terminal.model.Contract;
import com.segvek.terminal.model.DependencyAdmission;
import com.segvek.terminal.model.lazy.DependencyAdmissionLazy;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

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
    public List<Admission> getIndependentAdmissionByAdmission(Admission admission) throws DAOException {
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
    public void addAdmission(Admission admission) throws DAOException {
        String request = "INSERT INTO admission (idContract, idCargo, idDraionLocation, idTank, volume, factBeginDate, factEndDate, idStationaryStorage, planBeginDate, plan) VALUES (?,?,?,?,?,?,?,?,?,?);";
        if(DEBUG)
            Logger.getLogger(ContractMysqlDAO.class.getName()).info(request);
        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(((connection) ->{
                PreparedStatement statment = (PreparedStatement) connection.prepareStatement(request, new String[]{"id"});
                Long contractId=null;
                if(admission.getContract()!=null)
                    contractId=admission.getContract().getId();
                statment.setLong(1, contractId);
                Long cargoId=null;
                if(admission.getCargo()!=null)
                    cargoId=admission.getCargo().getId();
                statment.setLong(2, cargoId);
                Long drainLocationId=null;
                if(admission.getDrainLocation()!=null)
                    drainLocationId=admission.getDrainLocation().getId();
                statment.setLong(3, drainLocationId);
                Long tankId=null;
                if(admission.getTank()!=null)
                    tankId=admission.getTank().getId();
                statment.setLong(4, tankId);
                
                statment.setInt(5, admission.getVolume());
                if(admission.getFactBegin()!=null)
                    statment.setDate(6, new java.sql.Date(admission.getFactBegin().getTime()));
                else
                    statment.setNull(6, Types.TIMESTAMP);
                if(admission.getFactEnd()!=null)
                    statment.setDate(7, new java.sql.Date(admission.getFactEnd().getTime()));
                else
                    statment.setNull(7, Types.TIMESTAMP);
                if(admission.getStorage()!=null)
                    statment.setLong(8, admission.getStorage().getId());
                else
                    statment.setNull(8, Types.INTEGER);
                if(admission.getBegin()!=null)
                    statment.setDate(9, new java.sql.Date(admission.getBegin().getTime()));
                else
                    statment.setNull(9, Types.TIMESTAMP);
                statment.setBoolean(10, admission.isPlan());
                return statment;
            })
        , keyHolder);
        admission.setId(keyHolder.getKey().longValue());
    }

    @Override
    public List<Admission> getAdmissionsByContractAndCargo(Contract contract, Cargo cargo) throws DAOException {
	String request = "SELECT * FROM admission WHERE idContract=? AND idCargo=?;";
	return jdbcTemplate.query(request, new Object[]{contract.getId(),cargo.getId()}, new AdmissionRowMapper());
    }

 

    @Override
    public Admission getDependAdmissinByDependencyAdmission(DependencyAdmission dependencyAdmission) throws DAOException {
	String request = "SELECT a.* FROM dependencyadmission d INNER JOIN admission a ON d.idDependent = a.id WHERE d.id=?;";
	return jdbcTemplate.queryForObject(request, new Object[]{dependencyAdmission.getId()}, new AdmissionRowMapper());
    }

    @Override
    public Admission getIndependentAdmissinByDependencyAdmission(DependencyAdmission dependencyAdmission) throws DAOException {
	String request = "SELECT a.* FROM dependencyadmission d INNER JOIN admission a ON d.idIndependent = a.id WHERE d.id=?;";
	return jdbcTemplate.queryForObject(request, new Object[]{dependencyAdmission.getId()}, new AdmissionRowMapper());
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
