/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pharmacy.dao;

import com.pharmacy.admin.Admin;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author rktirtho
 */
public class AdminDbHelper {

    private static DBConnector connector = DBConnector.getInstance();
    

    public static void main(String[] args) {
        Admin admin = new Admin();
        admin.setName("Rejaul Karim");
        admin.setUserName("rktirtho");
        admin.setAccType("admin");
        admin.setPassword("qwert");
        admin.setIsActive(true);

//        registration(admin);
        int st = login("rktirtho", "qwert", "tuer");
        System.out.println(st);
    }

    public static int login(String userName, String password, String session) {
        int status = 0;
        Connection connection = connector.getConnection();
        PreparedStatement statement = null;
        ResultSet rs = null;

        try {
            String command = "select id from "
                    + DBConnector.ADMIN_TABLE + " where "
                    + DBConnector.EMAIL + "=? and "
                    + DBConnector.PASSWORD + "=? and "
                    + DBConnector.IS_ACTIVE + "=1";
            statement = connection.prepareStatement(command);

            statement.setString(1, userName);
            statement.setString(2, password);
//			statement.setBoolean(3, true);
            rs = statement.executeQuery();

            if (rs.next()) {
                status = 1;
                statement = connection.prepareCall(
                        "UPDATE " + DBConnector.ADMIN_TABLE + " SET "
                        + DBConnector.SESSION + " = ? WHERE id = " + rs.getInt("id"));
                statement.setString(1, session);
                status = statement.executeUpdate();
            }

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());

        } finally {
            try {
                if (!connection.isClosed()){
                    connection.close();
                }
                
                statement.close();
                rs.close();
            } catch (SQLException ex) {
                System.out.println(ex.getMessage());
            }
        }
        return status;
    }

    public static int registration(Admin admin) {
        int status = 0;

        Connection connection = connector.getConnection();
        PreparedStatement statement;
        try {
            statement = connection.prepareCall("insert into " + DBConnector.ADMIN_TABLE + " ("
                    + DBConnector.NAME + " ,"
                    + DBConnector.EMAIL + " ,"
                    + DBConnector.PASSWORD + " ,"
                    + DBConnector.ACC_TYPE + " ,"
                    + DBConnector.IS_ACTIVE + ") values(?,?,?,?,?)");
            statement.setString(1, admin.getName());
            statement.setString(2, admin.getUserName());
            statement.setString(3, admin.getPassword());
            statement.setString(4, admin.getAccType());
            statement.setBoolean(5, admin.isIsActive());

            status = statement.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(AdminDbHelper.class.getName()).log(Level.SEVERE, null, ex);
            System.err.println(ex.getMessage());
        }
        return status;
    }

    public List<Admin> getAllAdmin() {
        return null;
    }

    public Admin getById() {
        return null;
    }

    public Admin getByEmail() {
        return null;
    }
}
