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
                    <th>Discount</th>
                    <th>Total</th>
                    <th colspan="2">Total</th>
                </tr>
                <tr>
                    <td ><input list="name-m" class="item-entry" 
                                style="width: 380px" type="text" name="" 
                                />
                    </td>
                    <td><span id="unit-price"></span></td>
                    <td><input id="quantity" type="number" name="" /></td>
                    <td><span>5%</span></td>
                    <td id="total">00.00</td>
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
        <datalist id="name-m">

        </datalist>
        <script src="${pageContext.request.contextPath}/res/libs/jquery-3.3.1.js"></script>
        <script src="${pageContext.request.contextPath}/res/libs/bootstrap.bundle.js"></script>
        <script src="${pageContext.request.contextPath}/res/libs/bootstrap.js"></script>
        <script>
            $(document).ready(function () {
                var domainName = "/Pharmacy_Management"
                $('#add-more').click(function () {
                    
                    $.ajax({
                        url: domainName + "/jsps/sell/model.jsp",
                        type: 'GET',
                        success: function (data) {
                            document.getElementById('items').innerHTML = '<option>' + data + '</option>'
//                            $('#items').in('<option>'+data+'</option>')
                        }
                    });
                    
                    //                    $('#items').append('<tr>'
                    //                    +'<td ><input class="item-entry" style="width: 380px" type="text" name="" /></td>+'
                    //                    +'<td></td>+'
                    //                    +'<td><input type="number" name="" /></td>+'
                    //                    +'<td></td>+'
                    //                    +'<td><a class="btn btn-danger">Remove</a></td>+'
                    //                +'</tr>');
                });
                
                var dataList;
                $('.item-entry').keyup(function () {
                    $('#name-m').empty();
                    var text = $(this).val();
                    $.ajax({
                        url: domainName + "/webapi/products/search?key=" + text,
                        //                    console.log(text);
                        type: 'GET',
                        
                        success: function (data) {
                            dataList = data;
                            for (var i = 0; i < data.length; i++) {
                                //                            console.log(data[i]);
//                                $('#name-m').empty();
                                $('#name-m').append('<option class="oid">' + data[i].name + '</option')
                            }
                            
                        }
                    });
                });
                
                $('.item-entry').on("focusout", function () {
                    $('#unit-price').html(dataList[0].unitSellingPrize)
                    
                });
                
                $('#quantity').on("focusout", function () {
//                    console.log("out");
                    var q= $(this).val();
                    $('#total').html(dataList[0].unitSellingPrize*q)
                    
                });
            });
            
            console.log(dataList);
            
        </script>

    </body>
</html>
