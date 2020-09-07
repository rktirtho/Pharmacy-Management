/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pharmacy.service;

import com.pharmacy.dao.SellDbHelper;
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
    
}