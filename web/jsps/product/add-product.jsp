<%-- 
    Document   : add-product
    Created on : Sep 4, 2020, 6:23:29 AM
    Author     : rktirtho
--%>

<%@page import="com.pharmacy.dao.AdminDbHelper"%>
<%@page import="com.pharmacy.admin.Admin"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <%
        boolean isLogin = false;
        String satus = request.getParameter("status");
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
        <title>Add Product | <%= admin.getName() %></title>
    </head>
    <body>
        <div class="row">
            <div class="col-md-2">
                <div class="position-fixed col-2">
                    <div class="">
                        <div class="">
                            <div class="list-group">
                                <a href="" class="list-group-item">Link One</a>
                                <a href="" class="list-group-item">Link Two</a>
                                <a href="" class="list-group-item">Link Three</a>
                                <a href="" class="list-group-item">Link Four</a>
                                <a href="" class="list-group-item">Link Five</a>
                                <a href="" class="list-group-item">Link Six</a>
                                <a href="add-product" class="list-group-item">Registration</a>
                            </div>
                        </div>
                    </div>

                </div>
            </div>
            <div class="col-md-9 " >
                <div class="container">
                    <form autocomplete="off" class="border border-light p-5"
                          action="item-checkout?act=adm" method="post">

                        <p class="h4 mb-2 text-center">Add New Product</p>

                        <input type="hidden" value="" name="author_id">
                        <fieldset>
                            <div class="form-group">
                                <label for="name">Title*</label> 
                                <input type="text" required
                                       class="form-control" id="title" name="name"
                                       aria-describedby="name" placeholder="Product Title">

                            </div>


                            <!--                            <div class="col">
                                                                <div class="form-group" >
                                                                    <label for="category">Category*</label> <select
                                                                        required class="form-control" id="category" name="category"
                                                                        aria-describedby="emailHelp"  onchange="javascript: dynamicdropdown(this.options[this.selectedIndex].value);">
                                                                        <option>Select Category</option>
                                                                        <c:forEach var="c" items="${c}">
                                                                            <option value="${c}">${c}</option>
                                                                        </c:forEach>
                                                                    </select>
                                                                </div>
                                                            </div>-->

                            <!-- ===============================================Category, Brand, Model================================= -->

                            <div class="form-row mb-4">
                                <div class="col">
                                    <div class="form-group" >
                                        <label for="category">Category*</label> 
                                        <select
                                            required class="form-control" id="category" name="type"
                                            aria-describedby="category">
                                            <option value="medicine">Medicine</option>
                                            <option value="health-buaty">Health & Buaty</option>

                                        </select>
                                    </div>
                                </div>

                                <div class="col">
                                    <div class="form-group" >
                                        <label for="group">Group</label> 
                                        <input type="text" required
                                               class="form-control" id="title" name="group"
                                               aria-describedby="name" placeholder="Peracitamole bp 500">
                                    </div>
                                </div>

                                <div class="col">
                                    <div class="form-group">
                                        <label for="brand">Brand*</label> 
                                        <input type="text" required
                                               class="form-control" id="title" name="author"
                                               aria-describedby="brand" placeholder="Peracitamole bp 500">
                                    </div>
                                </div>

                                <div class="col">
                                    <div class="form-group">
                                        <label for="quantity">Quantity*</label> 
                                        <input type="text" required
                                               class="form-control" id="quantity" name="quantity"
                                               aria-describedby="quantity" placeholder="">
                                    </div>
                                </div>

                            </div>

                            <div class="form-row mb-4">
                                <div class="col">
                                    <div class="form-group" >
                                        <label for="expireDate">Expired Date*</label> 
                                        <input
                                            type="date" class="form-control" id="regular_price"
                                            name="expireDate" aria-describedby="expireDate"
                                            placeholder="000000">
                                    </div>
                                </div>

                                <div class="col">
                                    <div class="form-group" >
                                        <label for="batchNo">Batch No</label>
                                        <input
                                            type="text" class="form-control" id="regular_price" 
                                            name="batchNo" aria-describedby="batchNo"
                                            placeholder="000000">
                                    </div>
                                </div>

                                <div class="col">
                                    <div class="form-group">
                                        <label for="inventor">Bar Code</label> 
                                        <input
                                            type="text" class="form-control" id="" 
                                            name="codeNumber" aria-describedby="inventor"/>
                                        <input
                                            type="hidden" class="form-control" id="" 
                                            name="inventor" aria-describedby="inventor" 
                                            value="<%=admin.getUserName()%>" />
                                    </div>
                                </div>

                                <!--                                <div class="col">
                                                                    <div class="form-group">
                                                                        <label for="model">Quantity*</label> 
                                                                        <input type="text" required
                                                                               class="form-control" id="model" name="model"
                                                                               aria-describedby="emailHelp" placeholder="">
                                                                    </div>
                                                                </div>-->

                            </div>
                        </fieldset>
                        <fieldset>
                            <legend>Pricing</legend>
                            <!-- ===============================================Category, Brand, Model================================= -->

                            <div class="form-row mb-2">
                                <div class="col">
                                    <div class="form-group">
                                        <label for="unitSize">Unit Size*</label> <input
                                            type="text" class="form-control" id="" 
                                            name="unitSize" aria-describedby="unitSize"
                                            placeholder="">
                                    </div>
                                </div>
                                <div class="col">
                                    <div class="form-group">
                                        <label for="unitBuyingPrize">Purchase Price*</label> <input
                                            type="text" class="form-control" id=""
                                            name="unitBuyingPrize" aria-describedby="unitBuyingPrize"
                                            placeholder="">
                                    </div>
                                </div>
                                <div class="col">
                                    <div class="form-group">
                                        <label for="unitSellingPrize">Selling Price*</label> 
                                        <input type="text" required class="form-control" 
                                               id="discount" name="unitSellingPrize"
                                               aria-describedby="unitSellingPrize" placeholder="">
                                    </div>
                                </div>
                                <div class="col">
                                    <div class="form-group">
                                        <label for="discount">Discount%*</label> 
                                        <input type="text" required class="form-control" 
                                               id="discount" name="discount"
                                               aria-describedby="emailHelp" placeholder="">
                                    </div>
                                </div>

                            </div>
                        </fieldset>

                        <!-- ===============================================Description================================= -->

                        <!--                        <div class="form-row mb-4">
                                                    <div class="col">
                                                        <div class="form-group">
                                                            <label for="processor">Description</label>
                                                            <textarea rows="2" class="form-control" id="processor"
                                                                      placeholder="Category" name="description"> </textarea>
                                                        </div>
                                                    </div>
                        
                        
                                                </div>-->
                        
                            <%
                                if (satus != null) {
                                    if(satus.equals("1")){
                                        out.print("<h6 class='text-success text-center'>Successfully Inserted");
                                    }else{
                                        out.print("<h6 class='text-danger text-center'>Failed. Try again");
                                    }

                                } 
                            %>
                       

                        <button class="btn btn-info my-4 btn-block " type="submit">Registration</button>


                    </form>
                </div>
            </div>
        </div>
    </body>
</html>
