
<%@page import="java.time.format.DateTimeFormatter"%>
<%@page import="java.time.LocalDateTime"%>
<%@page import="com.pharmacy.service.SellsService"%>
<%@page import="com.pharmacy.dao.AdminDbHelper"%>
<%@page import="com.pharmacy.admin.Admin"%>

<!------ Include the above in your HEAD tag ---------->

<!DOCTYPE html>
<html lang="en">

    <%
        boolean isLogin = false;
        isLogin = (boolean) session.getAttribute("loged");
        if (!isLogin) {
            response.sendRedirect(request.getContextPath());
        }
        Admin admin = AdminDbHelper.getBySession(session.getId());

        LocalDateTime today = LocalDateTime.now();
        LocalDateTime yesterday = LocalDateTime.now().minusDays(1);
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");

        double todaySell = SellsService.totalAmmountPerDay(dtf.format(today));
        double yesterdaySell = SellsService.totalAmmountPerDay(dtf.format(yesterday));
        double total = SellsService.totalAmmount();

    %>

    <head>
        <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <meta name="description" content="Responsive sidebar template with sliding effect and dropdown menu based on bootstrap 3">
        <title> Dashboard</title>
        <link type="text/css" rel="stylesheet" 
              href="${pageContext.request.contextPath}/res/libs/bootstrap.css"/>
        <link type="text/css" rel="stylesheet"
              href="${pageContext.request.contextPath}/res/css/a-panel.css"


    </head>

    <body>
        <div class="page-wrapper chiller-theme toggled">
            <a id="show-sidebar" class="btn btn-sm btn-dark" href="#">
                <i class="fas fa-bars"></i>
            </a>
            <nav id="sidebar" class="sidebar-wrapper">
                <div class="sidebar-content">
                    <div class="sidebar-brand">
                        <a href="#">Company Name</a>
                        <div id="close-sidebar">
                            <i class="fas fa-times"></i>
                        </div>
                    </div>
                    <div class="sidebar-header">
                        <div class="user-pic">
                            <img class="img-responsive img-rounded" src="https://raw.githubusercontent.com/azouaoui-med/pro-sidebar-template/gh-pages/src/img/user.jpg"
                                 alt="User picture">
                        </div>
                        <div class="user-info">
                            <span class="user-name">
                                <strong><%=admin.getName()%></strong>
                            </span>
                            <span class="user-role"><%=admin.getAccType()%></span>
                            <span class="user-status">
                                <i class="fa fa-circle"></i>
                                <span>Online</span>
                            </span>
                        </div>
                    </div>
                    <!-- sidebar-header  -->
                    <div class="sidebar-search">
                        <div>
                            <div class="input-group">
                                <input type="text" class="form-control search-menu" placeholder="Search...">
                                <div class="input-group-append">
                                    <span class="input-group-text">
                                        <i class="fa fa-search" aria-hidden="true"></i>
                                    </span>
                                </div>
                            </div>
                        </div>
                    </div>
                    <!-- sidebar-search  -->
                    <div class="sidebar-menu">
                        <ul>
                            <li class="sidebar-dropdown">
                                <a target="_blank" href="sell">
                                    <i class="fa fa-chart-line"></i>
                                    <span>Create Bill</span>
                                </a>
                            </li>
                            <li class="header-menu">
                                <span>General</span>
                            </li>
                            <li class="sidebar-dropdown">
                                <a href="#">
                                    <i class="fa fa-tachometer-alt"></i>
                                    <span>Dashboard</span>
                                    <span class="badge badge-pill badge-warning">New</span>
                                </a>
                                <div class="sidebar-submenu">
                                    <ul>                                        
                                        <li>
                                            <a href="#">Profit
                                                <span class="badge badge-pill badge-success">Pro</span>
                                            </a>
                                        </li>
                                    </ul>
                                </div>
                            </li>
                            <li class="sidebar-dropdown">
                                <a href="#">
                                    <i class="fa fa-tachometer-alt"></i>
                                    <span>Sells</span>

                                </a>
                                <div class="sidebar-submenu">
                                    <ul>
                                        <% if (admin.getAccType().equals("admin")) {

                                        %>
                                        <li>
                                            <a id="all-sell">
                                                <span>All Sells</span>
                                                <span class="badge badge-pill badge-warning">Done</span>
                                            </a>
                                        </li>

                                        <li>
                                            <a  id="daily-sell">Daily Sell</a>
                                        </li>
                                        <li>
                                            <a href="#">Sell Statistics</a>
                                        </li>
                                        <%}%>
                                        <!--sells man portal-->
                                        <li>
                                            <a href="#" id="my-sell">My Sells</a>
                                        </li>


                                    </ul>
                                </div>
                            </li>
                            <li class="sidebar-dropdown">
                                <a href="#">
                                    <i class="fa fa-shopping-cart"></i>
                                    <span>Product</span>
                                    <span class="badge badge-pill badge-danger">3</span>
                                </a>
                                <div class="sidebar-submenu">
                                    <ul>
                                        <li>
                                            <a href="add-product" target="_blank">
                                                <span>Add Products</span>
                                                <span class="badge badge-pill badge-warning">Done</span>

                                            </a>
                                        </li>
                                        <li>
                                            <a href="#" id="update-prduct">

                                                Update Product
                                            </a>
                                        </li>
                                        <li>
                                            <a id="all-product">

                                                <span>All Products</span>
                                                <span class="badge badge-pill badge-warning">Done</span>
                                            </a>
                                        </li>

                                        <li>
                                            <a href="#" id="need-to-buy">
                                                <span>Need to Purchase</span>
                                                <span class="badge badge-pill badge-warning">Done</span>
                                            </a>
                                        </li>

                                        <li>
                                            <a id="stock-out"><span>Out of Stock</span>
                                                <span class="badge badge-pill badge-warning">Done</span>
                                            </a>
                                        </li>
                                    </ul>
                                </div>
                            </li>

                            <% if (admin.getAccType().equals("admin")) {

                            %>
                            <li class="sidebar-dropdown">
                                <a href="#">
                                    <i class="far fa-gem"></i>
                                    <span>Employee</span>
                                    <span class="badge badge-pill badge-warning">Done</span>

                                </a>
                                <div class="sidebar-submenu">
                                    <ul>
                                        <li>
                                            <a href="##" id="all-employee">All Employee</a>
                                        </li>
                                        <li>
                                            <a href="#" id="new-request">Need to Approve</a>
                                        </li>
                                        <li>
                                            <a href="#" id="deleted">Deactivate Accounts</a>
                                        </li>

                                    </ul>
                                </div>
                            </li>
                            <%}
                            %>
                            <!--                            <li class="sidebar-dropdown">
                                                            <a href="#">
                                                                <i class="fa fa-chart-line"></i>
                                                                <span>Charts</span>
                                                            </a>
                                                            <div class="sidebar-submenu">
                                                                <ul>
                                                                    <li>
                                                                        <a href="#">Pie chart</a>
                                                                    </li>
                                                                    <li>
                                                                        <a href="#">Line chart</a>
                                                                    </li>
                                                                    <li>
                                                                        <a href="#">Bar chart</a>
                                                                    </li>
                                                                    <li>
                                                                        <a href="#">Histogram</a>
                                                                    </li>
                                                                </ul>
                                                            </div>
                                                        </li>-->
                            <li class="sidebar-dropdown">
                                <a href="#">
                                    <i class="fa fa-globe"></i>
                                    <span>Settings</span>
                                </a>
                                <div class="sidebar-submenu">
                                    <ul>
                                        <li>
                                            <a href="#">View Profile</a>
                                        </li>
                                        <li>
                                            <a href="#" id="change-password">Change Password</a>
                                        </li>
                                        <li>
                                            <a href="logout" id="change-password">Logout</a>
                                        </li>
                                    </ul>
                                </div>
                            </li>
                            <!--                            <li class="header-menu">
                                                            <span>Extra</span>
                                                        </li>
                                                        <li>
                                                            <a href="#">
                                                                <i class="fa fa-book"></i>
                                                                <span>Documentation</span>
                                                                <span class="badge badge-pill badge-primary">Beta</span>
                                                            </a>
                                                        </li>
                                                        <li>
                                                            <a href="#">
                                                                <i class="fa fa-calendar"></i>
                                                                <span>Calendar</span>
                                                            </a>
                                                        </li>
                                                        <li>
                                                            <a href="#">
                                                                <i class="fa fa-folder"></i>
                                                                <span>Examples</span>
                                                            </a>
                                                        </li>-->
                        </ul>
                    </div>
                    <!-- sidebar-menu  -->
                </div>
                <!-- sidebar-content  -->
                <div class="sidebar-footer">
                    <a href="#">
                        <i class="fa fa-bell"></i>
                        <span class="badge badge-pill badge-warning notification">3</span>
                    </a>
                    <a href="#">
                        <i class="fa fa-envelope"></i>
                        <span class="badge badge-pill badge-success notification">7</span>
                    </a>
                    <a href="#">
                        <i class="fa fa-cog"></i>
                        <span class="badge-sonar"></span>
                    </a>
                    <a href="#">
                        <i class="fa fa-power-off"></i>
                    </a>
                </div>
            </nav>
            <!-- sidebar-wrapper  -->
            <main class="page-content">
                <div class="container-fluid">
                    <h5>Overview</h5>

                    <div class="my-2 card p-2" id="content">


                        <div class="d-flex  bd-highlight">
                            <div class="flex-fill py-3">
                                <h6 class="text-center">Toady</h6>
                                <center>
                                    <b>Sell: <%= todaySell%> BDT</b>
                                </center>
                            </div>
                            <div class="flex-fill border-left border-right py-3">
                                <h6 class="text-center">Yesterday</h6> 
                                <center>
                                    <b>Sell: <%= yesterdaySell%> BDT</b>
                                </center>
                            </div>
                            <div class="flex-fill py-3">
                                <h6 class="text-center">Total</h6>
                                <center>
                                    <b>Sell: <%= total%> BDT</b>
                                </center>
                            </div>

                        </div>
                    </div>


                </div>

                <div class="container-fluid">
                    <h5>Todays Sells</h5>

                    <div class="my-2 card p-2" id="content">


                        <div class="d-flex  bd-highlight">
                            <div class="flex-fill py-3">
                                <h6 class="text-center">Rejaul Karim</h6>
                                <center>
                                    <b>Sell: <%= todaySell%> BDT</b>
                                </center>
                            </div>
                            <div class="flex-fill border-left border-right py-3">
                                <h6 class="text-center">Hasan Mahmud</h6> 
                                <center>
                                    <b>Sell: <%= yesterdaySell%> BDT</b>
                                </center>
                            </div>
                            <div class="flex-fill py-3">
                                <h6 class="text-center">Hedayet</h6>
                                <center>
                                    <b>Sell: <%= total%> BDT</b>
                                </center>
                            </div>
                            <div class="flex-fill py-3 border-left">
                                <h6 class="text-center">Rajon Miah</h6>
                                <center>
                                    <b>Sell: <%= total%> BDT</b>
                                </center>
                            </div>

                        </div>
                    </div>


                </div>
                <div class="container-fluid">
                    <h5>Profits</h5>

                    <div class="my-2 card p-2" id="content">


                        <div class="d-flex  bd-highlight">
                            <div class="flex-fill py-3">
                                <h6 class="text-center">Toady</h6>
                                <center>
                                    <b>Sell: <%= todaySell%> BDT</b>
                                </center>
                            </div>
                            <div class="flex-fill border-left border-right py-3">
                                <h6 class="text-center">Yesterday</h6> 
                                <center>
                                    <b>Sell: <%= yesterdaySell%> BDT</b>
                                </center>
                            </div>
                            <div class="flex-fill py-3">
                                <h6 class="text-center">Total</h6>
                                <center>
                                    <b>Sell: <%= total%> BDT</b>
                                </center>
                            </div>

                        </div>
                    </div>


                </div>

                <div class="container-fluid">
                    <h5>Profit From sellers</h5>

                    <div class="my-2 card p-2" id="content">


                        <div class="d-flex  bd-highlight">
                            <div class="flex-fill py-3">
                                <h6 class="text-center">Rejaul Karim</h6>
                                <center>
                                    <b>Sell: <%= todaySell%> BDT</b>
                                </center>
                            </div>
                            <div class="flex-fill border-left border-right py-3">
                                <h6 class="text-center">Hasan Mahmud</h6> 
                                <center>
                                    <b>Sell: <%= yesterdaySell%> BDT</b>
                                </center>
                            </div>
                            <div class="flex-fill py-3">
                                <h6 class="text-center">Hedayet</h6>
                                <center>
                                    <b>Sell: <%= total%> BDT</b>
                                </center>
                            </div>
                            <div class="flex-fill py-3 border-left">
                                <h6 class="text-center">Rajon Miah</h6>
                                <center>
                                    <b>Sell: <%= total%> BDT</b>
                                </center>
                            </div>

                        </div>
                    </div>


                </div>

            </main>
            <!-- page-content" -->
        </div>
        <!-- page-wrapper -->
        <script src="${pageContext.request.contextPath}/res/libs/jquery-3.3.1.js"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js" integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q"
        crossorigin="anonymous"></script>
        <script src="${pageContext.request.contextPath}/res/libs/bootstrap.bundle.js"></script>

        <script src="${pageContext.request.contextPath}/res/libs/bootstrap.js"></script>
        <script src="${pageContext.request.contextPath}/res/js/a-panel.js"></script>

        <script>
            var domainName = "/Pharmacy_Management"

            $(document).ready(function () {

//                $.ajax({
//                    url: domainName + "/jsps/sell/short-status.jsp",
//                    type: 'GET',
//                    success: function (data) {
//                        document.getElementById("content").innerHTML = data;
//                    }
//
//            })
            })

            $('.close-btn').click(function () {
                $.ajax({
                    type: "GET",
                    url: 'webapi/cart/deleteCart/' + $(this).attr("id"),
                    cache: false,
                    success: function (data) {
                        location.reload();

                    }
                });
            });

            $('#all-product').click(function () {

                $.ajax({
                    url: domainName + "/jsps/product/AllProduct.jsp",
                    type: 'GET',
                    success: function (data) {
                        document.getElementById("content").innerHTML = data;
                    }


                })
            })
            $('#update-prduct').click(function () {

                $.ajax({
                    url: domainName + "/jsps/product/update-product.jsp",
                    type: 'GET',
                    success: function (data) {
                        document.getElementById("content").innerHTML = data;
                    }


                })
            })


            $('#need-to-buy').click(function () {

                $.ajax({
                    url: domainName + "/jsps/product/need-to-buy.jsp",
                    type: 'GET',
                    success: function (data) {
                        document.getElementById("content").innerHTML = data;
                    }


                })
            })

            $('#stock-out').click(function () {

                $.ajax({
                    url: domainName + "/jsps/product/stock-out.jsp",
                    type: 'GET',
                    success: function (data) {
                        document.getElementById("content").innerHTML = data;
                    },

                })
            })




            $('#all-employee').click(function () {

                $.ajax({
                    url: domainName + "/jsps/admin-panel/all-employee.jsp",
                    type: 'GET',
                    success: function (data) {
                        document.getElementById("content").innerHTML = data;
                    }


                })
            })

            $('#new-request').click(function () {

                $.ajax({
                    url: domainName + "/jsps/admin-panel/new-request.jsp",
                    type: 'GET',
                    success: function (data) {
                        document.getElementById("content").innerHTML = data;
                    }


                })
            })

            $('#deleted').click(function () {

                $.ajax({
                    url: domainName + "/jsps/admin-panel/deleted.jsp",
                    type: 'GET',
                    success: function (data) {
                        document.getElementById("content").innerHTML = data;
                    }


                })
            })

//=======================Sell============================

            $('#all-sell').click(function () {

                $.ajax({
                    url: domainName + "/jsps/sell/all-sell.jsp",
                    type: 'GET',
                    success: function (data) {
                        document.getElementById("content").innerHTML = data;
                    }

                })
            })
            $('#daily-sell').click(function () {

                $.ajax({
                    url: domainName + "/jsps/sell/daily-sell.jsp",
                    type: 'GET',
                    success: function (data) {
                        document.getElementById("content").innerHTML = data;
                    }

                })
            })
            $('#my-sell').click(function () {
                $.ajax({
                    url: domainName + "/jsps/sell/my-sell.jsp",
                    type: 'GET',
                    success: function (data) {
                        document.getElementById("content").innerHTML = data;
                    }

                })
            })
//Profile


            $('#change-password').click(function () {

                $.ajax({
                    url: domainName + "/jsps/admin-panel/change-password.jsp",
                    type: 'GET',
                    success: function (data) {
                        document.getElementById("content").innerHTML = data;
                    }


                })
            })

        </script>

    </body>



</html>