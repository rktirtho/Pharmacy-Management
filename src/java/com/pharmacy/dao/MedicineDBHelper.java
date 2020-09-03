/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pharmacy.dao;

import com.pharmacy.product.Product;
import com.pharmacy.product.MedicineAuthor;
import java.sql.Connection;
import java.sql.Date;
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
public class MedicineDBHelper {

    private final String TABLE = "product";
    private final String ID = "ID";
    private final String NAME = "_name";
    private final String DESCPRITION = "_description";
    private final String GROUP = "_group"; // Peracitamole
    private final String TYPE = "_type";  // tablet, Capsule, Injection etc
    private final String UNIT_SIZE = "unit_size";
    private final String UNIT_SELLING_PRIZE = "unit_selling_prize";
    private final String UNIT_BUYING_PRIZE = "unit_buying_prize";
    private final String PROFIT_PER_UNIT = "profit_per_unit";
    private final String DISCOUNT = "_discount";
    private final String IS_AVAILABLE = "is_available";
    private final String QUANTITY = "_quantity";
    private final String LAST_UPDATE = "_last_update";
    private final String AUTHOR_ID = "author_id";
    private final String EXPIRE_DATE = "_expire_date";
    

    public static void main(String[] args) {
        MedicineDBHelper bHelper = new MedicineDBHelper();
//        Product medicine = new Product();
//        medicine.setName("Napa Extends");
//        medicine.setDescprition("not data");
//        medicine.setAuthor(2);
//        medicine.setGroup("peracitame");
//        medicine.setBatchNo("fdfdf");
//        medicine.setUnitBuyingPrize(64);
//        medicine.setUnitSellingPrize(67);
//        medicine.setUnitSize(1);
//        medicine.setType("Tablet");
//        long mills = System.currentTimeMillis();
//        Date date = new Date(mills);
//        medicine.setExpireDate(date);
//        medicine.setDiscount(5);
//        medicine.setIsAvailable(true);
//        medicine.setQuantity(700);
//
//        System.out.println(bHelper.insert(medicine));
List<Product> list = bHelper.getAll();
        for (Product product : list) {
            System.err.println(product.getName());
        }

    }

    public boolean insert(Product medicine) {
        boolean status = false;
        DBConnector connector = DBConnector.getInstance();
        Connection connection = connector.getConnection();
        PreparedStatement statement = null;
        try {
            statement = connection.prepareCall("insert into " + TABLE + " ("
                    + NAME + " ," + DESCPRITION + " ,"
                    + GROUP + " ," + TYPE + " ," + AUTHOR_ID + " ,"
                    + UNIT_SIZE + " ," + QUANTITY + " ,"
                    + UNIT_BUYING_PRIZE + " ," + UNIT_SELLING_PRIZE + " ,"
                    + PROFIT_PER_UNIT + " ," + DISCOUNT + " ,"
                    + IS_AVAILABLE + " ," + EXPIRE_DATE + ") values(?,?,?,?,?,?,?,?,?,?,?,?,?)");
            statement.setString(1, medicine.getName());
            statement.setString(2, medicine.getDescprition());
            statement.setString(3, medicine.getGroup());
            statement.setString(4, medicine.getType());
            statement.setObject(5, medicine.getAuthor());
            statement.setDouble(6, medicine.getUnitSize());
            statement.setDouble(7, medicine.getQuantity());
            statement.setDouble(8, medicine.getUnitBuyingPrize());
            statement.setDouble(9, medicine.getUnitSellingPrize());
            statement.setDouble(10, medicine.getProfitPerUnit());
            statement.setDouble(11, medicine.getDiscount());
            statement.setBoolean(12, medicine.isIsAvailable());
            statement.setDate(13, medicine.getExpireDate());
            status = statement.executeUpdate() == 1;
        } catch (SQLException ex) {
            Logger.getLogger(MedicineDBHelper.class.getName()).log(Level.SEVERE, null, ex);
        }
        return status;
    }

    public List<Product> getAll() {
        List<Product> medicines = new ArrayList<>();
        DBConnector connector = DBConnector.getInstance();
        Connection connection = connector.getConnection();
        ResultSet rs = null;
        PreparedStatement statement = null;
        try {
            statement = connection.prepareCall("SELECT * FROM "+TABLE);
                        rs = statement.executeQuery();
            while (rs.next()) {
                Product product = new Product();
                inputter(rs, product);
                medicines.add(product);
            }

        } catch (SQLException ex) {
            Logger.getLogger(MedicineDBHelper.class.getName()).log(Level.SEVERE, null, ex);
        }

        return medicines;
    }
    
    void inputter(ResultSet rs, Product product) throws SQLException{
        product.setName(rs.getString(NAME));
        product.setDescprition(rs.getString(DESCPRITION));
        product.setGroup(rs.getString(GROUP));
        product.setType(rs.getString(TYPE));
        product.setAuthor(rs.getInt(AUTHOR_ID));
//        product.setBatchNo(rs.getString());
        product.setUnitSize(rs.getDouble(UNIT_SIZE));
        product.setUnitBuyingPrize(rs.getDouble(UNIT_BUYING_PRIZE));
        product.setUnitSellingPrize(rs.getDouble(UNIT_SELLING_PRIZE));
        product.setProfitPerUnit(rs.getDouble(PROFIT_PER_UNIT));
        product.setDiscount(rs.getDouble(DISCOUNT));
        product.setQuantity(rs.getInt(QUANTITY));
        product.setIsAvailable(rs.getBoolean(IS_AVAILABLE));
        product.setLaspdate(rs.getTimestamp(LAST_UPDATE));

//    private Date expireDate;
//    private String batchNo;
 
        

    }
}
