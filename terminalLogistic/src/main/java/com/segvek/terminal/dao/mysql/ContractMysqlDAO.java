package com.segvek.terminal.dao.mysql;

import com.mysql.jdbc.PreparedStatement;
import com.segvek.terminal.dao.ContractDAO;
import com.segvek.terminal.dao.DAOException;
import com.segvek.terminal.db.ConnectionManager;
import com.segvek.terminal.model.Admission;
import com.segvek.terminal.model.Client;
import com.segvek.terminal.model.Contract;
import com.segvek.terminal.model.lazy.AdmissionLazy;
import com.segvek.terminal.model.lazy.ContractLazy;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ContractMysqlDAO implements ContractDAO {

    @Override
    public List<Contract> getContractsByClient(Client c) throws DAOException {
        List<Contract> list = new ArrayList<Contract>();

        Connection connection = null;
        PreparedStatement statment = null;
        ResultSet res = null;
        try {
            connection = ConnectionManager.getInstance().instanceConnection();
            String request = "SELECT * FROM contract WHERE idClient=?";
            statment = (PreparedStatement) connection.prepareStatement(request);
            statment.setLong(1, c.getId());
            res = statment.executeQuery();
            while (res.next()) {
                ContractLazy conrtact = new ContractLazy(res.getLong("id"),
                         res.getString("number"),
                         res.getDate("beginDate"),
                         res.getDate("endDate"), c);
                list.add(conrtact);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ClientMysqlDAO.class.getName()).log(Level.SEVERE, null, ex);
            throw new DAOException();
        } finally {
            try {
                if (res != null) {
                    res.close();
                }
            } catch (SQLException ex) {
                Logger.getLogger(ClientMysqlDAO.class.getName()).log(Level.SEVERE, null, ex);
                throw new DAOException();
            } finally {
                try {
                    if (statment != null) {
                        statment.close();
                    }
                } catch (SQLException ex) {
                    Logger.getLogger(ClientMysqlDAO.class.getName()).log(Level.SEVERE, null, ex);
                    throw new DAOException();
                } finally {
                    if (connection != null) {
                        try {
                            connection.close();
                        } catch (SQLException ex) {
                            Logger.getLogger(ClientMysqlDAO.class.getName()).log(Level.SEVERE, null, ex);
                            throw new DAOException();
                        }
                    }
                }
            }
        }
        return list;
    }

    @Override
    public void addContract(Contract contract) throws DAOException {
        Connection connection = null;
        PreparedStatement statment = null;
        PreparedStatement statmentid = null;
        ResultSet res = null;
        try {
            connection = ConnectionManager.getInstance().instanceConnection();
            String request = "INSERT INTO contract(idClient,number,beginDate,endDate) VALUES (?,?,?,?)";
            statment = (PreparedStatement) connection.prepareStatement(request);
            statment.setLong(1, contract.getClient().getId());
            statment.setString(2, contract.getNumber());
            statment.setDate(3, new java.sql.Date(contract.getBeginDate().getTime()));
            statment.setDate(4, new java.sql.Date(contract.getEndDate().getTime()));
            statment.executeUpdate();
            statmentid = (PreparedStatement) connection.prepareStatement("SELECT LAST_INSERT_ID() \"id\";");
            res = statmentid.executeQuery();
            if (res.next()) {
                contract.setId(res.getLong("id"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(ClientMysqlDAO.class.getName()).log(Level.SEVERE, null, ex);
            throw new DAOException();
        } finally {
            try {
                if (res != null) {
                    res.close();
                }
            } catch (SQLException ex) {
                Logger.getLogger(ClientMysqlDAO.class.getName()).log(Level.SEVERE, null, ex);
                throw new DAOException();
            } finally {
                try {
                    if (statment != null) {
                        statment.close();
                    }
                } catch (SQLException ex) {
                    Logger.getLogger(ClientMysqlDAO.class.getName()).log(Level.SEVERE, null, ex);
                    throw new DAOException();
                } finally {
                    if (connection != null) {
                        try {
                            connection.close();
                        } catch (SQLException ex) {
                            Logger.getLogger(ClientMysqlDAO.class.getName()).log(Level.SEVERE, null, ex);
                            throw new DAOException();
                        }
                    }
                }
            }
        }
    }

    @Override
    public List<Contract> getAllContract() throws DAOException {
        List<Contract> list = new ArrayList<Contract>();

        Connection connection = null;
        PreparedStatement statment = null;
        ResultSet res = null;
        try {
            connection = ConnectionManager.getInstance().instanceConnection();
            String request = "SELECT * FROM contract";
            statment = (PreparedStatement) connection.prepareStatement(request);
            res = statment.executeQuery();
            while (res.next()) {
                ContractLazy conrtact = new ContractLazy(res.getLong("id"),
                         res.getString("number"),
                         res.getDate("beginDate"),
                         res.getDate("endDate"), null);
                list.add(conrtact);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ClientMysqlDAO.class.getName()).log(Level.SEVERE, null, ex);
            throw new DAOException();
        } finally {
            try {
                if (res != null) {
                    res.close();
                }
            } catch (SQLException ex) {
                Logger.getLogger(ClientMysqlDAO.class.getName()).log(Level.SEVERE, null, ex);
                throw new DAOException();
            } finally {
                try {
                    if (statment != null) {
                        statment.close();
                    }
                } catch (SQLException ex) {
                    Logger.getLogger(ClientMysqlDAO.class.getName()).log(Level.SEVERE, null, ex);
                    throw new DAOException();
                } finally {
                    if (connection != null) {
                        try {
                            connection.close();
                        } catch (SQLException ex) {
                            Logger.getLogger(ClientMysqlDAO.class.getName()).log(Level.SEVERE, null, ex);
                            throw new DAOException();
                        }
                    }
                }
            }
        }
        return list;
    }

    @Override
    public void updateContract(Contract contract) throws DAOException {
        Connection connection = null;
        PreparedStatement statment = null;
        try {
            connection = ConnectionManager.getInstance().instanceConnection();
            String request = "UPDATE contract SET `idClient`=?, `number`=?, `beginDate`=?, `endDate`=? WHERE id=?;";
            statment = (PreparedStatement) connection.prepareStatement(request);
            statment.setLong(1, contract.getClient().getId());
            statment.setString(2, contract.getNumber());
            statment.setDate(3, new java.sql.Date(contract.getBeginDate().getTime()));
            statment.setDate(4, new java.sql.Date(contract.getEndDate().getTime()));
            statment.setLong(5, contract.getId());
            statment.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(ClientMysqlDAO.class.getName()).log(Level.SEVERE, null, ex);
            throw new DAOException();
        } finally {
            try {
                if (statment != null) {
                    statment.close();
                }
            } catch (SQLException ex) {
                Logger.getLogger(ClientMysqlDAO.class.getName()).log(Level.SEVERE, null, ex);
                throw new DAOException();
            } finally {
                if (connection != null) {
                    try {
                        connection.close();
                    } catch (SQLException ex) {
                        Logger.getLogger(ClientMysqlDAO.class.getName()).log(Level.SEVERE, null, ex);
                        throw new DAOException();
                    }
                }
            }
        }
    }

    @Override
    public Contract getContractByAdmission(Admission admission) throws DAOException {
        Contract conrtact = null;

        Connection connection = null;
        PreparedStatement statment = null;
        ResultSet res = null;
        try {
            connection = ConnectionManager.getInstance().instanceConnection();
            String request = "SELECT c.* FROM admission a INNER JOIN contract c ON c.id=a.`idContract` WHERE a.id=?;";
            statment = (PreparedStatement) connection.prepareStatement(request);
            statment.setLong(1, admission.getId());
            res = statment.executeQuery();
            while (res.next()) {
                conrtact = new ContractLazy(res.getLong("id"),
                         res.getString("number"),
                         res.getDate("beginDate"),
                         res.getDate("endDate"), null);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ClientMysqlDAO.class.getName()).log(Level.SEVERE, null, ex);
            throw new DAOException();
        } finally {
            try {
                if (res != null) {
                    res.close();
                }
            } catch (SQLException ex) {
                Logger.getLogger(ClientMysqlDAO.class.getName()).log(Level.SEVERE, null, ex);
                throw new DAOException();
            } finally {
                try {
                    if (statment != null) {
                        statment.close();
                    }
                } catch (SQLException ex) {
                    Logger.getLogger(ClientMysqlDAO.class.getName()).log(Level.SEVERE, null, ex);
                    throw new DAOException();
                } finally {
                    if (connection != null) {
                        try {
                            connection.close();
                        } catch (SQLException ex) {
                            Logger.getLogger(ClientMysqlDAO.class.getName()).log(Level.SEVERE, null, ex);
                            throw new DAOException();
                        }
                    }
                }
            }
        }
        return conrtact;
    }

}
