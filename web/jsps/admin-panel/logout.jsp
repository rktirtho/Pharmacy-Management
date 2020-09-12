<%-- 
    Document   : logout
    Created on : Sep 12, 2020, 7:54:36 PM
    Author     : rktirtho
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <%
            session.setAttribute("loged", false);
            response.sendRedirect(request.getContextPath());
        %>
    </body>
</html>
