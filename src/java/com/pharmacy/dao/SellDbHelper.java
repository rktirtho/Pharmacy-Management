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
    public String SELLER_ID = "seller_id";
    public String QUANTITY = "quantity";
    public String PRICE = "price";
    public String TIMESTAMP = "sell_time";

    public static void main(String[] args) {
        SellDbHelper helper = new SellDbHelper();
        SellModel sm = new SellModel();
        sm.setProductId(23);
        sm.setSellerId(1);
        sm.setQuantity(10);
        sm.setPrice(10);
        System.out.println(helper.makeSell(sm));
    }

    public int makeSell(SellModel sm) {
        int status = 0;
        DBConnector connector = DBConnector.getInstance();
        Connection connection = connector.getConnection();
        PreparedStatement statement = null;

        try {
            statement = connection.prepareCall("INSERT INTO " + TABLE
                    + "("
                    + PRODUCT_ID + ", "
                    + SELLER_ID + ", "
                    + QUANTITY + ", "
                    + PRICE + ") values (?,?,?,?)"
            );
            statement.setInt(1, sm.getProductId());
            statement.setInt(2, sm.getSellerId());
            statement.setFloat(3, sm.getQuantity());
            statement.setDouble(4, sm.getPrice());
            status = statement.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(SellDbHelper.class.getName()).log(Level.SEVERE, null, ex);
        }
        return status;
    }

    public List<SellView> getAll() {
        List<SellView> sells = new ArrayList<>();

        return sells;
    }

    public List<SellView> getBySeller(int id) {
        List<SellView> sells = new ArrayList<>();

        return sells;
    }

    public List<SellView> getPerDay() {
        List<SellView> sells = new ArrayList<>();

        return sells;
    }

    public List<SellView> getPerDayBySeller() {
        List<SellView> sells = new ArrayList<>();

        return sells;
    }
    
     public List<SellView> getById(){
        List<SellView> sells = new ArrayList<>();

        return sells;
    }
     
     void inputter(ResultSet rs, SellView sm) throws SQLException{
         sm.setId(rs.getInt(ID));
         sm.setProductId(rs.getInt(PRODUCT_ID));
         sm.setSellerId(rs.getInt(SELLER_ID));
         sm.setProductName(rs.getString());
         sm.setSellerName(rs.getString(TABLE));
         sm.setQuantity(rs.getFloat(TABLE));
         sm.setPrice(rs.getDouble(TABLE));
         sm.setTime(rs.getTimestamp(TABLE));
     }
}
