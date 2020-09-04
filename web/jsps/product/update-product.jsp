<%-- 
    Document   : update-product
    Created on : Sep 4, 2020, 6:26:45 AM
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
        <h2>Update Product</h2>
        <hr>
        <form autocomplete="off" class="form-signin" 
              action="signin-check?cf=lg" method="post">
            <div class="form-label-group">
                <label for="inputEmail">Product Name</label>
                <input type="text" name="userName" id="inputEmail" class="form-control" placeholder="Email address" required autofocus>
            </div>


            <button class="btn my-3 btn-lg btn-primary btn-block text-uppercase" type="submit">Search</button>
        </form>
    </body>
</html>
