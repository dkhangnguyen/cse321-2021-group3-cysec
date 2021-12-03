/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;
import java.beans.*;
import java.util.*;
import java.time.*;
import java.time.format.*;
import java.io.Serializable;
import java.sql.*;
import java.util.ArrayList;

/**
 *
 * @author khang
 */

public class Post implements Serializable{
    private int postId;             //post id
    private int postType;           //1 for news, 2 for discussion
    private String postTitle;       //post title
    private String username;        //author
    private Timestamp postDate;          //date published
    private String postContent;     //content of the post
    private ArrayList<Comment> comments;
    
    
    //constructor
    public Post() {
        this.postId = 0;
        this.postType = 0;
        this.postTitle = "";
        this.postContent = "";
        this.postDate = Timestamp.valueOf(LocalDateTime.now());
        this.username = "";
        this.comments = new ArrayList<Comment>();
        
    }
    
    
    public Post(String postTitle, int postType, String username, String postContent) {
        //automic generated
        this.postDate = Timestamp.valueOf(LocalDateTime.now());
        //from input parameters
        this.postType = postType;
        this.postTitle = postTitle;
        this.username = username;
        this.postContent = postContent;
        this.comments = new ArrayList<Comment>();        
    }
    
    //getters
    
    public int getPostId() {
        return this.postId;
    }
    
    public int getPostType() {
        return this.postType;
    }
    
    public String getPostTitle() {
        return this.postTitle;
    }
    
    public String getUsername() {
        return this.username;
    }
    
    public String getPostContent() {
        return this.postContent;
    }            
    
    public ArrayList<Comment> getComments() {
        return this.comments;
    }
    
    public Timestamp getPostDate() {
        return this.postDate;
    }
     
    
    
    //setters
    
    public void setPostId(int postId) {
        this.postId = postId;
    }
   
    public void setPostType(int postType) {
        this.postType = postType;
    }
    
    public void setPostTitle(String postTitle) {
        this.postTitle = postTitle;
    }
    
    public void setUsername(String username) {
        this.username = username;
    }
    
    public void setPostContent(String content) {
        this.postContent = content;
    }
    
    public void setPostDate(Timestamp t) {
        this.postDate = t;
    }
    
    public void setComments(ArrayList<Comment> comments) {
        this.comments = comments;
    }
    
    //comment-related methods
    
    public int getCommentCount() {
        return comments.size();
    }
    
    
    public Comment getCommentFromId(int id) {
        for (Comment c : this.comments) {
            if (c.getCommentId() == id) {
                return c;
            }
        }
        return null;
    }
    
    
    /**
     * add new Comment into the List 
     * @param newComment the new comment
     */
    public void addComment(Comment newComment) {
        if (newComment.getPostId() == this.postId) {
            this.comments.add(0, newComment);
            newComment.addCommentToDB();
        }    
    }
    
    /**
     * delete a comment
     * @param deletingComment the comment to be deleted
     */
    public void removeComment(Comment deletingComment) {
        if (deletingComment.getPostId() == this.postId) {
            int idToBeDeleted = deletingComment.getCommentId();
            for (int i = 0; i < comments.size(); i++) {
                Comment c = comments.get(i);
                if (c.getCommentId() == idToBeDeleted) {
                    comments.remove(i);
                    deletingComment.removeCommentFromDB();
                    return;
                }            
            }
        }    
        
    }
    
    //Database
    public int addPostToDB() {
        try {
                
                String driver = "org.mariadb.jdbc.Driver";
                Class.forName(driver);

                String dbURL = "jdbc:mariadb://localhost:3306/apollo3_cysec";
                String username = "apollo3";
                String password = "qwer1234";

                Connection connection = DriverManager.getConnection(dbURL, username, password);

                java.sql.Statement statement = connection.createStatement();
                String myQuery1 = "INSERT INTO Post (postId, postTitle, postType, username, postContent, postDate) "
                                             + "VALUES (?, ?, ?, ?, ?, ?);";
                String myQuery2 = "INSERT INTO Comment (commentId, postId, username, commentContent, commentDate) "
                                             + "VALUES (?, ?, ?, ?, ?);";
                PreparedStatement  ps1 = connection.prepareStatement(myQuery1);
                PreparedStatement  ps2 = connection.prepareStatement(myQuery2);


                ps1.setInt(1, this.postId);
                ps1.setString(2, this.postTitle);
                ps1.setInt(3, this.postType);
                ps1.setString(4, this.username);
                ps1.setString(5, this.postContent);
                ps1.setTimestamp(6, this.postDate);
                if (!ps1.execute())
                    return 0;
                    
                ArrayList<Comment> comments = this.comments;

                for (Comment c : comments) {
                    ps2.setInt(1, c.getCommentId());
                    ps2.setInt(2, c.getPostId());
                    ps2.setString(3, c.getUsername());
                    ps2.setString(4, c.getCommentContent());
                    ps2.setTimestamp(5, c.getCommentDate());
                        
                    if (!ps2.execute())
                        return 0;
                        
                }
                     
                      
                ps1.close();
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
    
    
    public int removePostFromDB() {
        try {
                
                String driver = "org.mariadb.jdbc.Driver";
                Class.forName(driver);

                String dbURL = "jdbc:mariadb://localhost:3306/apollo3_cysec";
                String username = "apollo3";
                String password = "qwer1234";

                Connection connection = DriverManager.getConnection(dbURL, username, password);

                java.sql.Statement statement = connection.createStatement();
                String myQuery1 = "DELETE FROM Post WHERE postId= ?";

                PreparedStatement  ps1 = connection.prepareStatement(myQuery1);

                ps1.setInt(1, this.postId);
                    
                if (!ps1.execute())
                    return 0;
                    
                ArrayList<Comment> comments = this.comments;

                for (Comment c : comments) {
                    c.removeCommentFromDB();
                        
                }
                     
                      
                ps1.close();
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


