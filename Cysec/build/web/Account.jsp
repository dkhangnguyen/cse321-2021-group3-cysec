<%-- 
    Document   : Account
    Created on : Nov 30, 2021, 10:58:00 PM
    Author     : Rook
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>CySec</title>   
        <link rel="stylesheet" href="cysec.css">
        <!-- link to Bootstrap -->
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-uWxY/CJNBR+1zjPWmfnSnVxwRheevXITnMqoEIeG1LJrdI0GlVs/9cVSyPYXdcSF" crossorigin="anonymous">
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-kQtW33rZJAHjgefvhyyzcGF3C5TFyBQBA13V1RKPf4uH+bwyzQxZ6CmMZHmNBEfJ" crossorigin="anonymous"></script>
    
        <script>
            function gotoNews(){
                document.getElementById("newsRedirect").submit();
            }
            
            function gotoDiscussion(){
                document.getElementById("discussionRedirect").submit();
            }
            
            // Test 11/30 - Ji
            function gotoAccount() {
                document.getElementById("accountRedirect").submit();
            }
        </script>
    </head>
    <body>
    
        <!-- Header -->
        <header class="bg-image py-3" 
                style="background-image: url('cramer.jpg'); 
                       height: 40vh" >
                       

            <div class="text-center my-3">
                <img src="Logo1.png" alt="logo" class="img-thumbnail" style="max-width: 20vh; height: auto"/>
                <h1 class="text-white fs-1 fw-bolder">Cyber Security Club</h1>
                <h2 class="text-white fs-3 fw-bolder">New Mexico Tech</h2>
            </div>
        </header>
        
        <!-- Navigation Bar -->
        <nav class="navbar navbar-expand-lg" style="background-color: #154c79">
            <div class="container-fluid">
                    <ul class="navbar-nav ms-auto mb-2 mb-lg-0">
                        <li class="nav-item"> 
                            <a style="color:#ffffff;" class="nav-link text-white" href="index.jsp">Home</a> 
                        </li>
                        <li class="nav-item">
                            <form id="newsRedirect" action="PostServlet" method="post">
                                <input type="hidden" name="action" value="viewNews">
                                <a style="color:#ffffff;" class="nav-link" href="#" onclick="gotoNews()">News</a>
                            </form>
                        </li>
                        
                        <li class="nav-item">
                            <form id="discussionRedirect" action="PostServlet" method="post">
                                <input type="hidden" name="action" value="viewDiscussion">
                                <a style="color:#ffffff;" class="nav-link text-white" href="#" onclick="gotoDiscussion()">Discussion</a>
                            </form>
                        </li>
                        
                        <!-- Change the link here to your account.jsp file , look at what I did for the two Links above-->
                        <li class="nav-item"> 
                            <form id="accountRedirect" action="PostServlet" method="post">  
                                <input type="hidden" name="action" value="viewAccount">
                                <a style="color:#ffffff;" class="nav-link text-white" href="#" onclick="gotoAccount()">Account</a> 
                            </form>    
                        </li>
                    </ul>
            </div>
        </nav>
        
        
      <!-- Body section -->
      
        <!-- User Logged In --------------------------------->
         <c:if test="${user != null}">
            <div id="login-1">
                <div class="container-fluid">
                    <div class="row">
                        <div class="col-md-12">
                            <h3> Hi ${user.username} </h3>
                            <h3>You have logged in successfully! </h3>

                            <form action="LoginServlet" method="post">
                                <input type="hidden" name="action" value="Logout"> 
                                <input type="submit" value="Log out">
                            </form>

                        </div>
                    </div>
                </div>
            </div>            
        </c:if>
          
        
         <!-- User Not Logged In --------------------------------->
                 <!-- Log In --------------------------------->

    <c:if test="${user == null}">
      <div id="login-1">
      <div class="container-fluid">
        <div class="row">
          <div class="col-md-12">
            <h3>Login Here</h3>
          </div>
        </div>
      </div>
      <div class="forms" id="login">
        <h4>Login</h4>
        <c:choose>
            <c:when test='${error == true}'>
                <font color=red>Username or password incorrect</font>
            </c:when>
        </c:choose>
                
       <form action="LoginServlet" method="post">
          <div class="form-group">
            <div class="form-row">
              <div class="form-group col-md-12">
                <label>Username</label><br>
                <input class="form-control" type="text" name="username">
              </div>
            </div>
          </div>

          <div class="form-group">
            <div class="foprm-row">
              <div class="form-group col-md-12">
                <label>Password</label><br>
                <input class="form-control" type="password" name="password">
              </div>
            </div>
          </div>



          <div class="form-row">
                <div class="form-group col-md-12">
                    <input type="hidden" name="action" value="Login">    
                    <input type="submit" value="Login">
                </div>
          </div>
        </form>
      </div>
    </div>

      
                 <!-- Sign Up --------------------------------->
      <!-- ######### Registration ######## -->
      <div class="container-fluid">
        <div class="row">
          <div class="col-md-12">
            <h3>Need to Register?</h3>
          </div>
        </div>
      </div>

      <div class="forms" id="register">
        <h4>Register</h4>
        <c:choose>
            <c:when test='${error2 == true}'>
                <font color=red>Please fill in all fields</font>
            </c:when>
            <c:when test='${duplicate == true}'>
                <font color=red>A user with that username already exists</font>
            </c:when>
        </c:choose>
                
                
        <form action="LoginServlet" method="post">
          <div class="form-group">
            <div class="form-row">
              <div class="form-group col-md-12">
                <label>Username</label><br>
                <input class="form-control" type="text" name="username">
              </div>
            </div>
          </div>

          <div class="form-group">
            <div class="foprm-row">
              <div class="form-group col-md-12">
                <label>Password</label><br>
                <input class="form-control" type="password" name="password">
              </div>
            </div>
          </div>



          <div class="form-row">
            <div class="form-group col-md-12">
                <input type="hidden" name="action" value="Register">    
              <input type="submit" value="Register">
            </div>
          </div>
        </form>
      </div>
      

      <c:choose>
            <c:when test='${error == true}'>
                <font color=red>Username or password incorrect</font>
            </c:when>
      </c:choose>
    </c:if> 
    
    
        <!-- Bootstrap core JS-->
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.0/dist/js/bootstrap.bundle.min.js"></script>
        <!-- Core theme JS-->
        <script src="js/scripts.js"></script>

</body>
</html>