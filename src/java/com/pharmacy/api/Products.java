/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pharmacy.api;

import com.pharmacy.product.Product;
import com.pharmacy.service.ProductService;
import java.util.List;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

/**
 *
 * @author rktirtho
 */
@Path("products")
@Produces(MediaType.APPLICATION_JSON)
public class Products {
    
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Product> getIt(){
        return ProductService.allProduct();
    }
    
    @Path("search")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Product> search(@QueryParam("key")String key){
        
        return ProductService.getByCode(key);
    }
    
}
