<%-- 
    Document   : deleted
    Created on : Sep 4, 2020, 5:26:39 AM
    Author     : rktirtho
--%>

<%@page import="com.pharmacy.dao.AdminDbHelper"%>
<%@page import="com.pharmacy.admin.Admin"%>
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
        <%
            List<Admin> admins = AdminDbHelper.getDeleted();
            request.setAttribute("admins", admins);
        %>

        <table class="table table-striped table-bordered">
            <tr>
                <th>ID</th>
                <th>Name</th>
                <th>User Name</th>
                <th>Department</th>
                <th>Join Date</th>
                <th>Approve</th>
                <th>Reject</th>
            </tr>
            <c:forEach items="${admins}" var="a">

                <tr>
                    <td><a href="">${a.getId()}</a></td>
                    <td>${a.getName()}</td>
                    <td>${a.getUserName()}</td>
                    <td>${a.getAccType()}</td>
                    <td>${a.getTimestamp()}</td>
                    <td><a class="btn btn-success">Approve</a></td>
                    <td><a class="btn btn-danger">Reject</a></td>
                </tr>
            </c:forEach>
        </table>
    </body>
</html>

