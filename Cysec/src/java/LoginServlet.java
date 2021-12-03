/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import beans.*;
import java.io.*;
import java.sql.*;
import java.text.*;
import javax.servlet.*;
import java.util.*;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import javax.servlet.http.*;
/**
 *
 * @author Rook
 */
public class LoginServlet extends HttpServlet {

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
            out.println("<title>Servlet LoginServlet</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet LoginServlet at " + request.getContextPath() + "</h1>");
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
        String url = "Account.jsp";
                    
        String uname = request.getParameter("username");
        String pwd = request.getParameter("password");
        System.out.println("user= " + uname + " pw= " + pwd);

        if (action.equals("Register")) { //Register ===========================================================

            /* one of parameter is empty */
            if(uname.isEmpty() || pwd.isEmpty()) {
                RequestDispatcher rd = request.getRequestDispatcher("Account.jsp");
                request.setAttribute("error2", true);
                rd.forward(request, response);
                
            } 
            
            System.out.println("Pass test 1");

            /* duplicate username when sign up */
            User checkuser = User.getUserFromDB(uname);
            if (checkuser != null) {
                RequestDispatcher dispatcher = request.getRequestDispatcher(url);
                request.setAttribute("duplicate", true);   
                dispatcher.forward(request, response);
            }

            System.out.println("Pass test 2");


            /* Add account to Database*/
            User newUser = new User(uname, pwd);
            newUser.addUserToDB();
            
            System.out.println("Add to database");


        } else if (action.equals("Login")) { //Login ===========================================================
            //check if user exists
            User checkuser = User.getUserFromDB(uname);

            if (checkuser != null ) {
            System.out.println("User do exists");
            System.out.println("Password on database: " + checkuser.getPassword());
                if (pwd.equals(checkuser.getPassword())) { //if the password is correct
                                

                    HttpSession session = request.getSession();
                    User user = (User) session.getAttribute("user");
                    user = new User(uname, pwd);
                    session.setAttribute("user", user);
                } else {
                    System.out.println("wrong password");
                    request.setAttribute("error", true);   
                }

            } else { // User do not exists ==========
                request.setAttribute("error", true); 
                System.out.println("User does not exists");

            }

        } else if (action.equals("Logout")) {
            HttpSession session = request.getSession();
            if (session.getAttribute("user") != null) 
                session.removeAttribute("user");
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

