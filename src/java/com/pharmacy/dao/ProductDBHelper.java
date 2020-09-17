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

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author rktirtho
 */
public class ProductDBHelper {

    private final String TABLE = "product";
    private final String ID = "ID";
    private final String NAME = "_name";
    private final String CODE_NUMBER = "_description";
    private final String GROUP = "_group"; // Peracitamole
    private final String TYPE = "_type";  // tablet, Capsule, Injection etc
    private final String UNIT_SIZE = "unit_size";
    private final String UNIT_SELLING_PRIZE = "unit_selling_prize";
    private final String UNIT_BUYING_PRIZE = "unit_buying_prize";
    private final String PROFIT_PER_UNIT = "profit_per_unit";
    private final String DISCOUNT = "_discount";
    private final String IS_AVAILABLE = "is_available";
    private final String IS_UPDATED = "is_updated";
    private final String QUANTITY = "_quantity";
    private final String INVENTOR = "inventor";
    private final String BATCH_NO = "batch_no";
    private final String LAST_UPDATE = "_last_update";
    private final String AUTHOR_ID = "author_id";
    private final String EXPIRE_DATE = "_expire_date";

    public static void main(String[] args) {
        ProductDBHelper bHelper = new ProductDBHelper();
//        Product medicine = new Product();
//        medicine.setName("Max Pro");
//        medicine.setDescprition("not data");
//        medicine.setAuthor("ACI");
//        medicine.setInventor("Fahim");
//        medicine.setGroup("peracitame");
//        medicine.setBatchNo("fdfdf");
//        medicine.setUnitBuyingPrize(5.0);
//        medicine.setUnitSellingPrize(6.5);
//        medicine.setUnitSize(1);
//        medicine.setType("Tablet");
//        long mills = System.currentTimeMillis();
//        Date date = new Date(mills);
//        medicine.setExpireDate("4682-345");
//        medicine.setDiscount(5);
//        medicine.setIsAvailable(true);
//        medicine.setQuantity(20);

//        System.out.println(bHelper.insert(medicine));
//System.out.println(bHelper.updateQuantity(medicine));
//        List<Product> list = bHelper.searchByKeyword("nap");
//        System.out.println(list.size());
//        for (Product product : list) {
//            System.err.println(product.getName());
//        }

        System.out.println(bHelper.updateQuantity("413212", 2));
    }

    public boolean insert(Product medicine) {
        boolean status = false;
        DBConnector connector = DBConnector.getInstance();
        Connection connection = connector.getConnection();
        PreparedStatement statement = null;
        Product product = new Product();

        try {
            statement = connection.prepareCall("insert into " + TABLE + " ("
                    + NAME + " ," + CODE_NUMBER + " ,"
                    + GROUP + " ," + TYPE + " ," + AUTHOR_ID + " ,"
                    + UNIT_SIZE + " ," + QUANTITY + " ,"
                    + UNIT_BUYING_PRIZE + " ," + UNIT_SELLING_PRIZE + " ,"
                    + PROFIT_PER_UNIT + " ," + DISCOUNT + " ,"
                    + IS_AVAILABLE + " ," + EXPIRE_DATE + ", " + BATCH_NO + ", "
                    + INVENTOR + ", " + IS_UPDATED
                    + ") values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");

//            if (isExist(medicine)) {
//                product = selectExist(medicine);
//                System.out.println(product);
//                statement.setString(1, medicine.getName());
//                statement.setString(2, medicine.getCodeNumber());
//                statement.setString(3, medicine.getGroup());
//                statement.setString(4, medicine.getType());
//                statement.setObject(5, medicine.getAuthor());
//                statement.setDouble(6, medicine.getUnitSize());
//                statement.setDouble(7, medicine.getQuantity() + product.getQuantity());
//                System.out.println(medicine.getQuantity() + product.getQuantity());
//                statement.setDouble(8,
//                        ((medicine.getUnitBuyingPrize() * medicine.getQuantity())
//                        + (product.getUnitBuyingPrize() * product.getQuantity()))
//                        / (medicine.getQuantity() + product.getQuantity())
//                );
//                statement.setDouble(9,medicine.getUnitSellingPrize());
//                statement.setDouble(10, medicine.getProfitPerUnit());
//                statement.setDouble(11, medicine.getDiscount());
//                statement.setBoolean(12, medicine.isIsAvailable());
//                statement.setString(13, medicine.getExpireDate());
//                statement.setString(14, medicine.getBatchNo());
//                statement.setString(15, medicine.getInventor());
//                statement.setBoolean(16, true);
//                System.out.println(updateQuantity(product));
//            } else {
                statement.setString(1, medicine.getName());
                statement.setString(2, medicine.getCodeNumber());
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
                statement.setString(13, medicine.getExpireDate());
                statement.setString(14, medicine.getBatchNo());
                statement.setString(15, medicine.getInventor());
                statement.setBoolean(16, true);
//            }
            status = statement.executeUpdate() == 1;
        } catch (SQLException ex) {
            Logger.getLogger(ProductDBHelper.class.getName()).log(Level.SEVERE, null, ex);
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
            statement = connection.prepareCall("SELECT * FROM " 
                    + TABLE +" WHERE "+IS_UPDATED+"=? and "+QUANTITY+">0");
            statement.setBoolean(1, true);
            rs = statement.executeQuery();
            while (rs.next()) {
                Product product = new Product();
                inputter(rs, product);
                medicines.add(product);
            }

        } catch (SQLException ex) {
            Logger.getLogger(ProductDBHelper.class.getName()).log(Level.SEVERE, null, ex);
        }

        return medicines;
    }
    
    
    public List<Product> searchByKeyword(String key){
        List<Product> products = new ArrayList<>();
        DBConnector connector = DBConnector.getInstance();
        Connection connection = connector.getConnection();
        ResultSet rs = null;
        PreparedStatement statement = null;
        
        try {
            statement = connection.prepareCall("SELECT * FROM "+TABLE
                    +" WHERE "+NAME+" like ? and "+ IS_UPDATED+"=?");
            statement.setString(1, "%"+key+"%");
            statement.setBoolean(2, true);
            
            
            rs = statement.executeQuery();
            while (rs.next()) {                
                Product product = new Product();
                inputter(rs, product);
                products.add(product);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ProductDBHelper.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return products;
    }
    
    public List<Product> searchProductCode(String key){
        List<Product> products = new ArrayList<>();
        DBConnector connector = DBConnector.getInstance();
        Connection connection = connector.getConnection();
        ResultSet rs = null;
        PreparedStatement statement = null;
        
        try {
            statement = connection.prepareCall("SELECT * FROM "+TABLE
                    +" WHERE "+CODE_NUMBER+" =? and "+ IS_UPDATED+"=? and _quantity >0");
            statement.setString(1, key);
            statement.setBoolean(2, true);
            
            
            rs = statement.executeQuery();
            while (rs.next()) {                
                Product product = new Product();
                inputter(rs, product);
                products.add(product);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ProductDBHelper.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return products;
    }

    /**
     * Return a list of Product which quantity is less then 100.
     * @return List of Product
     */
    public List<Product> getStockOut() {
        List<Product> medicines = new ArrayList<>();
        DBConnector connector = DBConnector.getInstance();
        Connection connection = connector.getConnection();
        ResultSet rs = null;
        PreparedStatement statement = null;
        try {
            statement = connection.prepareCall("SELECT * FROM "
                    + TABLE + " WHERE " + QUANTITY + "<1");
            rs = statement.executeQuery();
            while (rs.next()) {
                Product product = new Product();
                inputter(rs, product);
                medicines.add(product);
            }

        } catch (SQLException ex) {
            Logger.getLogger(ProductDBHelper.class.getName()).log(Level.SEVERE, null, ex);
        }

        return medicines;
    }

    /**
     * When product quantity is less then 100
     *
     * @return List of Product
     */
    public List<Product> needToBuy() {
        List<Product> medicines = new ArrayList<>();
        DBConnector connector = DBConnector.getInstance();
        Connection connection = connector.getConnection();
        ResultSet rs = null;
        PreparedStatement statement = null;
        try {
            statement = connection.prepareCall("SELECT * FROM "
                    + TABLE + " WHERE " + QUANTITY + "<100");
            rs = statement.executeQuery();
            while (rs.next()) {
                Product product = new Product();
                inputter(rs, product);
                medicines.add(product);
            }

        } catch (SQLException ex) {
            Logger.getLogger(ProductDBHelper.class.getName()).log(Level.SEVERE, null, ex);
        }

        return medicines;
    }

    public List<Product> getById(String code) {
        List<Product> medicines = new ArrayList<>();
        DBConnector connector = DBConnector.getInstance();
        Connection connection = connector.getConnection();
        ResultSet rs = null;
        PreparedStatement statement = null;
        try {
            statement = connection.prepareCall("SELECT * FROM " + TABLE
                    + " WHERE " + CODE_NUMBER + "=?");
            statement.setString(1, code);
            rs = statement.executeQuery();
            while (rs.next()) {
                Product product = new Product();
                inputter(rs, product);
                medicines.add(product);
            }

        } catch (SQLException ex) {
            Logger.getLogger(ProductDBHelper.class.getName()).log(Level.SEVERE, null, ex);
        }

        return medicines;
    }

    /**
     * <p>
     * <b color="red">Method has some error.</b>
     * Method query is not valid
     * </p>
     *
     * @param inventor
     * @return List of Product
     *
     */
    public List<Product> getByInventor(String inventor) {
        List<Product> medicines = new ArrayList<>();
        DBConnector connector = DBConnector.getInstance();
        Connection connection = connector.getConnection();
        ResultSet rs = null;
        PreparedStatement statement = null;
        try {
            statement = connection.prepareCall("SELECT * FROM " + TABLE
                    + " WHERE " + ID + "=?");
            statement.setString(1, inventor);
            rs = statement.executeQuery();
            while (rs.next()) {
                Product product = new Product();
                inputter(rs, product);
                medicines.add(product);
            }

        } catch (SQLException ex) {
            Logger.getLogger(ProductDBHelper.class.getName()).log(Level.SEVERE, null, ex);
        }

        return medicines;
    }

    public List<Product> getByType(String type) {
        List<Product> medicines = new ArrayList<>();
        DBConnector connector = DBConnector.getInstance();
        Connection connection = connector.getConnection();
        ResultSet rs = null;
        PreparedStatement statement = null;
        try {
            statement = connection.prepareCall("SELECT * FROM " + TABLE
                    + " WHERE " + TYPE + "=?");
            statement.setString(1, type);
            rs = statement.executeQuery();
            while (rs.next()) {
                Product product = new Product();
                inputter(rs, product);
                medicines.add(product);
            }

        } catch (SQLException ex) {
            Logger.getLogger(ProductDBHelper.class.getName()).log(Level.SEVERE, null, ex);
        }

        return medicines;
    }

    public int delete(int id) {
        int status = 0;
        DBConnector connector = DBConnector.getInstance();
        Connection connection = connector.getConnection();
        ResultSet rs = null;
        PreparedStatement statement = null;
        try {
            statement = connection.prepareCall("DELETE FROM " + TABLE + " WHERE " + ID + "=?");
            statement.executeUpdate();

            status = statement.executeUpdate();

        } catch (SQLException ex) {
            Logger.getLogger(ProductDBHelper.class.getName()).log(Level.SEVERE, null, ex);
        }

        return status;
    }

    public int updatePrize(int id, double buyingPrize, double sellingPrize) {
        int status = 0;
        DBConnector connector = DBConnector.getInstance();
        Connection connection = connector.getConnection();
        ResultSet rs = null;
        PreparedStatement statement = null;
        try {
            statement = connection.prepareCall("UPDATE " + TABLE + " SET "
                    + UNIT_BUYING_PRIZE + "=?, " + UNIT_SELLING_PRIZE + "=? WHERE " + ID + "=?");
            statement.setDouble(1, buyingPrize);
            statement.setDouble(2, sellingPrize);
            statement.setInt(3, id);
            status = statement.executeUpdate();

        } catch (SQLException ex) {
            Logger.getLogger(ProductDBHelper.class.getName()).log(Level.SEVERE, null, ex);
        }

        return status;
    }

    private Product selectExist(Product product) {
        Product product1 = new Product();
        DBConnector connector = DBConnector.getInstance();
        Connection connection = connector.getConnection();
        ResultSet rs = null;
        PreparedStatement statement = null;
        try {
            statement = connection.prepareCall("SELECT * FROM "
                    + TABLE + " WHERE "
                    + NAME + "=? and "
                    + GROUP + "=? and "
                    + AUTHOR_ID + "=? and "
                    + TYPE + "=? and "
                    + IS_UPDATED + "=? "
            );
            statement.setString(1, product.getName());
            statement.setString(2, product.getGroup());
            statement.setString(3, product.getAuthor());
            statement.setString(4, product.getType());
            statement.setBoolean(5, true);

            rs = statement.executeQuery();

            if (rs.next()) {
                inputter(rs, product1);

            }
        } catch (SQLException ex) {
            Logger.getLogger(ProductDBHelper.class.getName()).log(Level.SEVERE, null, ex);
        }
        return product1;
    }

    private int updateQuantity(Product product) {
        DBConnector connector = DBConnector.getInstance();
        Connection connection = connector.getConnection();
        PreparedStatement statement = null;
        int status =0;
        try {
            statement = connection.prepareCall("UPDATE "
                    + TABLE + " SET "+IS_UPDATED+"=? WHERE "
                    + NAME + "=? and "
                    + GROUP + "=? and "
                    + AUTHOR_ID + "=? and "
                    + TYPE + "=? and "
                    + IS_UPDATED + "=?"
            );
            statement.setBoolean(1, false);
            statement.setString(2, product.getName());
            statement.setString(3, product.getGroup());
            statement.setString(4, product.getAuthor());
            statement.setString(5, product.getType());
            statement.setBoolean(6, true);
            
            status = statement.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(ProductDBHelper.class.getName()).log(Level.SEVERE, null, ex);
        }
        return status;

    }
    
    public int updateQuantity(String productCode, int quantity) {
        DBConnector connector = DBConnector.getInstance();
        Connection connection = connector.getConnection();
        PreparedStatement statement = null;
        int status =0;
        try {
            statement = connection.prepareCall("UPDATE "
                    + TABLE + " SET "+QUANTITY+"="+QUANTITY+"-? WHERE "
                    + CODE_NUMBER + "=?"
            );
            
            statement.setInt(1, quantity);
            statement.setString(2, productCode);
       
            
            status = statement.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(ProductDBHelper.class.getName()).log(Level.SEVERE, null, ex);
        }
        return status;

    }

    private boolean isExist(Product product) {
        boolean exist = false;
        DBConnector connector = DBConnector.getInstance();
        Connection connection = connector.getConnection();
        ResultSet rs = null;
        PreparedStatement statement = null;
        try {
            statement = connection.prepareCall("SELECT * FROM "
                    + TABLE + " WHERE "
                    + NAME + "=? and "
                    + GROUP + "=? and "
                    + AUTHOR_ID + "=? and "
                    + TYPE + "=?"
            );
            statement.setString(1, product.getName());
            statement.setString(2, product.getGroup());
            statement.setString(3, product.getAuthor());
            statement.setString(4, product.getType());

            rs = statement.executeQuery();

            if (rs.next()) {
                exist = true;

            }
        } catch (SQLException ex) {
            Logger.getLogger(ProductDBHelper.class.getName()).log(Level.SEVERE, null, ex);
        }

        return exist;
    }

    void inputter(ResultSet rs, Product product) throws SQLException {
        
        product.setId(rs.getInt(ID));
        product.setName(rs.getString(NAME));
        product.setInventor(rs.getString(INVENTOR));
        product.setCodeNumber(rs.getString(CODE_NUMBER));
        product.setGroup(rs.getString(GROUP));
        product.setType(rs.getString(TYPE));
        product.setAuthor(rs.getString(AUTHOR_ID));
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
