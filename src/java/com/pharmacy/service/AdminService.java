/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pharmacy.service;

import com.pharmacy.admin.Admin;
import com.pharmacy.dao.AdminDbHelper;
import java.util.List;

/**
 *
 * @author rktirtho
 */
public class AdminService {
    
    public static void main(String[] args) {
    
        List<Admin> admins = AdminDbHelper.getAll();
        for (Admin admin : admins) {
            System.out.println(admin.getName());
        }
    }
    
}
