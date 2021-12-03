/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import beans.*;
import java.beans.*;
import java.io.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import javax.servlet.*;
import javax.servlet.http.*;

/**
 *
 * @author khang
 */
public class PostServlet extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet PostServlet</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet PostServlet at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getParameter("action");
        if (action == null) {
            action = "none";
        }
        
        HttpSession session = request.getSession();
        PostList postList = (PostList) session.getAttribute("postList");
        
        
        
        if (postList == null) {
            postList = new PostList();
            postList = postList.getListFromDB();   
            //System.out.println("update from database");
            
        }
        
        
        
        session.setAttribute("postList", postList);

        String url = "/Discussion.jsp";
        
        if (action.equals("none")) {
            url = "/Discussion.jsp";
        } else if (action.equals("createPostRequest")) {
            User user = (User) session.getAttribute("user");
            session.setAttribute("user", user);

            session.setAttribute("postList", postList);
            url = "/PostCreation.jsp";
            
        } else if (action.equals("viewDiscussion")) {
            User user = (User) session.getAttribute("user");
            session.setAttribute("user", user);
            url="/Discussion.jsp";    
                
        } else if (action.equals("viewNews")) {
            User user = (User) session.getAttribute("user");
            session.setAttribute("user", user);
            url="/News.jsp";
        
        } else if (action.equals("viewAccount")) {
            User user = (User) session.getAttribute("user");
            session.setAttribute("user", user);
            url="/Account.jsp";
            
        } else if (action.equals("createPost")) {
            User user = (User) session.getAttribute("user");
            session.setAttribute("user", user);
            String postTitle = request.getParameter("postTitle");
            String postTypeString = request.getParameter("postType");
            int postType = 2;
            
            if (postTypeString.equals("Discussion")) {
                postType = 2;
            } else if (postTypeString.equals("News")) {
                postType = 1;
            }
             
            
            String postAuthor = request.getParameter("author");
            String postContent = request.getParameter("postContent");
            
            Post newPost = new Post();
     
            //get the newest ID value + 1
            int newId = postList.getPostList().get(0).getPostId() + 1;
            newPost.setPostId(newId);
            newPost.setPostTitle(postTitle);
            newPost.setPostType(postType);
            newPost.setUsername(postAuthor);
            newPost.setPostContent(postContent);
            
            postList.addPost(newPost);
            
            session.setAttribute("postList", postList);
            if (postType == 1) {
                url = "/News.jsp";
            } else if (postType == 2) {
                url = "/Discussion.jsp";
            }
            
            
        } else if (action.equals("threadView")) {
            User user = (User) session.getAttribute("user");
            session.setAttribute("user", user);
            int postIdToView = Integer.parseInt(request.getParameter("postId"));
            System.out.println("ID received = " + postIdToView);
            Post postToView = postList.getPostFromId(postIdToView);
            System.out.println("Receive post: " + postToView.getPostTitle());
            if (postToView != null) {
                session.setAttribute("postView", postToView);
                //url = "/PostThread.jsp?postId=" + postIdToView;
                url = "/PostThread.jsp";
            } else {
                url = "/Discussion.jsp";
            }
           
        } else if (action.equals("postComment")) {
            User user = (User) session.getAttribute("user");
            session.setAttribute("user", user);
            int postToView = Integer.parseInt(request.getParameter("postId"));
            
            Post p = postList.getPostFromId(postToView);
            
            int newCommentId = postList.getLatestCommentId();
            
            String commentContent = request.getParameter("commentContent");
            String commentUsername = request.getParameter("author");
            
            Comment c = new Comment();
            c.setCommentId(newCommentId);
            c.setPostId(postToView);
            c.setUsername(commentUsername);
            c.setCommentContent(commentContent);
            
            postList.getPostFromId(postToView).addComment(c);
            session.setAttribute("postList", postList);
            session.setAttribute("postView", p);
            
            
            url = "/PostThread.jsp";
        } else if (action.equals("deletePost")) {
            int postId = Integer.parseInt(request.getParameter("postId"));
            int postType = Integer.parseInt(request.getParameter("postType"));
            Post deletingPost = postList.getPostFromId(postId);
            postList.removePost(deletingPost);
            session.setAttribute("postList", postList);
            
            if (postType == 1) {
                url="/News.jsp";
            } else if (postType == 2) {
                url="/Discussion.jsp";
            }
            
        } else if (action.equals("deleteComment")) {
            String test = request.getParameter("postId");
            if (test != null) {
                System.out.println(test);
            int postId = Integer.parseInt(request.getParameter("postId"));
            int commentId = Integer.parseInt(request.getParameter("commentId"));
            Post p = postList.getPostFromId(postId);
            if (p != null) {
                Comment c = p.getCommentFromId(commentId);
                p.removeComment(c);
            }
            session.setAttribute("postList", postList);
            session.setAttribute("postView", p);
            url="/PostThread.jsp";
            } else if (test == null) {
                System.out.println("return null");
            }
            
        }
        
        RequestDispatcher dispatcher = request.getRequestDispatcher(url);
        dispatcher.forward(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
