/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pharmacy.service;

import com.pharmacy.dao.SellDbHelper;
import com.pharmacy.sells.DailySell;
import com.pharmacy.sells.SellModel;
import com.pharmacy.sells.SellView;
import java.util.List;

/**
 *
 * @author rktirtho
 */
public class SellsService {
    
    private static SellDbHelper dbHelper = new SellDbHelper();
    
    public static List<SellView> allSells(){
        return dbHelper.getAll();
    }
    
    public static int[] makeBill(List<SellModel> list){
        return dbHelper.makeSell(list);
    }
    public static List<SellView> getByInvocationId(String invocatonId){
        return dbHelper.getByInvocation(invocatonId);
    }
    
    public static List<DailySell> getDailySellInfo(){
        return dbHelper.getDailySellInfo();
    }
    public static List<DailySell> getDailySellInfoOfSeller(int id){
        return dbHelper.getDailySellInfoOfSeller(id);
    }
    
    public static List<SellView> getPerDaySell(String date){
        return dbHelper.getPerDay(date);
    }
    public static List<SellView> getPerDayBySeller(String date, int id){
        return dbHelper.getPerDayBySeller(date, id);
    }
    
     public static double totalAmmountPerDay(String date){
        return dbHelper.totalAmmountPerDay(date);
    }
     public static double totalAmmount(){
        return dbHelper.totalAmmount();
    }
    
}
