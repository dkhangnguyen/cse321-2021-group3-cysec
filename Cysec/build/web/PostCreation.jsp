<%-- 
    Document   : PostCreation
    Created on : Nov 27, 2021, 2:39:04 PM
    Author     : khang
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>

<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

    <title>CySec Discussion</title>
    
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
      
      
      Hi ${user.username}


      <div class="row my-4 justify-content-center"> 
      
        <div class="my-3" id="DiscussionPost1">
        
            <div class="container-fluid col-lg-10">
                <h3>Create a Post </h3>
                <form action="PostServlet" method="post">
                    <label for="postTitile">Post Title: </label><br>
                    <input type="text" name="postTitle" id="postTitle" maxlength="256" required><br>
                    <br>
                    <input type="radio" id="DiscussionPost" name="postType" value="Discussion" checked="true">
                    <label for="DiscussionPost">Discussion Post </label><br>
                    <input type="radio" id="NewsArticle" name="postType" value="News">
                    <label for="NewsArticle">News Article </label><br>
                    <br>
                    <label for="postContent">Post Content: </label><br>
                    <textarea id="postContent" name="postContent" rows="10" cols="80" required> </textarea>  
                    <br>
                    <input type="hidden" name="author" value="${user.username}">
                    <input type="hidden" name="action" value="createPost">
                    <br>
                    <input type="submit" value="Create Post">
                </form>
                
                
            </div>
            
        </div>  
        
      </div>  

       
        <!-- Bootstrap core JS-->
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.0/dist/js/bootstrap.bundle.min.js"></script>
        <!-- Core theme JS-->
        <script src="js/scripts.js"></script>

</body>







</html>
