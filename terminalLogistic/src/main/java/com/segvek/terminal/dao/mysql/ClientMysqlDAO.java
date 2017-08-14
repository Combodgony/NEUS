package com.segvek.terminal.dao.mysql;

import com.segvek.terminal.model.lazy.ClientLazy;
import com.segvek.terminal.dao.ClientDAO;
import com.segvek.terminal.dao.DAOException;
import com.segvek.terminal.model.Client;
import com.segvek.terminal.model.Contract;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;


public class ClientMysqlDAO implements ClientDAO{
    
    @Autowired
    private JdbcTemplate jdbcTemplate;
    
    @Override
    public List<Client> getAll() throws DAOException { 
        String request="SELECT * FROM client";
        return jdbcTemplate.query(request, new ClientRowMapper());
    }

    @Override
    public Client getClientByContract(Contract contract) throws DAOException {
        String request="SELECT cl.* FROM client cl INNER JOIN contract con ON con.`idClient`=cl.id WHERE con.id=?;";
        return jdbcTemplate.queryForObject(request, new Object[]{contract.getId()}, new ClientRowMapper());
    }
    
    private static final class ClientRowMapper implements RowMapper<Client>{
        @Override
        public Client mapRow(ResultSet res, int i) throws SQLException {
            return new ClientLazy(res.getLong("id"), res.getString("name"), res.getString("adress"));
        }
    } 
}
