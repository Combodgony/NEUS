/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.segvek.terminal.db;

import com.segvek.terminal.dao.mysql.ClientMysqlDAO;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author valeriy.lipin
 */
public class ConnectionManager {
    private static ConnectionManager cm=new ConnectionManager();
    
    public static ConnectionManager getInstance(){
        return cm;
    }
    
    
    private String url = "jdbc:mysql://localhost:3306/terminal";
    private String name = "root";
    private String password = "";
    
    
    
    public Connection instanceConnection() throws SQLException{
        Properties properties=new Properties();
        properties.setProperty("user",name);
        properties.setProperty("password",password);
        properties.setProperty("useUnicode","true");
        properties.setProperty("characterEncoding","UTF-8");
        return(DriverManager.getConnection(url,	properties));
    }
    
    private ConnectionManager() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException ex) {
            System.out.println("error Драйвер не подключен");
            Logger.getLogger(ClientMysqlDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    
}