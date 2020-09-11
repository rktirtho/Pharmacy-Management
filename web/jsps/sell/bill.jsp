<%-- 
    Document   : bill
    Created on : Sep 11, 2020, 5:32:56 PM
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
            String invocationNO= request.getParameter("invocation");
            
        %>
        <h1><%=invocationNO%></h1>
    </body>
</html>
