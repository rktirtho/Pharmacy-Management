<%-- 
    Document   : AllProduct
    Created on : Sep 3, 2020, 1:38:42 PM
    Author     : rktirtho
--%>

<%@page import="com.pharmacy.service.ProductService"%>
<%@page import="java.util.List"%>
<%@page import="com.pharmacy.product.Product"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

    </head>
    <body>
        <%
            List<Product> products = ProductService.allProduct();
            request.setAttribute("products", products);
        %>
        <h2>Product List</h2>
        <hr>

        <table class="table table-striped table-bordered">
            <tr>
                <th>Product Name</th>
                <th>In Stock</th>
                <th>Unit Size</th>
                <th>Buying Prize</th>
                <th>Selling Prize </th>
                <th>Discount</th>
                <!--<th>Profit</th>-->
                <th>Inventor</th>
                <th></th>
            </tr>
            <c:forEach items="${products}" var="p">

                <tr>
                    <td><a href="">${p.getName()}</a></td>
                    <td>${p.getQuantity()}</td>
                    <td>${p.getUnitSize()}</td>
                    <td>${p.getUnitBuyingPrize()}</td>
                    <td>${p.getUnitSellingPrize()}</td>
                    <td>${p.getDiscount()}</td>
                    
                    <td><a href="">${p.getInventor()}</a></td>
                </tr>
            </c:forEach>
        </table>



    </body>
</html>
