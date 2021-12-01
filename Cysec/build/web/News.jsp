<%-- 
    Document   : News
    Created on : Nov 27, 2021, 3:21:57 AM
    Author     : khang
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<!DOCTYPE html>
<html>

<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>CySec News</title>
    
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
                            <a style="color:#ffffff;" class="nav-link text-white" href="account.html">Account</a> 
                        </li>
                    </ul>
            </div>
        </nav>
        
        
      <!-- Body section -->

    <div class="row my-4 justify-content-center" id="DiscussionList" > 
        <c:forEach var="post"  items="${postList.postList}" >
            <c:if test="${post.postType == 1}">

                <div class="my-3">

                    <div class="container-fluid col-lg-10">
                        <a class="nav-link text-black" href="#" id="PostTitle"> 
                            <h3> ${post.postTitle} </h3>
                        </a>
                        <p class="lead" id="Author">By ${post.username}</p>

                        <div class="row">
                            <div class="col-3" id="DateTime">
                                <fmt:formatDate value="${post.postDate}" type="both" />
                            </div>
                            <div class="col-5" id="CommentCount">
                                
                                <c:out value="${post.comments.size()}"/> comments
                            </div>
                        </div>

                        <form action="PostServlet" method="post">
                            <input type="hidden" name="action" value="threadView">
                            <input type="hidden" name="postId" value="${post.postId}">
                            <input type="submit" value="View the Post">
                        </form> 
                    </div>

                </div>
            </c:if>        
        </c:forEach>        
              
        
      </div>

       
        <!-- Bootstrap core JS-->
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.0/dist/js/bootstrap.bundle.min.js"></script>
        <!-- Core theme JS-->
        <script src="js/scripts.js"></script>

</body>







</html>

