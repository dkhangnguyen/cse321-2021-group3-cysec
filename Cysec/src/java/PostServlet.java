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
            System.out.println("update from database");
            
        }
        
        
        session.setAttribute("postList", postList);

        String url = "/Discussion.jsp";
        
        if (action.equals("none")) {
            url = "/Discussion.jsp";
        } else if (action.equals("createPostRequest")) {
            session.setAttribute("postList", postList);
            url = "/PostCreation.jsp";
            
        } else if (action.equals("viewDiscussion")) {
            
            url="/Discussion.jsp";    
                
        } else if (action.equals("viewNews")) {
            url="/News.jsp";
        
        } else if (action.equals("createPost")) {
            String postTitle = request.getParameter("postTitle");
            String postTypeString = request.getParameter("postType");
            int postType = 2;
            
            if (postTypeString.equals("NewsArticle")) {
                postType = 1;
            } else if (postTypeString.equals("DiscussionPost")) {
                postType = 2;
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
            url = "/Discussion.jsp";
            
            
        } else if (action.equals("threadView")) {
            int postIdToView = Integer.parseInt(request.getParameter("postId"));        
            Post postToView = postList.getPostFromId(postIdToView);
            if (postToView != null) {
                session.setAttribute("postView", postToView);
                url = "/PostThread.jsp?postId=" + postIdToView;
            } else {
                url = "/Discussion.jsp";
            }
           
        } else if (action.equals("postComment")) {
            int postId = Integer.parseInt(request.getParameter("postId"));
            
            Post p = postList.getPostFromId(postId);
            int newCommentId = p.getComments().get(0).getCommentId() + 1;
            
            String commentContent = request.getParameter("commentContent");
            String commentUsername = request.getParameter("author");
            
            Comment c = new Comment();
            c.setCommentId(newCommentId);
            c.setPostId(postId);
            c.setUsername(commentUsername);
            c.setCommentContent(commentContent);
            
            postList.getPostFromId(postId).addComment(c);
            session.setAttribute("postList", postList);
            
            url = "/PostThread.jsp?postId=" + postId;
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
