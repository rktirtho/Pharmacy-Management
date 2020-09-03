/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pharmacy.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author rktirtho
 */
public class DBConnector {
    public static final String DB_NAME= "pharmacy_management";
    public static final String ID= "id";
    public static final String NAME= "name";
    public static final String ADMIN_TABLE= "admin_table";
    public static final String EMAIL= "email";
    public static final String PASSWORD= "password";
    public static final String ACC_TYPE= "auth_type";
    public static final String IS_ACTIVE= "status";
    public static final String IS_RESPONSED= "is_responsed";
    public static final String REG_TIME= "reg_time";
    public static final String SESSION= "session_id";
    private Connection connection;
    private static DBConnector connector= new DBConnector();

    public Connection getConnection() {
        
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/",
                    "root", "root");

            Statement statement = connection.createStatement();
             

            statement.execute("CREATE DATABASE IF NOT EXISTS "+DB_NAME);
            
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/" 
                     + DB_NAME,"root", "root");
            statement = connection.createStatement();

            statement.execute("CREATE TABLE IF NOT EXISTs "+ADMIN_TABLE+"( "
                    + ID + " int(20) NOT NULL AUTO_INCREMENT PRIMARY KEY, "
                    + NAME+ " varchar(50) NOT NULL, "
                    + EMAIL+ " varchar(50) NOT NULL, "
                    + PASSWORD+ " varchar(50) NOT NULL, "
                    + ACC_TYPE+" varchar(50) NOT NULL, "
                    + IS_ACTIVE +" varchar(50) NOT NULL, "
                    + REG_TIME+" timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP"
                    + ")");

        } catch (ClassNotFoundException ex) {
            Logger.getLogger(DBConnector.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(DBConnector.class.getName()).log(Level.SEVERE, null, ex);
        }
        return connection;
    }

    public static void main(String[] args) {
        
        try {
            System.out.println(getInstance().getConnection().getClientInfo());
        } catch (SQLException ex) {
            Logger.getLogger(DBConnector.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    private DBConnector(){
    }
    
    public static DBConnector getInstance() {
        if (connector == null) {
            connector = new DBConnector();
        }
        return connector;
    }

}
