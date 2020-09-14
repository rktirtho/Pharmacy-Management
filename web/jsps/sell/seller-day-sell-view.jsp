<%-- 
    Document   : seller-day-sell-view
    Created on : Sep 13, 2020, 3:27:49 PM
    Author     : rktirtho
--%>

<%@page import="com.pharmacy.dao.AdminDbHelper"%>
<%@page import="com.pharmacy.admin.Admin"%>
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
            double total = 0;
            request.setAttribute("total", total);
            Admin admin = AdminDbHelper.getBySession(session.getId());

            String date = request.getPathInfo().replace("/", "").substring(0, 10);
            List<SellView> svs = SellsService.getPerDayBySeller(date, admin.getId());
            request.setAttribute("sells", svs);

        %>
        <title><%=date%> | <%= admin.getName() %>'s Sells</title>
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
                    <c:set var="total" value="${total+ p.getPrice()}"></c:set>
                </c:forEach>
                <tr>
                    <th colspan="2" >Total: </th>
                    <th colspan=""><c:out value="${total}"></c:out> BDT</th>
                    <td></td>

                </tr>
            </table>

        </div>
    </body>
</html>
