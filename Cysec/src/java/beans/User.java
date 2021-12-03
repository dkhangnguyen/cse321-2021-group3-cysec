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
 * @author Rook
 */

public class User implements Serializable {
	
	private String username;
	private String password;
	
	//Constructors
	public User() {
		username = "";
		password = "";
	}
	
        public User(String username, String password) {
		this.username = username;
		this.password = password;
	}
	
	//Getters & Setters
	public String getUsername() {
		return this.username;
	}
	
	public void setUsername(String username) {
		this.username = username;
	}
	
	public String getPassword() {
		return this.password;
	}
	
	public void setPassword(String password) {
		this.password = password;
	}
        
        public static User getUserFromDB(String uname) {
            User user = new User();

            try {
                
                String driver = "org.mariadb.jdbc.Driver";
                Class.forName(driver);

                String dbURL = "jdbc:mariadb://localhost:3306/apollo3_cysec";
                String username = "apollo3";
                String password = "qwer1234";

                Connection connection = DriverManager.getConnection(dbURL, username, password);

                java.sql.Statement statement = connection.createStatement();                
                String myQuery = "SELECT * FROM Account WHERE username= ?";               
                PreparedStatement ps = connection.prepareStatement(myQuery);
                
                ps.setString(1, uname);
                
                ResultSet rs = ps.executeQuery();

                if(rs.next()) {
                    user.setUsername(rs.getString("username"));
                    user.setPassword(rs.getString("userPwd"));
                    

                } else {
                   user = null;
                }
                      
                rs.close();
                statement.close();
                connection.close();
            
            } catch (ClassNotFoundException ex) {
                System.out.println("Error with connection: " + ex);
            } catch (SQLException ex) {
                System.out.println("SQLException: " + ex.getMessage());
                System.out.println("SQLState: " + ex.getSQLState());
                System.out.println("VendorError: " + ex.getErrorCode());
            }
            
        return user;    
    
    }
        
        public int addUserToDB() {
            try {
                
                String driver = "org.mariadb.jdbc.Driver";
                Class.forName(driver);

                String dbURL = "jdbc:mariadb://localhost:3306/apollo3_cysec";
                String username = "apollo3";
                String password = "qwer1234";

                Connection connection = DriverManager.getConnection(dbURL, username, password);

                java.sql.Statement statement = connection.createStatement();
                String myQuery = "INSERT INTO Account (username, userPwd )"
                                             + "VALUES (?, ?);";
                PreparedStatement  ps = connection.prepareStatement(myQuery);


                ps.setString(1, this.username);
                ps.setString(2, this.password);

                if (!ps.execute())
                    return 0;
                    
                     
                      
                ps.close();
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