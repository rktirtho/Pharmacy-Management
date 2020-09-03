/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pharmacy.dao;

import com.pharmacy.product.Medicine;
import com.pharmacy.product.MedicineAuthor;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;
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
    public  final String TABLE_CREATE = "CREATE TABLE IF NOT EXISTS "+TABLE+"( "
                    + ID + " int(20) NOT NULL AUTO_INCREMENT PRIMARY KEY, "
                    + NAME+ " varchar(255) NOT NULL, "
                    + GROUP+ " varchar(255) NOT NULL, "
                    + DESCPRITION+ " text(50), "
                    + TYPE+" varchar(50), "
                    + UNIT_SIZE+" float(50) NOT NULL, "
                    + UNIT_BUYING_PRIZE+" double(50) NOT NULL, "
                    + UNIT_SELLING_PRIZE+" double(50) NOT NULL, "
                    + PROFIT_PER_UNIT+" double(50) NOT NULL, "
                    + DISCOUNT+" float(50) NOT NULL, "
                    + QUANTITY+" int(50) NOT NULL, "
                    + AUTHOR_ID+" int(50) NOT NULL, "
                    + IS_AVAILABLE +" boolean(50) NOT NULL, "
                    + EXPIRE_DATE +" DATE NOT NULL, "
                    + LAST_UPDATE+" timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP"
                    + ")";
    
    public static void main(String[] args) {
        MedicineDBHelper bHelper = new MedicineDBHelper();
        Medicine medicine = new Medicine();
        medicine.setName("Napa Extends");
        medicine.setDescprition("not data");
        medicine.setAuthor(2);
        medicine.setGroup("peracitame");
        medicine.setBatchNo("fdfdf");
        medicine.setUnitBuyingPrize(64);
        medicine.setUnitSellingPrize(67);
        medicine.setUnitSize(1);
        medicine.setType("Tablet");
        long mills = System.currentTimeMillis();
        Date date = new Date(mills);
        medicine.setExpireDate(date);
        medicine.setDiscount(5);
        medicine.setIsAvailable(true);
        medicine.setQuantity(700);
        
        System.out.println(bHelper.insert(medicine));
        
        
    }
    
    public boolean insert(Medicine medicine){
        boolean status= false;
        DBConnector connector= DBConnector.getInstance();
        Connection connection = connector.getConnection();
        PreparedStatement statement = null;
        try {
            statement = connection.prepareCall("insert into " + TABLE + " ("
                    + NAME + " ,"+ DESCPRITION + " ,"
                    + GROUP + " ,"+ TYPE + " ,"+ AUTHOR_ID + " ,"
                    + UNIT_SIZE + " ,"+ QUANTITY + " ,"
                    + UNIT_BUYING_PRIZE + " ,"+ UNIT_SELLING_PRIZE + " ,"
                    + PROFIT_PER_UNIT + " ,"+ DISCOUNT + " ,"
                    + IS_AVAILABLE + " ,"+ EXPIRE_DATE + ") values(?,?,?,?,?,?,?,?,?,?,?,?,?)");
            statement.setString(1,medicine.getName());
            statement.setString(2,medicine.getDescprition());
            statement.setString(3,medicine.getGroup());
            statement.setString(4,medicine.getType());
            statement.setObject(5,medicine.getAuthor());
            statement.setDouble(6,medicine.getUnitSize());
            statement.setDouble(7,medicine.getQuantity());
            statement.setDouble(8,medicine.getUnitBuyingPrize());
            statement.setDouble(9,medicine.getUnitSellingPrize());
            statement.setDouble(10,medicine.getProfitPerUnit());
            statement.setDouble(11,medicine.getDiscount());
            statement.setBoolean(12,medicine.isIsAvailable());
            statement.setDate(13,medicine.getExpireDate());
            status = statement.executeUpdate()==1;
        } catch (SQLException ex) {
            Logger.getLogger(MedicineDBHelper.class.getName()).log(Level.SEVERE, null, ex);
        }
        return status;
    }
}
