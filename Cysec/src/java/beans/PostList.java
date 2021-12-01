/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;
import java.beans.*;
import java.io.PrintWriter;
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
public class PostList implements Serializable {
    private ArrayList<Post> postList;
    
    public PostList() {
        this.postList = new ArrayList<Post>();
    }
    
    public ArrayList<Post> getPostList() {
        return this.postList;
    }
    
    public Post getPostFromId(int id) {
        for (Post p : postList) {
            if (p.getPostId() == id) {
                return p;
            }
        }
        return null;
    }
    
    public Post getPostFromDB(int id) {
        Post p = new Post();

            try {
                
                String driver = "org.mariadb.jdbc.Driver";
                Class.forName(driver);

                String dbURL = "jdbc:mariadb://localhost:3306/apollo3_cysec";
                String username = "apollo3";
                String password = "qwer1234";

                Connection connection = DriverManager.getConnection(dbURL, username, password);

                java.sql.Statement statement = connection.createStatement();
                String myQuery1 = "SELECT * FROM Post where postId=" + id;
                String myQuery2 = "SELECT * FROM Comment where postId" + id;
                ResultSet rs1 = statement.executeQuery(myQuery1);
                ResultSet rs2 = statement.executeQuery(myQuery2);


                if(rs1.next()) {
                    p.setPostId(Integer.parseInt(rs1.getString("postId")));
                    p.setPostTitle(rs1.getString("postTitle"));
                    p.setPostType(Integer.parseInt(rs1.getString("postType")));
                    p.setUsername(rs1.getString("username"));
                    p.setPostContent(rs1.getString("postContent"));
                    p.setPostDate(rs1.getTimestamp("postDate"));
                    
                    while (rs2.next()) {
                        Comment c = new Comment();
                        c.setCommentId(Integer.parseInt(rs2.getString("commentId")));
                        c.setPostId(Integer.parseInt(rs2.getString("postId")));
                        c.setUsername(rs2.getString("username"));
                        c.setCommentContent(rs2.getString("commentContent"));
                        c.setCommentDate(rs2.getTimestamp("commentDate"));
                        
                        p.addComment(c);
                    }

                } 
                      
                rs1.close();
                rs2.close();
                statement.close();
                connection.close();
            
            } catch (ClassNotFoundException ex) {
                System.out.println("Error with connection: " + ex);
            } catch (SQLException ex) {
                System.out.println("SQLException: " + ex.getMessage());
                System.out.println("SQLState: " + ex.getSQLState());
                System.out.println("VendorError: " + ex.getErrorCode());
            }
            
        return null;    
    }
    
    public int getPostCount() {
        return this.postList.size();
    }
    
    public int getNewsNum() {
        int num = 0;
        for (Post p : postList) {
            if (p.getPostType() == 1) {
                num = num + 1;
            } 
        }
        return num;
    }
    
    public int getDiscussionNum() {
        int num = 0;
        for (Post p : postList) {
            if (p.getPostType() == 2) {
                num = num + 1;
            } 
        }
        return num;
    }
    
    /**
     * add a post to the newsBoard or discussionBoard based on its type
     * 1 for News
     * 2 for Discussion
     * @param newPost the post
     */
    public void addPost(Post newPost) {
        int newPostId = newPost.getPostId();

        for (int i = 0; i < postList.size(); i++) {
            Post p = postList.get(i);
            if (p.getPostId() == newPostId) {
                return;
            }            
        }
        
        postList.add(0, newPost);
        if (newPost.addPostToDB() != 1) {
            System.out.println("Error when add to database");
            return;
        }
              
    }
    
    
    /**
     * remove a post from the newsBoard or discussionBoard based on its type
     * 1 for News
     * 2 for Discussion
     * @param deletingPost the post to be deleted
     */
    public void removePost(Post deletingPost) {
        int delPostId = deletingPost.getPostId();        
        
        for (int i = 0; i < postList.size(); i++) {
            Post p = postList.get(i);
            if (p.getPostId() == delPostId) {
                postList.remove(p);
                p.removePostFromDB();
                return;
            }            
        }           
        
    }
    
    
    //Database
    
    
    public PostList getListFromDB() {
        PostList pl = new PostList();
            try {
                
                String driver = "org.mariadb.jdbc.Driver";
                Class.forName(driver);

                String dbURL = "jdbc:mariadb://localhost:3306/apollo3_cysec";
                String username = "apollo3";
                String password = "qwer1234";

                Connection connection = DriverManager.getConnection(dbURL, username, password);

                java.sql.Statement statement = connection.createStatement();
                String myQuery1 = "SELECT * FROM post";
                String myQuery2 = "SELECT * FROM comment";
                ResultSet rs1 = statement.executeQuery(myQuery1);
                ResultSet rs2 = statement.executeQuery(myQuery2);


                while(rs1.next()) {
                    Post p = new Post();
                    p.setPostId(Integer.parseInt(rs1.getString("postId")));
                    p.setPostTitle(rs1.getString("postTitle"));
                    p.setPostType(Integer.parseInt(rs1.getString("postType")));
                    p.setUsername(rs1.getString("username"));
                    p.setPostContent(rs1.getString("postContent"));
                    p.setPostDate(rs1.getTimestamp("postDate"));

                    ArrayList<Comment> comments = new ArrayList<Comment>();

                    while (rs2.next()) {
                        Comment c = new Comment();
                        c.setCommentId(Integer.parseInt(rs2.getString("commentId")));
                        c.setPostId(Integer.parseInt(rs2.getString("postId")));
                        c.setUsername(rs2.getString("username"));
                        c.setCommentContent(rs2.getString("commentContent"));
                        c.setCommentDate(rs2.getTimestamp("commentDate"));

                        
                        p.addComment(c);
                    }
                    
                    pl.addPost(p);
                } 
                      
                rs1.close();
                rs2.close();
                statement.close();
                connection.close();
            
            } catch (ClassNotFoundException ex) {
                System.out.println("Error with connection: " + ex);
            } catch (SQLException ex) {
                System.out.println("SQLException: " + ex.getMessage());
                System.out.println("SQLState: " + ex.getSQLState());
                System.out.println("VendorError: " + ex.getErrorCode());
            }
            
        return pl;    
    }    
    
    
}
