<%-- 
    Document   : add-product
    Created on : Sep 4, 2020, 6:23:29 AM
    Author     : rktirtho
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link type="text/css" rel="stylesheet" 
              href="${pageContext.request.contextPath}/res/libs/bootstrap.css"/>
        <title>Add Product</title>
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
                          action="ProductRegistrationCheck?act=adm" method="post"
                          enctype="multipart/form-data">

                        <p class="h4 mb-2 text-center">Add New Product</p>

                        <input type="hidden" value="" name="author_id">
                        <fieldset>
                            <div class="form-group">
                                <label for="title">Title*</label> <input type="text" required
                                                                         class="form-control" id="title" name="title"
                                                                         aria-describedby="emailHelp" placeholder="Product Title">
                              
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
                                        <label for="category">Category*</label> <select
                                            required class="form-control" id="category" name="category"
                                            aria-describedby="emailHelp">
                                            <option value="medicine">Medicine</option>
                                            <option value="health-buaty">Health & Buaty</option>
                                            
                                        </select>
                                    </div>
                                </div>

                                <div class="col">
                                    <div class="form-group" >
                                        <label for="category">Group</label> <select
                                            required class="form-control" id="sub_category" name="sub_category"
                                            aria-describedby="emailHelp" >
                                            <option>Select Category</option>
                                        </select>
                                    </div>
                                </div>

                                <div class="col">
                                    <div class="form-group">
                                        <label for="brand">Brand*</label> 
                                        <select
                                            required class="form-control" id="brand" name="brand"
                                            aria-describedby="emailHelp" >
                                            <option>Select Brand</option>
                                            <c:forEach var="b" items="${b}">
                                                <option value="${b}">${b}</option>
                                            </c:forEach>
                                        </select>
                                    </div>
                                </div>

                                <div class="col">
                                    <div class="form-group">
                                        <label for="model">Quantity*</label> <input type="text" required
                                                                                 class="form-control" id="model" name="model"
                                                                                 aria-describedby="emailHelp" placeholder="">
                                    </div>
                                </div>

                            </div>
                        </fieldset>
                        <fieldset>
                            <legend>Pricing</legend>
                            <!-- ===============================================Category, Brand, Model================================= -->

                            <div class="form-row mb-2">
                                <div class="col">
                                    <div class="form-group">
                                        <label for="regular_price">Unit Size*</label> <input
                                            type="text" class="form-control" id="regular_price" required
                                            name="regularprice" aria-describedby="emailHelp"
                                            placeholder="000000">
                                    </div>
                                </div>
                                <div class="col">
                                    <div class="form-group">
                                        <label for="regular_price">Purchase Price*</label> <input
                                            type="text" class="form-control" id="regular_price" required
                                            name="regularprice" aria-describedby="emailHelp"
                                            placeholder="000000">
                                    </div>
                                </div>
                                <div class="col">
                                    <div class="form-group">
                                        <label for="discount">Selling Price*</label> <input type="text"
                                                                                        required class="form-control" id="discount" name="discount"
                                                                                        aria-describedby="emailHelp" placeholder="10/20">
                                    </div>
                                </div>
                                <div class="col">
                                    <div class="form-group">
                                        <label for="discount">Discount%*</label> <input type="text"
                                                                                        required class="form-control" id="discount" name="discount"
                                                                                        aria-describedby="emailHelp" placeholder="10/20">
                                    </div>
                                </div>

                            </div>
                        </fieldset>

                        <!-- ===============================================Description================================= -->

                        <div class="form-row mb-4">
                            <div class="col">
                                <div class="form-group">
                                    <label for="processor">Description</label>
                                    <textarea rows="2" class="form-control" id="processor"
                                              placeholder="Category" name="description"> </textarea>
                                </div>
                            </div>


                        </div>

                        <button class="btn btn-info my-4 btn-block" type="submit">Registration</button>


                    </form>
                </div>
            </div>
        </div>
    </body>
</html>
