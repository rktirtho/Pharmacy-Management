<%-- 
    Document   : my-sell
    Created on : Sep 13, 2020, 3:05:14 PM
    Author     : rktirtho
--%>

<%@page import="com.pharmacy.dao.AdminDbHelper"%>
<%@page import="com.pharmacy.admin.Admin"%>
<%@page import="com.pharmacy.service.SellsService"%>
<%@page import="com.pharmacy.sells.DailySell"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
    <head>
        <%
            Admin admin = AdminDbHelper.getBySession(session.getId());
            List<DailySell> sells = SellsService.getDailySellInfoOfSeller(admin.getId());
            request.setAttribute("sells", sells);
            sells.get(0).getNumberOfInvoice();


        %>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

    </head>
    <body>
        
        <h2>My Sell</h2>
        <hr>

        <table class="table table-striped table-bordered">
            <tr>

                <th>Date</th>
                <th>Total Invoice</th>
                <th>Total Product Sell</th>
                <th>Ammoun</th>
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
                           href="seller-sell-view/${p.getTimestamp()}">Details</a></td>

                </tr>
            </c:forEach>
        </table>
    </body>
</html>
