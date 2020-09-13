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
import java.util.ArrayList;
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
//        Admin admin = new Admin();
//        admin.setName("Rejaul Karim");
//        admin.setUserName("rktirtho");
//        admin.setAccType("admin");
//        admin.setPassword("qwert");
//        admin.setIsActive(true);

//        List<Admin> admins = AdminDbHelper.getNewRequest();
//        for (Admin admin : admins) {
//            System.out.println(admin);
//        }
        System.out.println(changePassword("qwert", "1234", 1));
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
                if (!connection.isClosed()) {
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

    public static List<Admin> getAll() {
        List<Admin> admins = new ArrayList<>();
        Connection connection = connector.getConnection();
        PreparedStatement statement = null;
        ResultSet rs = null;
        try {
            statement = connection.prepareCall("SELECT * FROM " + DBConnector.ADMIN_TABLE
                    + " WHERE " + DBConnector.IS_ACTIVE + " =?");
            statement.setBoolean(1, true);
            rs = statement.executeQuery();

            while (rs.next()) {
                Admin admin = new Admin();
                inputer(admin, rs);
                admins.add(admin);
            }
        } catch (SQLException ex) {
            Logger.getLogger(AdminDbHelper.class.getName()).log(Level.SEVERE, null, ex);
        }

        return admins;

    }

    public static List<Admin> getNewRequest() {
        List<Admin> admins = new ArrayList<>();
        Connection connection = connector.getConnection();
        PreparedStatement statement = null;
        ResultSet rs = null;
        try {
            statement = connection.prepareCall("SELECT * FROM " + DBConnector.ADMIN_TABLE
                    + " WHERE " + DBConnector.IS_ACTIVE + " =? and "
                    + DBConnector.IS_ACTIVE + "=?");
            statement.setBoolean(1, false);
            statement.setBoolean(2, false);
            rs = statement.executeQuery();

            while (rs.next()) {
                Admin admin = new Admin();
                inputer(admin, rs);
                admins.add(admin);
            }
        } catch (SQLException ex) {
            Logger.getLogger(AdminDbHelper.class.getName()).log(Level.SEVERE, null, ex);
        }

        return admins;

    }

    public static List<Admin> getDeleted() {
        List<Admin> admins = new ArrayList<>();
        Connection connection = connector.getConnection();
        PreparedStatement statement = null;
        ResultSet rs = null;
        try {
            statement = connection.prepareCall("SELECT * FROM " + DBConnector.ADMIN_TABLE
                    + " WHERE " + DBConnector.IS_ACTIVE + " =? and " + DBConnector.IS_ACTIVE + "=?");
            statement.setBoolean(1, false);
            statement.setBoolean(1, true);
            rs = statement.executeQuery();

            while (rs.next()) {
                Admin admin = new Admin();
                inputer(admin, rs);
                admins.add(admin);
            }
        } catch (SQLException ex) {
            Logger.getLogger(AdminDbHelper.class.getName()).log(Level.SEVERE, null, ex);
        }

        return admins;

    }

    public static Admin getById(int id) {
        Admin admin = new Admin();
        Connection connection = connector.getConnection();
        PreparedStatement statement = null;
        ResultSet rs = null;
        try {
            statement = connection.prepareCall(
                    "SELECT * FROM " + DBConnector.ADMIN_TABLE
                    + "  WHERE " + DBConnector.ID + " =?");
            statement.setInt(1, id);
            rs = statement.executeQuery();

            while (rs.next()) {
                inputer(admin, rs);
            }
        } catch (SQLException ex) {
            Logger.getLogger(AdminDbHelper.class.getName()).log(Level.SEVERE, null, ex);
        }

        return admin;

    }

    public Admin getByEmail(String email) {
        Admin admin = new Admin();
        Connection connection = connector.getConnection();
        PreparedStatement statement = null;
        ResultSet rs = null;
        try {
            statement = connection.prepareCall(
                    "SELECT * FROM " + DBConnector.ADMIN_TABLE
                    + "  WHERE " + DBConnector.EMAIL + " =?");
            statement.setString(1, email);
            rs = statement.executeQuery();

            while (rs.next()) {
                inputer(admin, rs);
            }
        } catch (SQLException ex) {
            Logger.getLogger(AdminDbHelper.class.getName()).log(Level.SEVERE, null, ex);
        }

        return admin;

    }

    public static int delete(int id) {
        Connection connection = connector.getConnection();
        PreparedStatement statement = null;
        int status = 0;
        try {
            statement = connection.prepareCall("DELETE FROM "
                    + DBConnector.ADMIN_TABLE + " WHERE " + DBConnector.ID + "=?");
            statement.setInt(1, id);
            status = statement.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(AdminDbHelper.class.getName()).log(Level.SEVERE, null, ex);
        }
        return status;
    }

    public static int approve(int id) {
        Connection connection = connector.getConnection();
        PreparedStatement statement = null;
        int status = 0;
        try {
            statement = connection.prepareCall("UPDATE "
                    + DBConnector.ADMIN_TABLE + " SET " + DBConnector.IS_ACTIVE
                    + "=?, " + DBConnector.IS_RESPONSED + "=? WHERE " + DBConnector.ID + "=?");
            statement.setBoolean(1, true);
            statement.setBoolean(2, true);
            statement.setInt(3, id);
            status = statement.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(AdminDbHelper.class.getName()).log(Level.SEVERE, null, ex);
        }
        return status;
    }

    public static int reject(int id) {
        Connection connection = connector.getConnection();
        PreparedStatement statement = null;
        int status = 0;
        try {
            statement = connection.prepareCall("UPDATE "
                    + DBConnector.ADMIN_TABLE + " SET " + DBConnector.IS_ACTIVE
                    + "=?, " + DBConnector.IS_RESPONSED + "=? WHERE " + DBConnector.ID + "=?");
            statement.setBoolean(1, false);
            statement.setBoolean(2, true);
            statement.setInt(3, id);
            status = statement.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(AdminDbHelper.class.getName()).log(Level.SEVERE, null, ex);
        }
        return status;
    }

    public static int changePassword(String oldPawwrord,
            String newPassword, int id) {
        Connection connection = connector.getConnection();
        PreparedStatement statement = null;
        int status = 0;

        if (getPassword(oldPawwrord, id)) {

            try {
                statement = connection.prepareCall("UPDATE "
                        + DBConnector.ADMIN_TABLE + " SET " + DBConnector.PASSWORD
                        + "=? WHERE " + DBConnector.ID + "=?");
                statement.setString(1, newPassword);
                statement.setInt(2, id);
                status = statement.executeUpdate();
            } catch (SQLException ex) {
                Logger.getLogger(AdminDbHelper.class.getName()).log(Level.SEVERE, null, ex);
            }

        }

        return status;
    }

    private static boolean getPassword(String password, int id) {
        Connection connection = connector.getConnection();
        PreparedStatement statement = null;
        ResultSet rs = null;
        boolean status = false;

        try {
            statement = connection.prepareCall("SELECT " + DBConnector.PASSWORD
                    + " FROM " + DBConnector.ADMIN_TABLE
                    + " where " + DBConnector.ID
                    + "=?");
            statement.setInt(1, id);

            rs = statement.executeQuery();
            if (rs.next()) {
                if (rs.getString(DBConnector.PASSWORD).equals(password)) {
//                    System.out.println("Mached");
                    status = true;
                }
            }

        } catch (SQLException ex) {
            Logger.getLogger(AdminDbHelper.class.getName()).log(Level.SEVERE, null, ex);
        }
        return status;
    }

    public static Admin getBySession(String sessionId) {
        Admin admin = new Admin();
        Connection connection = connector.getConnection();
        PreparedStatement statement = null;
        ResultSet rs = null;
        try {
            statement = connection.prepareCall(
                    "SELECT * FROM " + DBConnector.ADMIN_TABLE
                    + "  WHERE " + DBConnector.SESSION + " =?");
            statement.setString(1, sessionId);
            rs = statement.executeQuery();

            while (rs.next()) {
                inputer(admin, rs);
            }
        } catch (SQLException ex) {
            Logger.getLogger(AdminDbHelper.class.getName()).log(Level.SEVERE, null, ex);
        }

        return admin;

    }

    private static void inputer(Admin admin, ResultSet rs) {
        try {
            admin.setId(rs.getInt(DBConnector.ID));
            admin.setName(rs.getString(DBConnector.NAME));
            admin.setUserName(rs.getString(DBConnector.EMAIL));
            admin.setAccType(rs.getString(DBConnector.ACC_TYPE));
            admin.setTimestamp(rs.getTimestamp(DBConnector.REG_TIME));
            admin.setIsActive(rs.getBoolean(DBConnector.IS_ACTIVE));
        } catch (SQLException ex) {
            Logger.getLogger(AdminDbHelper.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
}
