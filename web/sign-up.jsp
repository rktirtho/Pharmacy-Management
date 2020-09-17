<%-- 
    Document   : registration
    Created on : Sep 2, 2020, 3:34:20 PM
    Author     : rktirtho
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link type="text/css" rel="stylesheet" 
              href="${pageContext.request.contextPath}/res/libs/bootstrap.css"/>
        <title>Signup</title>
    </head>
    <body>
        <div class="container">
            <div class="row">
                <div class="col-sm-9 col-md-7 col-lg-5 mx-auto">
                    <div class="card card-signin my-5">
                        <div class="card-body">
                            <h5 class="card-title bg-success text-center py-3">Sign Up</h5>
                            <form autocomplete="off" class="form-signin" action="signup-check?cf=sn" method="post">
                                <div class="form-label-group">
                                    <label for="inputEmail">Name</label>
                                    <input type="text" name="name" id="inputEmail" class="form-control" placeholder="Email address" required autofocus>
                                </div>

                                <div class="form-label-group">
                                    <label for="inputEmail">Email address</label>
                                    <input type="email" name="userName" id="inputEmail" class="form-control" placeholder="Email address" required autofocus>
                                </div>

                                <div class="form-label-group">
                                    <label for="inputPassword">Password</label>
                                    <input type="password" name="password" id="inputPassword" class="form-control" placeholder="Password" required>
                                </div>
                                
                                <div class="form-label-group">
                                    <label for="inputPassword">Re type Password</label>
                                    <input type="password" name="re-password" id="inputPassword" class="form-control" placeholder="Password" required>
                                </div>
                                <div class="form-label-group my-2">
                                    <label for="inputPassword">Role</label>
                                    <select name="accType">
                                        <option value="admin">Admin</option>
                                        <option value="sellsman">Sells Man</option>
                                    </select>
                                </div>
                               

<!--                                <div class="custom-control custom-checkbox mb-3">
                                    <input type="checkbox" class="custom-control-input" id="customCheck1">
                                    <label class="custom-control-label" for="customCheck1">Remember password</label>
                                </div>-->
                                <button class="btn my-3 btn-lg btn-primary btn-block text-uppercase" type="submit">Create Account</button>
                            </form>
                            <hr class="my-4">
                            <a class="btn btn-lg btn-outline-success rounded btn-block " href="${pageContext.request.contextPath}" >Sign in</a>

                        </div>
                    </div>
                </div>
            </div>
        </div>
    </body>
</html>
