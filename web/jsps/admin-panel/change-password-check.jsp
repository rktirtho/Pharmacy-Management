<%-- 
    Document   : change-password-check
    Created on : Sep 13, 2020, 4:35:38 PM
    Author     : rktirtho
--%>

<%@page import="com.pharmacy.dao.AdminDbHelper"%>
<%@page import="com.pharmacy.admin.Admin"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <%
            String oldPass = request.getParameter("old-password");
            String newPass = request.getParameter("new-password");
            String confPass = request.getParameter("confirm-Password");
            Admin admin = AdminDbHelper.getBySession(session.getId());
            if(!newPass.equals(confPass)){
                out.print("New password and confirm password are not same");
                return;
            }
            int status = AdminDbHelper.changePassword(oldPass, newPass, admin.getId());

            if (status == 1) {
                out.print("password changed");

            } else {
                out.print("Failed");
            }
        %>
    </body>
</html>
