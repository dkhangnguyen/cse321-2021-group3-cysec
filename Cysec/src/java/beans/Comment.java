/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;
import java.beans.*;
import java.io.Serializable;
import java.sql.*;
import java.util.*;
import java.time.*;
import java.time.format.DateTimeFormatter;
/**
 *
 * @author khang
 */
public class Comment implements Serializable {
    private int commentId;
    private int postId;
    private String username;
    private String commentContent;
    private Timestamp commentDate;
    
    
    //constructor
    public Comment() {
        this.commentId = 0;
        this.commentContent = "";
        this.postId = 0;
        this.username = "";
        this.commentDate = Timestamp.valueOf(LocalDateTime.now());
        
    }
    
    public Comment(int commentId, String username, int postId, String commentContent) {
        this.commentId = commentId;
        this.commentDate = Timestamp.valueOf(LocalDateTime.now());
        this.commentContent = commentContent;
        this.username = username;  
        this.postId = postId;
    }
    
    //getters
    public int getCommentId() {
        return this.commentId;
    }
    
    public int getPostId() {
        return this.postId;
    }
    
    public String getUsername() {
        return this.username;
    }
    
    public String getCommentContent() {
        return this.commentContent;
    }
            
    public Timestamp getCommentDate() {
        return this.commentDate;
    }
     
     

    //setter
    public void setCommentId(int commentId) {
        this.commentId = commentId;
    }
    
    public void setCommentDate(Timestamp t) {
        this.commentDate = t;
    }
    
    public void setPostId(int postId) {
        this.postId = postId;
    }
    
    public void setUsername(String username) {
        this.username = username;
    }
    
    public void setCommentContent(String content) {
        this.commentContent = content;
    }
    
    //Database
    
    public int addCommentToDB() {
        try {
                
                String driver = "org.mariadb.jdbc.Driver";
                Class.forName(driver);

                String dbURL = "jdbc:mariadb://localhost:3306/apollo3_cysec";
                String username = "apollo3";
                String password = "qwer1234";

                Connection connection = DriverManager.getConnection(dbURL, username, password);

                java.sql.Statement statement = connection.createStatement();

                String myQuery2 = "INSERT INTO comment (commentId, postId, username, commentContent, commentDate) "
                                             + "VALUES (?, ?, ?, ?, ?);";
                PreparedStatement  ps2 = connection.prepareStatement(myQuery2);


                ps2.setInt(1, this.commentId);
                ps2.setInt(2, this.postId);
                ps2.setString(3, this.username);
                ps2.setString(4, this.commentContent);
                ps2.setTimestamp(5, this.commentDate);
                        
                if (!ps2.execute())
                    return 0;
                                
                ps2.close();
                statement.close();
                connection.close();
                
                
            
            } catch (ClassNotFoundException ex) {
                System.out.println("Error with connection: " + ex);
            } catch (SQLException ex) {
                System.out.println("SQLException: " + ex.getMessage());
                System.out.println("SQLState: " + ex.getSQLState());
                System.out.println("VendorError: " + ex.getErrorCode());
            }
            
        
        return 1;
    }
    
    public int removeCommentFromDB() {
        try {
                
                String driver = "org.mariadb.jdbc.Driver";
                Class.forName(driver);

                String dbURL = "jdbc:mariadb://localhost:3306/apollo3_cysec";
                String username = "apollo3";
                String password = "qwer1234";

                Connection connection = DriverManager.getConnection(dbURL, username, password);

                java.sql.Statement statement = connection.createStatement();

                String myQuery2 = "DELETE FROM Comment WHERE commentId = ? AND postId = ?";
                PreparedStatement  ps2 = connection.prepareStatement(myQuery2);


                ps2.setInt(1, this.commentId);
                ps2.setInt(2, this.postId);
                        
                if (!ps2.execute())
                    return 0;
                                
                ps2.close();
                statement.close();
                connection.close();
                
                
            
            } catch (ClassNotFoundException ex) {
                System.out.println("Error with connection: " + ex);
            } catch (SQLException ex) {
                System.out.println("SQLException: " + ex.getMessage());
                System.out.println("SQLState: " + ex.getSQLState());
                System.out.println("VendorError: " + ex.getErrorCode());
            }
            
        
        return 1;
    }
    
}
