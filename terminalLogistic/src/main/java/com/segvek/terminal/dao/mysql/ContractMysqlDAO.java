package com.segvek.terminal.dao.mysql;

import com.mysql.jdbc.PreparedStatement;
import com.segvek.terminal.dao.ContractDAO;
import com.segvek.terminal.dao.DAOException;
import com.segvek.terminal.model.Admission;
import com.segvek.terminal.model.Client;
import com.segvek.terminal.model.Contract;
import com.segvek.terminal.model.lazy.ContractLazy;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

public class ContractMysqlDAO implements ContractDAO {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public List<Contract> getContractsByClient(Client c) throws DAOException {
        String request = "SELECT * FROM contract WHERE idClient=?";
        return jdbcTemplate.query(request, new Object[]{c.getId()}, new ContractRowMapper());
    }

    @Override
    public void addContract(Contract contract) throws DAOException {
        String request = "INSERT INTO contract(idClient,number,beginDate,endDate) VALUES (?,?,?,?)";
        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(((connection) ->{
                PreparedStatement statment = (PreparedStatement) connection.prepareStatement(request, new String[]{"id"});
                statment.setLong(1, contract.getClient().getId());
                statment.setString(2, contract.getNumber());
                statment.setDate(3, new java.sql.Date(contract.getBeginDate().getTime()));
                statment.setDate(4, new java.sql.Date(contract.getEndDate().getTime()));
                return statment;
            })
        , keyHolder);
        contract.setId(keyHolder.getKey().longValue());
    }

    @Override
    public List<Contract> getAllContract() throws DAOException {
        String request = "SELECT * FROM contract";
        return jdbcTemplate.query(request, new ContractRowMapper());
    }

    @Override
    public void updateContract(Contract contract) throws DAOException {
        String request = "UPDATE contract SET `idClient`=?, `number`=?, `beginDate`=?, `endDate`=? WHERE id=?;";
        jdbcTemplate.update(request, contract.getClient().getId(), contract.getNumber(),
                new java.sql.Date(contract.getBeginDate().getTime()),
                new java.sql.Date(contract.getEndDate().getTime()), contract.getId());
    }

    @Override
    public Contract getContractByAdmission(Admission admission) throws DAOException {
        String request = "SELECT c.* FROM admission a INNER JOIN contract c ON c.id=a.`idContract` WHERE a.id=?;";
        return jdbcTemplate.queryForObject(request, new Object[]{admission.getId()}, new ContractRowMapper());
    }

    private static final class ContractRowMapper implements RowMapper<Contract> {
        @Override
        public Contract mapRow(ResultSet res, int rowNum) throws SQLException {
            return new ContractLazy(res.getLong("id"), res.getString("number"),res.getDate("beginDate"),res.getDate("endDate"), null);
        }
    }
}
