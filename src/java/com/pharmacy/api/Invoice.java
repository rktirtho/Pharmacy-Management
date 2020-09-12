/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pharmacy.api;

import com.pharmacy.admin.Admin;
import com.pharmacy.dao.AdminDbHelper;
import com.pharmacy.sells.SellModel;
import com.pharmacy.service.SellsService;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Random;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;

/**
 *
 * @author rktirtho
 */
@Path("invoice")
@Produces(MediaType.APPLICATION_JSON)
public class Invoice {

    @POST
    @Path("create-bill")
    public String makeBill(
            @QueryParam("data")String data,
            @Context HttpServletRequest request
            ) {
        HttpSession session = request.getSession();
        Admin  admin = AdminDbHelper.getBySession(session.getId());
        int [] status=null;
        List<SellModel> sms= new ArrayList<>();
        String invoice = ""+Calendar.getInstance().getTimeInMillis();
        String[] ids = data.split(",");
        System.out.println(ids);
        for (int i = 0; i < ids.length-1; i+=3) {
            SellModel sm = new SellModel();
            sm.setProductId(ids[i]);
            sm.setQuantity(Integer.parseInt(ids[i+1]));
            sm.setSellerId(admin.getId());
            sm.setInvoiceNo(invoice);
            sm.setPrice(Double.parseDouble(ids[i+2]));
            sms.add(sm);
            
            
//            System.out.println("Productids id " +ids[i]+"\tQuantity "+ids[i+1]);
        }
        status = SellsService.makeBill(sms);
        
        return invoice;
    }
}
