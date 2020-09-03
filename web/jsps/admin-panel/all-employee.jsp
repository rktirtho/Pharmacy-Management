<%-- 
    Document   : all-employee
    Created on : Sep 3, 2020, 2:43:23 PM
    Author     : rktirtho
--%>

<%@page import="com.pharmacy.dao.AdminDbHelper"%>
<%@page import="com.pharmacy.admin.Admin"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>All </title>
    </head>
    <body>
        <%
            List<Admin> admins = AdminDbHelper.getAll();
            request.setAttribute("admins", admins);
        %>

        <table class="table table-striped table-bordered">
            <tr>
                <th>ID</th>
                <th>Name</th>
                <th>User Name</th>
                <th>Department</th>
                <th>Join Date</th>
                <th>Block</th>
                <th>Remove</th>
            </tr>
            <c:forEach items="${admins}" var="a">

                <tr>
                    <td><a href="">${a.getId()}</a></td>
                    <td>${a.getName()}</td>
                    <td>${a.getUserName()}</td>
                    <td>${a.getAccType()}</td>
                    <td>${a.getTimestamp()}</td>
                    <td><a class="btn btn-warning">Block</a></td>
                    <td><a class="btn btn-danger">Delete</a></td>
                </tr>
            </c:forEach>
        </table>
        
    </body>
</html>
