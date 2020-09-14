<%-- 
    Document   : sell
    Created on : Sep 4, 2020, 11:00:03 AM
    Author     : rktirtho
--%>

<%@page import="com.pharmacy.dao.AdminDbHelper"%>
<%@page import="com.pharmacy.admin.Admin"%>
<%@page import="java.time.LocalDateTime"%>
<%@page import="java.time.LocalDate"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <%
        boolean isLogin = false;
        isLogin = (boolean) session.getAttribute("loged");
        if (!isLogin) {
            response.sendRedirect(request.getContextPath());
        }
        Admin admin = AdminDbHelper.getBySession(session.getId());

    %>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link type="text/css" rel="stylesheet" 
              href="${pageContext.request.contextPath}/res/libs/bootstrap.css"/>
        <link type="text/css" rel="stylesheet" 
              href="${pageContext.request.contextPath}/res/libs/fontawesome-all.min.css"/>
        <title>Make Bill</title>
    </head>
    <body>
        <div class="card my-2 "  >
            <h4 class="text-center my-1">Janata Pharmacy Ltd.</h4>
            <p class="text-center my-1">12/C, Bangla Bazar, Dhaka-1100</p>
            <p class="text-center my-1">
                <span>
                    <%                        LocalDateTime date = LocalDateTime.now();
                        out.print(date);
                    %>
                </span>
            </p>

            <div class="" style="position: absolute; right: 50px; top: 10px">
                <b>Sells man:</b><br>
                
                <strong><%= admin.getName()%></strong>
            </div> 
        </div>
        <div class="sticky-top bg-white display-block p-4 mb-2 border" >
            <h5 class="text-right mx-5 "><span>Total: </span><span style="color: red" id="total-bill"> 00.0</span> BDT</h5>
            <a id="go-to-order" class="btn btn-primary text-white px-5 mx-3 float-right">Go &rightarrow;</a>
            <a class="btn btn-warning  px-5 mx-3 float-right" id="cancle-bill">Cancle Bill</a>
            <a class="btn btn-success text-white mx-3 float-right" id="add-more">Add Item</a>
        </div>


        <div class="container-fluid">

            <table id="items" class="table 
                   table-striped table-bordered text-center">
                <tr>
                    <th width="200px">Product Code</th>
                    <th width="350px">Name</th>
                    <th>Unit Price</th>
                    <th>Quantity</th>
                    <th>Available</th>
                    <th>Total</th>
                    <th colspan="2"></th>
                </tr>
                
            </table>


        </div>

        <datalist id="name-m">

        </datalist>
        <script src="${pageContext.request.contextPath}/res/libs/jquery-3.3.1.js"></script>
        <script src="${pageContext.request.contextPath}/res/libs/bootstrap.bundle.js"></script>
        <script src="${pageContext.request.contextPath}/res/libs/bootstrap.js"></script>
        <script src="${pageContext.request.contextPath}/res/js/sell-handaler.js"></script>
        <script>
            $(document).ready(function () {
                $('#name-m').empty();
                var domainName = "/Pharmacy_Management"

            })
                    ;

        </script>

    </body>
</html>
