<%-- 
    Document   : need-to-buy
    Created on : Sep 4, 2020, 5:52:12 AM
    Author     : rktirtho
--%>

<%@page import="com.pharmacy.service.ProductService"%>
<%@page import="com.pharmacy.product.Product"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h2>Need to Purchase</h2>
        <hr>
        If product quantity is less then 100.
        <hr>
        <%
            List<Product> products = ProductService.needToBuy();
            request.setAttribute("products", products);
        %>

        <table class="table table-striped table-bordered">
            <tr>
                <th>Product Name</th>
                <th>In Stock</th>
                <th>Unit Size</th>
                <th>Buying Prize</th>
                <th>Selling Prize </th>
                <th>Discount</th>
                <th>Profit</th>
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
                    <td>${p.getProfitPerUnit()}</td>
                    <td><a href="">${p.getInventor()}</a></td>
                </tr>
            </c:forEach>
        </table>
    </body>
</html>
