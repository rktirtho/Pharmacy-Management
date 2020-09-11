<%-- 
    Document   : pro-reg-check
    Created on : Sep 4, 2020, 4:19:48 PM
    Author     : rktirtho
--%>

<%@page import="com.pharmacy.service.ProductService"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<jsp:useBean id="product" class="com.pharmacy.product.Product"/>
<jsp:setProperty name="product" property="*" />
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <p align="center">Loading...</p>
        <%
            product.setIsAvailable(true);
            boolean inserted = ProductService.insertProduct(product);
//            out.print(product.getCodeNumber());

            response.sendRedirect("add-product?status=1");


        %>
    </body>
</html>
