<%-- 
    Document   : signup-check
    Created on : Sep 3, 2020, 9:10:16 AM
    Author     : rktirtho
--%>

<%@page import="com.pharmacy.dao.AdminDbHelper"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<jsp:useBean id="admin" class="com.pharmacy.admin.Admin"/>
<jsp:setProperty name="admin" property="*"/>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Checking...</title>
    </head>
    <body>
        Loading....
        <%
            admin.setIsActive(false);
            int status = AdminDbHelper.registration(admin);
            admin =  null;
            if (status == 0){
                response.sendRedirect("sign-up?status=0");
            }else if(status == 1){
                out.print("<p>Account has created. Please wait for approval."
                        + " Go to login <a href=\""+request.getContextPath()+"\">Page</a></p>");
            }

        %>
    </body>
</html>
