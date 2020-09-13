<%-- 
    Document   : change-password
    Created on : Sep 5, 2020, 10:40:25 AM
    Author     : rktirtho
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link type="text/css" rel="stylesheet" 
              href="${pageContext.request.contextPath}/res/libs/bootstrap.css"/>
        <title>JSP Page</title>
    </head>
    <body>
        <div class="container">
            <div class="row">
                <div class="col-sm-9 col-md-7 col-lg-5 mx-auto">
                    <div class="card card-signin my-5">
                        <div class="card-body">
                            <h5 class="card-title bg-success text-center py-3">Change Password</h5>
                            <form autocomplete="off" class="form-signin" 
                                  action="change-pass" id="change-pass" method="post">
                                <div class="form-label-group">
                                    <label for="inputEmail">Old Password</label>
                                    <input type="password" name="old-password" 
                                           id="inputEmail" class="form-control" 
                                           placeholder="" required autofocus>
                                </div>

                                <div class="form-label-group">
                                    <label for="inputPassword">New Password</label>
                                    <input type="password" name="new-password" 
                                           id="inputPassword" class="form-control" 
                                           placeholder="" required>
                                </div>
                                <div class="form-label-group">
                                    <label for="inputPassword">Confirm Password</label>
                                    <input type="password" name="password" 
                                           id="confirm-Password" class="form-control" 
                                           placeholder="" required>
                                </div>

                                <!--                                <div class="custom-control custom-checkbox mb-3">
                                                                    <input type="checkbox" class="custom-control-input" id="customCheck1">
                                                                    <label class="custom-control-label" for="customCheck1">Remember password</label>
                                                                </div>-->
                                <button class="btn my-3 btn-lg btn-primary btn-block text-uppercase" id="submit-button" type="submit">Change Password</button>
                            </form>
                            <hr class="my-4">

                        </div>
                    </div>
                </div>
            </div>
        </div> 
    </body>
</html>
