/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pharmacy.admin;

import com.pharmacy.sells.SellModel;
import com.pharmacy.service.SellsService;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Random;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
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
    public List<SellModel> makeBill(@QueryParam("data")String data) {
        List<SellModel> sms= new ArrayList<>();
        String invoice = ""+Calendar.getInstance().getTimeInMillis();
        String[] ids = data.split(",");
        for (int i = 0; i < ids.length-1; i+=2) {
            SellModel sm = new SellModel();
            sm.setProductId(ids[i]);
            sm.setQuantity(Integer.parseInt(ids[i+1]));
            sm.setSellerId(1);
            sm.setInvoiceNo(invoice);
            sm.setPrice(new Random().nextDouble()%300);
            sms.add(sm);
            int [] status = SellsService.makeBill(sms);
            
//            System.out.println("Productids id " +ids[i]+"\tQuantity "+ids[i+1]);
        }
        
        return sms;
    }
}
