<%-- 
    Document   : sign-in-check
    Created on : Sep 3, 2020, 10:30:14 AM
    Author     : rktirtho
--%>

<%@page import="com.pharmacy.dao.AdminDbHelper"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <%
            String userName = (String) request.getParameter("userName");
            String password = (String) request.getParameter("password");
            
            int status = AdminDbHelper.login(userName, password, session.getId());
            
            if (status == 1) {
                    response.sendRedirect("dashboard");
                    session.setAttribute("loged", true);
                }if(status ==0){
                    response.sendRedirect(request.getContextPath());
                } else {
                              
                
                }
            out.print(userName+ password);
            
        %>
    </body>
</html>
