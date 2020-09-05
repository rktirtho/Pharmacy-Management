/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pharmacy.dao;

import com.pharmacy.product.Product;
import com.pharmacy.sells.SellModel;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author rktirtho
 */
public class SellDbHelper {
    
    public String ID ="";
    public String PRODUCT_ID="";
    public String SELLER_ID="";
    public String QUANTITY="";
    public String PRICE="";
    public String TIMESTAMP="";

    
    public static void main(String[] args) {
        
    }
    
    public int makeSell(SellModel sm){
        int status =0;
        DBConnector connector = DBConnector.getInstance();
        Connection connection = connector.getConnection();
        PreparedStatement statement = null;
        
        try {
            statement = connection.prepareCall("INSERT INTO "+""
            );
        } catch (SQLException ex) {
            Logger.getLogger(SellDbHelper.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
        return status;
    }
    
}
