/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pharmacy.admin;

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
    public String makeBill(@QueryParam("data")String data ) {
        String[] ids = data.split(",");
        for (int i = 0; i < ids.length-1; i+=2) {
            System.out.println("Productids id " +ids[i]+"\tQuantity "+ids[i+1]);
        }
        return data;
    }
}
