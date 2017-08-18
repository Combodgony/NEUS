package com.segvek.terminal.dao.mysql;

import static com.segvek.terminal.dao.DAO.DEBUG;
import com.segvek.terminal.dao.DAOException;
import com.segvek.terminal.dao.DependencyAdmissionDAO;
import com.segvek.terminal.model.Admission;
import com.segvek.terminal.model.DependencyAdmission;
import com.segvek.terminal.model.lazy.DependencyAdmissionLazy;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

public class DependencyAdmissionMysqlDAO implements DependencyAdmissionDAO {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public void addDependencyAdmission(DependencyAdmission dependencyAdmission) throws DAOException {
	//todo add code for getting id new row 
	String request = "INSERT INTO dependencyadmission (idDependent, idIndependent) VALUES(?,?)";
	if (DEBUG) {
	    Logger.getLogger(AdmissionMysqlDAO.class.getName()).info(request);
	}
	jdbcTemplate.update(request, dependencyAdmission.getDepend().getId(), dependencyAdmission.getIndependnet().getId());
    }

    @Override
    public List<DependencyAdmission> getAllDependencyAdmissions() throws DAOException {
	String request = "SELECT * FROM dependencyadmission";
	if (DEBUG) {
	    Logger.getLogger(AdmissionMysqlDAO.class.getName()).info(request);
	}
	return jdbcTemplate.query(request, new DependencyAdmissionRowMapper());
    }

    @Override
    public List<DependencyAdmission> getDependencyAdmissionsByAdmission(Admission admission) throws DAOException {
	String request = "SELECT * FROM dependencyadmission WHERE `idDependent`=? OR `idIndependent`=?";
	if (DEBUG) {
	    Logger.getLogger(AdmissionMysqlDAO.class.getName()).info(request);
	}
	return jdbcTemplate.query(request, new Object[]{admission.getId(), admission.getId()}, new DependencyAdmissionRowMapper());
    }

    @Override
    public void deleteDependencyAdmissionByAdmission(Admission admission) throws DAOException {
	String requString = "DELETE FROM dependencyadmission WHERE `idDependent`=? OR `idIndependent`=?";
	jdbcTemplate.update(requString, admission.getId(), admission.getId());
    }

    @Override
    public void deleteDependencyAdmission(DependencyAdmission da) throws DAOException {
	String requString = "DELETE FROM dependencyadmission WHERE `id`=?";
	jdbcTemplate.update(requString, da.getId());
    }

    private static final class DependencyAdmissionRowMapper implements RowMapper<DependencyAdmission> {

	@Override
	public DependencyAdmission mapRow(ResultSet rs, int rowNum) throws SQLException {
	    return new DependencyAdmissionLazy(rs.getLong("id"), null, null);
	}

    }
}
