<%-- 
    Document   : approve-admin
    Created on : Sep 5, 2020, 10:14:47 AM
    Author     : rktirtho
--%>

<%@page import="com.pharmacy.dao.AdminDbHelper"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Checking</title>
    </head>
    <body>
        <%
            String choise = request.getParameter("response");
            String idStr = request.getParameter("id");
            int id = Integer.parseInt(idStr);
            
            if (choise.equals("1")) {
                // Approve Account
                int status = AdminDbHelper.approve(id);
                if (status == 1) {
                    out.print("Approved");
                } else {
                    out.print("error");
                }
            } else {
                int status = AdminDbHelper.reject(id);
                if (status == 1) {
                    out.print("Request rejected");
                } else {
                    out.print("error");
                }
            }
            
        %>
    </body>
</html>
