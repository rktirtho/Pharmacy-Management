<%-- 
    Document   : all-sell
    Created on : Sep 5, 2020, 10:21:57 PM
    Author     : rktirtho
--%>

<%@page import="com.pharmacy.service.SellsService"%>
<%@page import="com.pharmacy.sells.SellView"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>All Sells</title>
    </head>
    <body>
        <%
            List<SellView> sells = SellsService.allSells();
            request.setAttribute("sells", sells);
        %>
        <h2>Product List</h2>
        <hr>

        <table class="table table-striped table-bordered">
            <tr>
                <th>Invoice No</th>
                <th>Seller Name</th>
                <th>Total</th>
                <th>Time</th>
            </tr>
            <c:forEach items="${sells}" var="p">

                <tr>
                    <td><a target="_blank" href="bill?invocation=${p.getInvoiceNo()}">${p.getInvoiceNo()}</a></td>
                    <td>${p.getSellerName()}</td>
                    <td>${p.getPrice()}</td>
                    <td>${p.getTime()}</td>
                    
                </tr>
            </c:forEach>
        </table>
    </body>
</html>
