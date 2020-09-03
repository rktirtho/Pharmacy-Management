/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pharmacy.product;

import java.sql.Date;
import java.sql.Timestamp;

/**
 *
 * @author rktirtho
 */
public class Product {
    
    private int id;
    private String name;
    private String descprition;
    private String group; // Peracitamole
    private String type;  // tablet, Capsule, Injection etc
    private int author;
    private double unitSize;
    private double unitSellingPrize;
    private double unitBuyingPrize;
    private double profitPerUnit;
    private Date expireDate;
    private String batchNo;
    private double discount;
    private boolean isAvailable;
    private int quantity;
    private Timestamp laspdate;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescprition() {
        return descprition;
    }

    public void setDescprition(String descprition) {
        this.descprition = descprition;
    }

    public String getGroup() {
        return group;
    }

    public void setGroup(String group) {
        this.group = group;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getAuthor() {
        return author;
    }

    public void setAuthor(int author) {
        this.author = author;
    }

    public double getUnitSize() {
        return unitSize;
    }

    public void setUnitSize(double unitSize) {
        this.unitSize = unitSize;
    }

    public double getUnitSellingPrize() {
        return unitSellingPrize;
    }

    public void setUnitSellingPrize(double unitSellingPrize) {
        this.unitSellingPrize = unitSellingPrize;
    }

    public double getUnitBuyingPrize() {
        return unitBuyingPrize;
    }

    public void setUnitBuyingPrize(double unitBuyingPrize) {
        this.unitBuyingPrize = unitBuyingPrize;
    }

    public double getProfitPerUnit() {
        return profitPerUnit;
    }

    public void setProfitPerUnit(double profitPerUnit) {
        this.profitPerUnit = profitPerUnit;
    }

    public Date getExpireDate() {
        return expireDate;
    }

    public void setExpireDate(Date expireDate) {
        this.expireDate = expireDate;
    }

    public String getBatchNo() {
        return batchNo;
    }

    public void setBatchNo(String batchNo) {
        this.batchNo = batchNo;
    }

    public double getDiscount() {
        return discount;
    }

    public void setDiscount(double discount) {
        this.discount = discount;
    }

    public boolean isIsAvailable() {
        return isAvailable;
    }

    public void setIsAvailable(boolean isAvailable) {
        this.isAvailable = isAvailable;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public Timestamp getLaspdate() {
        return laspdate;
    }

    public void setLaspdate(Timestamp laspdate) {
        this.laspdate = laspdate;
    }

    public void setExpireDate(String string) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    
    
    

    
    
}
