<%-- 
    Document   : daily-sell-view
    Created on : Sep 12, 2020, 4:57:35 PM
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
        <%

            String date = request.getPathInfo().replace("/", "").substring(0, 10);
            List<SellView> svs = SellsService.getPerDaySell(date);
            request.setAttribute("sells", svs);

        %>
        <title><%=date%></title>
        <link type="text/css" rel="stylesheet" 
              href="${pageContext.request.contextPath}/res/libs/bootstrap.css"/>
    </head>

    <body> 
        <div class="container">
            
            <h1 class="text-center">Date: <%= date%></h1>
        <table class="table table-striped table-bordered">
            <tr>
                <th>Invoice No</th>
                <th>Seller Name</th>
                <th>Total</th>
                <th>Time</th>
            </tr>
            <c:forEach items="${sells}" var="p">

                <tr>
                    <td><a target="_blank" href="${pageContext.request.contextPath}/bill?invocation=${p.getInvoiceNo()}">${p.getInvoiceNo()}</a></td>
                    <td>${p.getSellerName()}</td>
                    <td>${p.getPrice()}</td>
                    <td>${p.getTime()}</td>

                </tr>
            </c:forEach>
        </table>

        </div>
    </body>
</html>
