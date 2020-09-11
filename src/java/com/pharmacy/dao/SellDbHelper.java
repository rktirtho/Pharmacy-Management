/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pharmacy.dao;

import com.pharmacy.product.Product;
import com.pharmacy.sells.SellModel;
import com.pharmacy.sells.SellView;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author rktirtho
 */
public class SellDbHelper {

    public String TABLE = "sell";
    public String ID = "id";
    public String PRODUCT_ID = "product_id";
    public String PRODUCT_NAME = "product_name";
    public String SELLER_ID = "seller_id";
    public String SELLER_NAME = "seller_name";
    public String QUANTITY = "quantity";
    public String INVOICE_NO = "invoice_no";
    public String UNIT_PRICE = "unit_selling_prize";
    public String PRICE = "price";
    public String TIMESTAMP = "sell_time";

    public static void main(String[] args) {
        SellDbHelper helper = new SellDbHelper();
//        SellModel sm = new SellModel();
//        sm.setProductId(23);
//        sm.setSellerId(1);
//        sm.setQuantity(10);
//        sm.setPrice(10);
//        System.out.println(helper.makeSell(sm));
        List<SellView> svs = helper.getAll();
        for (SellView sv : svs) {
            System.out.println(sv);
        }

//        System.out.println(Calendar.getInstance().getTimeInMillis());
    }

    public int[] makeSell(List<SellModel> items) {
        int[] status = new int[items.size()];
        DBConnector connector = DBConnector.getInstance();
        Connection connection = connector.getConnection();
        PreparedStatement statement = null;
        int count = 0;
        for (SellModel sm : items) {
            try {
                statement = connection.prepareCall("INSERT INTO " + TABLE
                        + "("
                        + PRODUCT_ID + ", "
                        + SELLER_ID + ", "
                        + QUANTITY + ", "
                        + INVOICE_NO + ", "
                        + PRICE + ") values (?,?,?,?,?)"
                );
                statement.setString(1, sm.getProductId());
                statement.setInt(2, sm.getSellerId());
                statement.setFloat(3, sm.getQuantity());
                statement.setString(4, sm.getInvoiceNo());
                statement.setDouble(5, sm.getPrice());
                status[count] = statement.executeUpdate();
                count++;
            } catch (SQLException ex) {
                Logger.getLogger(SellDbHelper.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        return status;
    }

    public List<SellView> getAll() {
        List<SellView> sells = new ArrayList<>();
        DBConnector connector = DBConnector.getInstance();
        Connection connection = connector.getConnection();
        PreparedStatement statement = null;
        ResultSet rs = null;
        try {
            statement = connection.prepareCall("select sell.invoice_no, sell.seller_id,  sum(sell.price) as price, admin_table.name "
                    + " as seller_name, sell_time from sell inner join admin_table where "
                    + " sell.seller_id = admin_table.id group by sell.invoice_no");
            rs = statement.executeQuery();
            while (rs.next()) {
                SellView sv = new SellView();
                sv.setInvoiceNo(rs.getString(INVOICE_NO));
                sv.setSellerName(rs.getString(SELLER_NAME));
                sv.setSellerId(rs.getInt(SELLER_ID));
                sv.setPrice(rs.getDouble(PRICE));
                sv.setTime(rs.getTimestamp(TIMESTAMP));
                sells.add(sv);
            }

        } catch (SQLException ex) {
            Logger.getLogger(SellDbHelper.class.getName()).log(Level.SEVERE, null, ex);
        }

        return sells;
    }

    public List<SellView> getBySeller(int id) {
        List<SellView> sells = new ArrayList<>();
        DBConnector connector = DBConnector.getInstance();
        Connection connection = connector.getConnection();
        PreparedStatement statement = null;
        ResultSet rs = null;
        try {
            statement = connection.prepareCall("select sell.id, sell.product_id,"
                    + " sell.seller_id, product._name as product_name, admin_table.name"
                    + " as seller_name, sell.quantity, sell.price, sell.sell_time "
                    + "from sell inner join product inner join admin_table where  "
                    + "sell.product_id = product.id and sell.seller_id = admin_table.id and sell.seller_id=?");
            statement.setInt(1, id);
            rs = statement.executeQuery();
            while (rs.next()) {
                SellView sv = new SellView();
                inputter(rs, sv);
                sells.add(sv);
            }

        } catch (SQLException ex) {
            Logger.getLogger(SellDbHelper.class.getName()).log(Level.SEVERE, null, ex);
        }

        return sells;
    }

    public List<SellView> getPerDay(Timestamp date) {
        List<SellView> sells = new ArrayList<>();
        DBConnector connector = DBConnector.getInstance();
        Connection connection = connector.getConnection();
        PreparedStatement statement = null;
        ResultSet rs = null;
        try {
            statement = connection.prepareCall("select sell.id, sell.product_id,"
                    + " sell.seller_id, product._name as product_name, admin_table.name"
                    + " as seller_name, sell.quantity, sell.price, sell.sell_time "
                    + "from sell inner join product inner join admin_table where  "
                    + "sell.product_id = product.id and sell.seller_id = admin_table.id and sell.sell.sell_time=?");
            statement.setTimestamp(1, date);
            rs = statement.executeQuery();
            while (rs.next()) {
                SellView sv = new SellView();
                inputter(rs, sv);
                sells.add(sv);
            }

        } catch (SQLException ex) {
            Logger.getLogger(SellDbHelper.class.getName()).log(Level.SEVERE, null, ex);
        }

        return sells;
    }

    public List<SellView> getPerDayBySeller() {
        List<SellView> sells = new ArrayList<>();

        return sells;
    }

    public List<SellView> getByInvocation(String invocationID) {
        List<SellView> sells = new ArrayList<>();
        DBConnector connector = DBConnector.getInstance();
        Connection connection = connector.getConnection();
        PreparedStatement statement = null;
        ResultSet rs = null;
        try {
            statement = connection.prepareCall("select sell.id, sell.invoice_no, sell.product_id,"
                    + " sell.seller_id, product._name as product_name, product.unit_selling_prize, admin_table.name"
                    + " as seller_name, sell.quantity, sell.price, sell.sell_time "
                    + "from sell inner join product inner join admin_table where  "
                    + "sell.product_id =  product._description and sell.seller_id = admin_table.id"
                    + " and sell.invoice_no =?");
            statement.setString(1, invocationID);
            rs = statement.executeQuery();
            while (rs.next()) {
                SellView sv = new SellView();
                inputter(rs, sv);
                sells.add(sv);
            }

        } catch (SQLException ex) {
            Logger.getLogger(SellDbHelper.class.getName()).log(Level.SEVERE, null, ex);
        }

        return sells;
    }

    void inputter(ResultSet rs, SellView sm) throws SQLException {
        sm.setId(rs.getInt(ID));
        sm.setProductId(rs.getInt(PRODUCT_ID));
        sm.setSellerId(rs.getInt(SELLER_ID));
        sm.setProductName(rs.getString(PRODUCT_NAME));
        sm.setSellerName(rs.getString(SELLER_NAME));
        sm.setQuantity(rs.getFloat(QUANTITY));
        sm.setPrice(rs.getDouble(PRICE));
        sm.setUnitPrice(rs.getDouble(UNIT_PRICE));
        sm.setTime(rs.getTimestamp(TIMESTAMP));
    }
}
