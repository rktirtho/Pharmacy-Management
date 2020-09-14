<%-- 
    Document   : daily-sell
    Created on : Sep 12, 2020, 3:49:17 PM
    Author     : rktirtho
--%>

<%@page import="java.util.Calendar"%>
<%@page import="com.pharmacy.service.SellsService"%>
<%@page import="java.util.List"%>
<%@page import="com.pharmacy.sells.DailySell"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
    <head>
        <%
            List<DailySell> sells = SellsService.getDailySellInfo();
            request.setAttribute("sells", sells);
//            sells.get(0).getTimestamp().getDay();
           

        %>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Daily Sells</title>
    </head>
    <body>
        
        <h2>Daily Sell</h2>
        <hr>

        <table class="table table-striped table-bordered">
            <tr>
                
                <th>Date</th>
                <th>Total Invoice</th>
                <th>Total Product Sell</th>
                <th>Sell Ammount</th>
                <th></th>
            </tr>
            <c:forEach items="${sells}" var="p">

                <tr>
                    <!--<td><a target="_blank" href="bill?invocation=</a></td>-->
                    <td>
                        ${p.getTimestamp().getDate()} -
                        ${p.getTimestamp().getMonth()+1} -
                        ${1900+p.getTimestamp().getYear()}
                    </td>
                    <td>${p.getNumberOfInvoice()}</td>
                    <td>${p.getTotalSell()}</td>
                    <td>${p.getAmmount()}</td>
                    <td><a class="btn btn-success" target="_blank"
                           href="sell-view/${p.getTimestamp()}">Details</a></td>
                    
                    </tr>
            </c:forEach>
        </table>
    </body>
</html>
