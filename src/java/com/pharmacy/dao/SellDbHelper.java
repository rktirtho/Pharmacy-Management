/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pharmacy.dao;

import com.pharmacy.product.Product;
import com.pharmacy.sells.DailySell;
import com.pharmacy.sells.SellModel;
import com.pharmacy.sells.SellView;
import com.pharmacy.service.ProductService;
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
        List<DailySell> svs = helper.getDailySellInfoOfSeller(1);
        for (DailySell sv : svs) {
            System.out.println(sv);
        }

//        System.out.println(helper.totalAmmountPerDay("2020-09-11"));
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
                if (status[count] == 1) {
                    ProductService.updateQuantity(sm.getProductId(), (int) sm.getQuantity());
                }
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
                    + " sell.seller_id = admin_table.id group by sell.invoice_no order by sell.sell_time desc");
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
    
    public List<DailySell> getDailySellInfo() {
        List<DailySell> sells = new ArrayList<>();
        DBConnector connector = DBConnector.getInstance();
        Connection connection = connector.getConnection();
        PreparedStatement statement = null;
        ResultSet rs = null;
        
        try {
            statement = connection.prepareCall("select  count(distinct invoice_no)"
                    + " as number_of_invoice,  sell_time, date(from_unixtime(sell_time))"
                    + " as _date, sum(price) as total, count(invoice_no ) as productSell  from sell "
                    + "group by date(sell_time) order by sell_time desc;");
            
            rs = statement.executeQuery();
            while (rs.next()) {
                DailySell dailySell = new DailySell();
                dailySell.setTotalSell(rs.getInt("productSell"));
                dailySell.setAmmount(rs.getDouble("total"));
                dailySell.setNumberOfInvoice(rs.getInt("number_of_invoice"));
                dailySell.setTimestamp(rs.getTimestamp("sell_time"));
                sells.add(dailySell);
                
            }
        } catch (SQLException ex) {
            Logger.getLogger(SellDbHelper.class.getName())
                    .log(Level.SEVERE, null, ex);
        }
        
        return sells;
    }
    
    public List<SellView> getPerDay(String date) {
        List<SellView> sells = new ArrayList<>();
        DBConnector connector = DBConnector.getInstance();
        Connection connection = connector.getConnection();
        PreparedStatement statement = null;
        ResultSet rs = null;
        try {
            statement = connection.prepareCall("select sell.invoice_no, sell.seller_id,  sum(sell.price) as price, admin_table.name "
                    + "                     as seller_name, sell_time from sell inner join admin_table where "
                    + "                     sell.seller_id = admin_table.id and sell.sell_time like ? group by sell.invoice_no order by sell.sell_time desc");
            statement.setString(1, date + "%");
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
    
    public List<SellView> getPerDayBySeller(String date, int id) {
        List<SellView> sells = new ArrayList<>();
        DBConnector connector = DBConnector.getInstance();
        Connection connection = connector.getConnection();
        PreparedStatement statement = null;
        ResultSet rs = null;
        try {
            statement = connection.prepareCall("select sell.invoice_no, sell.seller_id,  sum(sell.price) as price, admin_table.name "
                    + "                     as seller_name, sell_time from sell inner join admin_table where "
                    + "                     sell.seller_id = admin_table.id and sell.sell_time like ? and seller_id=? group by sell.invoice_no order by sell.sell_time desc");
            statement.setString(1, date + "%");
            statement.setInt(2, id);
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
    
    public double totalAmmountPerDay(String date) {
        double total = 0;
        DBConnector connector = DBConnector.getInstance();
        Connection connection = connector.getConnection();
        PreparedStatement statement = null;
        ResultSet rs = null;
        
        try {
            statement = connection.prepareCall("select sum(price) as total from sell where sell_time like ?");
            statement.setString(1, date + "%");
            rs = statement.executeQuery();
            if (rs.next()) {
                total = rs.getDouble("total");
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(SellDbHelper.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return total;
    }
    
    public double totalAmmount() {
        double total = 0;
        DBConnector connector = DBConnector.getInstance();
        Connection connection = connector.getConnection();
        PreparedStatement statement = null;
        ResultSet rs = null;
        
        try {
            statement = connection.prepareCall("select sum(price) as total from sell");
            
            rs = statement.executeQuery();
            if (rs.next()) {
                total = rs.getDouble("total");
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(SellDbHelper.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return total;
    }
    
    public List<DailySell> getDailySellInfoOfSeller(int id) {
        List<DailySell> sells = new ArrayList<>();
        DBConnector connector = DBConnector.getInstance();
        Connection connection = connector.getConnection();
        PreparedStatement statement = null;
        ResultSet rs = null;
        System.out.println("id "+id);
        try {
            statement = connection.prepareCall("select  count(distinct invoice_no) as number_of_invoice,"
                    + "  sell_time, date(from_unixtime(sell_time)) "
                    + " as _date,  sum(price) as total, count(invoice_no ) as productSell  from sell  where seller_id=? "
                    + " group by date(sell_time) order by sell_time desc ;");
            statement.setInt(1, id);
            rs = statement.executeQuery();
            while (rs.next()) {
                DailySell dailySell = new DailySell();
                dailySell.setTotalSell(rs.getInt("productSell"));
                dailySell.setAmmount(rs.getDouble("total"));
                dailySell.setNumberOfInvoice(rs.getInt("number_of_invoice"));
                dailySell.setTimestamp(rs.getTimestamp("sell_time"));
                sells.add(dailySell);
                
            }
        } catch (SQLException ex) {
            Logger.getLogger(SellDbHelper.class.getName())
                    .log(Level.SEVERE, null, ex);
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
