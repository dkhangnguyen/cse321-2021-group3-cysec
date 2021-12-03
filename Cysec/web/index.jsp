<%-- 
    Document   : Home
    Created on : Nov 27, 2021, 3:21:37 AM
    Author     : khang
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>CySec</title>   
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

        <!-- About Us section-->
        <section class="py-5" id="AboutUs">
            <div class="container my-5">
                <div class="row justify-content-center">
                    <div class="col-lg-8">
                        <h2>About us</h2>
                        <p class="lead">
                            The New Mexico Tech Cybersecurity Club is a student-run organization at the New Mexico
                            Institute of Mining and Technology University, Socorro, New Mexico. Members and leaders
                            of the club are currently pursuing studies in computer science, computer engineering, 
                            and electrical engineering, and other disciplines. </p>
                        <p class="mb-0">The club's main purpose is to provide students who are interested in 
                            cybersecurity with the technical resources needed to advance their computer 
                            security careers. The club achieves this mission by organizing monthly meetings 
                            to discuss and practice hands-on cybersecurity concepts including, but not limited 
                            to, network security, digital forensics, penetration testing, secure coding, cryptography, 
                            and reverse engineering. On top of this, we invite local companies and organizations to 
                            speak at our meetings and promote their work in computer security. </p>
                    </div>
                </div>
            </div>
        </section>
        
        
        
        
       
        <!-- Image element -->
        <div class="py-3 bg-image-full" 
            style="background-image: url('image1.jpg'); 
                   height:40vh; 
                   object-position: center;" >
        </div>


        <!-- Meeting Info section-->
        <section class="py-5" id="MeetingInfo">
            <div class="container my-5">
                <div class="row justify-content-center">
                    <div class="col-lg-8">
                        <h2>Interested in joining?</h2>
                        <p class="lead">Meet at Cramer 236</p>
                        <p class="mb-0">Sunday 5:00am</p>
                    </div>
                </div>
            </div>
        </section>
        
        

       
        <!-- Bootstrap core JS-->
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.0/dist/js/bootstrap.bundle.min.js"></script>
        <!-- Core theme JS-->
        <script src="js/scripts.js"></script>

</body>
</html>
