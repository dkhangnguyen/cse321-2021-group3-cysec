<%-- 
    Document   : PostThread
    Created on : Nov 27, 2021, 3:35:36 PM
    Author     : khang
--%>

<%@page import="beans.*"%>
<%@page import="java.util.*"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<!DOCTYPE html>
<html>

<head>
    <title>CySec</title>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

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
                <h1 class="text-white fs-1 fw-bolder" style="color:#ffffff;">Cyber Security Club</h1>
                <h2 class="text-white fs-3 fw-bolder" style="color:#ffffff;">New Mexico Tech</h2>
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

      <div class="row my-4 justify-content-center">
             
        <div class="my-3">
        
            <div class="container-fluid col-lg-10">
            
                <h3 id="PostTitle">
                    ${postView.postTitle}
                </h3> 
                <p class="lead" id="Author">
                    ${postView.username}
                </p>
                
                <div id="DiscussionBody">
                    <p> 
                        ${postView.postContent}
                    </p>
                
                </div>
                
                <div class="row" id="PostInfo">
                    <div class="col-3" id="DateTime"> 
                        <fmt:formatDate value="${postView.postDate}" type="both" />
                    </div>
                    <div class="col-5" id="CommentCount">
                        <c:out value="${postView.comments.size()}"/> comments
                    </div>
                </div>
                
                <br>
                
                <c:choose>
                
                    <c:when test="${postView.postType == 2}">
                    
                        <c:if test="${user != null}">  
                        <div id="comment-box">
                            <form action="PostServlet" method="post">
                                <textarea id="commentBox" name="commentContent" rows="4" cols="50" required>
                                Enter your comment here...
                                </textarea> 
                                <input type="hidden" name="postId" value="${postView.postId}">
                                <input type="hidden" name="action" value="postComment">
                                <input type="hidden" name="author" value="${user.username}">
                                <input type="submit" value="Post Comment">

                            </form>
                        </div>
                        </c:if>
                                
                        <br>            

                        <div class="row" id="CommentList">

                            <c:forEach var="comment" items="${postView.comments}">

                                <div id="comment1" class="container-fluid my-3" style="border-left: 2px solid black; padding-left: 10px;">

                                    <div class="row">
                                        <div class="col-2 fw-light" id="Commenter">
                                            ${comment.username}
                                        </div>
                                        <div class="col-4 fw-light" id="CommentDateTime">
                                            <fmt:formatDate value="${comment.commentDate}" type="both" />
                                        </div>
                                        
                                        
                                        <c:if test="${user != null}"> 
                                            <c:if test="${user.username == comment.username}">

                                                <div class="col-2 fw-light" >
                                                    <form action="PostServlet" method="post">
                                                        <input type="hidden" name="action" value="deleteComment">
                                                        <input type="hidden" name="postId" value="${postView.postId}">
                                                        <input type="hidden" name="commentId" value="${comment.commentId}">
                                                        <input type="submit" value="Delete Comment">
                                                    </form> 
                                                </div>
                                            </c:if>    
                                        </c:if>
                                    </div>

                                    <p id="CommentContent" class="mb-0">
                                        ${comment.commentContent}
                                    </p>

                                </div>

                            </c:forEach>

                        </div>
                    </c:when>
                    
                    <c:otherwise>
                        <br>
                        <div>
                            <p>You cannot comment in News Article</p>
                        </div>
                    </c:otherwise>
                
                </c:choose>    
                    
                <c:if test="${user != null}"> 
                    <c:if test="${user.username == postView.username}">
                <div class="row my-4 justify-content-center">
                    <form action="PostServlet" method="post">
                        <input type="hidden" name="action" value="deletePost">
                        <input type="hidden" name="postType" value="${postView.postType}">
                        <input type="hidden" name="postId" value="${postView.postId}">
                        <input type="submit" value="Delete this Post">
                    </form>
                </div>   
                    </c:if>    
                </c:if> 
                
            </div>
            
        </div>
        
        
        

        
      </div>  

       
        <!-- Bootstrap core JS-->
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.0/dist/js/bootstrap.bundle.min.js"></script>
        <!-- Core theme JS-->
        <script src="js/scripts.js"></script>

</body>







</html>

