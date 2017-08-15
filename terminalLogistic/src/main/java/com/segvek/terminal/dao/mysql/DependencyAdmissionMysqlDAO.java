package com.segvek.terminal.dao.mysql;

import com.segvek.terminal.Loader;
import com.segvek.terminal.dao.AdmissionDAO;
import com.segvek.terminal.dao.DAOException;
import com.segvek.terminal.dao.DependencyAdmissionDAO;
import com.segvek.terminal.model.Admission;
import com.segvek.terminal.model.DependencyAdmission;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

public class DependencyAdmissionMysqlDAO implements DependencyAdmissionDAO {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    AdmissionDAO admissionDAO;

    public DependencyAdmissionMysqlDAO(AdmissionDAO admissionDAO) {
        this.admissionDAO = admissionDAO;
    }

    

    @Override
    public void addDependencyAdmission(DependencyAdmission dependencyAdmission) throws DAOException {
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
        return jdbcTemplate.query(request, new DependencyAdmissionRowMapper(admissionDAO));
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
                return new DependencyAdmission(depend, independed);
            } catch (DAOException ex) {
                Logger.getLogger(DependencyAdmissionMysqlDAO.class.getName()).log(Level.SEVERE, null, ex);
                throw new SQLException(ex.getMessage());
            }
        }

    }
}
