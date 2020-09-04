<%-- 
    Document   : sell
    Created on : Sep 4, 2020, 11:00:03 AM
    Author     : rktirtho
--%>

<%@page import="java.time.LocalDateTime"%>
<%@page import="java.time.LocalDate"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link type="text/css" rel="stylesheet" 
              href="${pageContext.request.contextPath}/res/libs/bootstrap.css"/>
        <title>Selling</title>
    </head>
    <body>

        <div class="container-fluid">
            <div class="card my-2">
                <h4 class="text-center my-1">Janata Pharmacy Ltd.</h4>
                <p class="text-center my-1">12/C, Bangla Bazar, Dhaka-1100</p>
                <p class="text-center my-1">
                    <span>
                        <%
                            LocalDateTime date = LocalDateTime.now();
                            out.print(date);
                        %>
                    </span>
                </p>

            </div>
            <table id="items" class="table table-striped table-bordered">
                <tr>
                    <th width="400px">Name</th>
                    <th>Unit Price</th>
                    <th>Quantity</th>
                    <th>Total</th>
                    <th colspan="2">Total</th>
                </tr>
                <tr>
                    <td ><input style="width: 380px" type="text" name="" /></td>
                    <td>34</td>
                    <td><input type="number" name="" /></td>
                    <td>650</td>
                    <td><a class="btn btn-danger">Remove</a></td>
                </tr>
            </table>
            <div>
               <button class="px-4 mr-4" style="display: block" id="add-more">Add More</button>
            </div>
            <div class="my-3 border py-3">
                <b class="p-3">Total: </b><span class=" p-3">456356</span>
            </div>

        </div>
        <script src="${pageContext.request.contextPath}/res/libs/jquery-3.3.1.js"></script>
        <script src="${pageContext.request.contextPath}/res/libs/bootstrap.bundle.js"></script>
        <script src="${pageContext.request.contextPath}/res/libs/bootstrap.js"></script>
        <script>
            $(document).ready(function () {
                $('#add-more').click(function () {
                    $('#items').append('<tr>' +
                            '<td ><input style="width: 380px" type="text" name="" /></td>'
                            + '<td></td>'
                            + '<td><input type="number" name="" /></td>'
                            + '<td></td>'
                            + '<td><a class="btn btn-danger">Remove</a></td>'
                            + '</tr>')


                })
            });

        </script>

    </body>
</html>
