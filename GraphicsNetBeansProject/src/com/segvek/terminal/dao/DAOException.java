/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.segvek.terminal.dao;

/**
 *
 * @author valeriy.lipin
 */
public class DAOException extends Exception {

    @Override
    public String getMessage() {
        return "DAOException: " + super.getMessage(); //To change body of generated methods, choose Tools | Templates.
    }
    
}
