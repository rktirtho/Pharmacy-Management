/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pharmacy.service;

import com.pharmacy.dao.MedicineDBHelper;
import com.pharmacy.product.Product;
import java.util.List;

/**
 *
 * @author rktirtho
 */
public class ProductService {
    
    public static void main(String[] args) {
        
    }
    
    private static MedicineDBHelper dbHelper = new MedicineDBHelper();
    public static List<Product> allProduct(){
        
        return dbHelper.getAll();
    }
    
    
}