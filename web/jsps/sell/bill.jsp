<%-- 
    Document   : bill
    Created on : Sep 11, 2020, 5:32:56 PM
    Author     : rktirtho
--%>

<%@page import="com.pharmacy.service.SellsService"%>
<%@page import="com.pharmacy.sells.SellView"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <%
            String invocationNO = request.getParameter("invocation");
            List<SellView> sv = SellsService.getByInvocationId(invocationNO);
            request.setAttribute("sv", sv);

        %>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Invocation no <%=invocationNO %></title>
    </head>
    <body>
        
    <center>
        <h3><%=invocationNO %></h3>
        <table border="1">
            <tr>
                <th>Product Name</th>
                <th>Unit Price</th>
                <th>Quantity</th>
                <th>Total</th>
                <th>Time</th>
            </tr>

            <c:forEach items="${sv}" var="s">
                <tr>
                <td>${s.getProductName()}</td>
                <td>${s.getUnitPrice()}</td>
                <td>${s.getQuantity()}</td>
                <td>${s.getPrice()}</td>
                <td>${s.getTime()}</td>
            </tr>

            </c:forEach>
        </table>
    </center>
    </body>
</html>
