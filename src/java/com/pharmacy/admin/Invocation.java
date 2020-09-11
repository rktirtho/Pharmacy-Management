/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pharmacy.admin;

import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

/**
 *
 * @author rktirtho
 */

@Path("invocation")
@Produces(MediaType.APPLICATION_JSON)
public class Invocation {
    
    public int makeBill(){
        return 0;
    }
}
