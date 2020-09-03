/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pharmacy.dao;

import com.pharmacy.admin.Admin;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author rktirtho
 */
public class AdminDbHelper {
    
    private static final DBConnector connector = DBConnector.getInstance();
    private static PreparedStatement statement;
    
    public static void main(String[] args) {
        Admin admin = new Admin();
        admin.setName("Rejaul Karim");
        admin.setUserName("rktirtho");
        admin.setAccType("admin");
        admin.setPassword("qwert");
        admin.setIsActive(true);
        
        registration(admin);
    }
    
    public static int login(){
        
        return 0;
    }
    public static int registration(Admin admin){
        int status=0;

        Connection connection = connector.getConnection();
        try {
            statement = connection.prepareCall("insert into "+DBConnector.ADMIN_TABLE+" ("
                    + DBConnector.NAME+" ,"
                    + DBConnector.EMAIL+" ,"
                    + DBConnector.PASSWORD+" ,"
                    + DBConnector.ACC_TYPE+" ,"
                    + DBConnector.IS_ACTIVE+") values(?,?,?,?,?)");
            statement.setString(1, admin.getName());
            statement.setString(2, admin.getUserName());
            statement.setString(3, admin.getPassword());
            statement.setString(4, admin.getAccType());
            statement.setBoolean(5, admin.isIsActive());
            
            status =  statement.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(AdminDbHelper.class.getName()).log(Level.SEVERE, null, ex);
            System.err.println(ex.getMessage());
        }
        return status;
    }
    
    public List<Admin> getAllAdmin(){
        return null;
    }
    
    public Admin getById(){
        return null;
    }
    
    public Admin getByEmail(){
        return null;
    }
}
