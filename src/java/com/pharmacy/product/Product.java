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
    
    private int id; // not in form
    private String name;
    private String codeNumber;// not in form
    private String group; 
    private String type;  // tablet, Capsule, Injection etc
    private String author;
    private double unitSize;
    private double unitSellingPrize;
    private double unitBuyingPrize;
    private double profitPerUnit; // not in form
    private String expireDate;
    private String batchNo;
    private double discount;
    private String inventor;
    private boolean isAvailable; // not in form
    private boolean isUpdated;// not in form
    private int quantity;
    private Timestamp laspdate; // not in form

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

    public String getCodeNumber() {
        return codeNumber;
    }

    public void setCodeNumber(String descprition) {
        this.codeNumber = descprition;
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

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
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

    public String getExpireDate() {
        return expireDate;
    }

    public void setExpireDate(String expireDate) {
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

    public String getInventor() {
        return inventor;
    }

    public void setInventor(String inventor) {
        this.inventor = inventor;
    }

    public boolean isIsUpdated() {
        return isUpdated;
    }

    public void setIsUpdated(boolean isUpdated) {
        this.isUpdated = isUpdated;
    }

    @Override
    public String toString() {
        return "Product{" + "id=" + id + ", name=" + name + ", descprition=" + codeNumber + ", group=" + group + ", type=" + type + ", author=" + author + ", unitSize=" + unitSize + ", unitSellingPrize=" + unitSellingPrize + ", unitBuyingPrize=" + unitBuyingPrize + ", profitPerUnit=" + profitPerUnit + ", expireDate=" + expireDate + ", batchNo=" + batchNo + ", discount=" + discount + ", inventor=" + inventor + ", isAvailable=" + isAvailable + ", isUpdated=" + isUpdated + ", quantity=" + quantity + ", laspdate=" + laspdate + '}';
    }
    
    
    
    
    
    

    
    
}
