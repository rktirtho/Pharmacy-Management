<%-- 
    Document   : allsels
    Created on : Sep 11, 2020, 12:06:05 PM
    Author     : rktirtho
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <table class="table table-striped table-bordered">
            <tr>
                <th>Invoice No.</th>
                <th>Seller Name</th>
                <th>Total</th>
                <th>Time</th>
            </tr>
            <c:forEach items="${sells}" var="p">

                <tr>
                    <td><a href="">${p.getProductName()}</a></td>
                    <td>${p.getSellerName()}</td>
                    <td>${p.getQuantity()}</td>
                    <td>${p.getPrice()}</td>
                    <td>${p.getTime()}</td>
                    
                </tr>
            </c:forEach>
        </table>
    </body>
</html>
