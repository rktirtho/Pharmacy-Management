/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pharmacy.sells;

import java.sql.Timestamp;

/**
 *
 * @author rktirtho
 */
public class DailySell {
    private int numberOfInvoice;
    private Timestamp timestamp;
    private int totalSell;
    private double ammount;

    public int getNumberOfInvoice() {
        return numberOfInvoice;
    }

    public void setNumberOfInvoice(int numberOfInvoice) {
        this.numberOfInvoice = numberOfInvoice;
    }

    public Timestamp getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Timestamp timestamp) {
        this.timestamp = timestamp;
    }

    public int getTotalSell() {
        return totalSell;
    }

    public void setTotalSell(int totalSell) {
        this.totalSell = totalSell;
    }

    public double getAmmount() {
        return ammount;
    }

    public void setAmmount(double ammount) {
        this.ammount = ammount;
    }

    @Override
    public String toString() {
        return "DailySell{" + "numberOfInvoice=" + numberOfInvoice + ", timestamp=" + timestamp + ", totalSell=" + totalSell + ", ammount=" + ammount + '}';
    }
    

   
}
