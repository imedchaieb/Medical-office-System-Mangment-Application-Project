package com.medoffice.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.mindrot.jbcrypt.BCrypt;

import com.medoffice.util.DatabaseConnection;


public class UserDAO {
    public boolean validateLogin (String username, String password){
        String sql="SELECT * FROM users where username=?";
        try (Connection conn =DatabaseConnection.getConnection();
            PreparedStatement stmt=conn.prepareStatement(sql);
            ){
                stmt.setString(1,username);
                try(ResultSet res= stmt.executeQuery();){
                    if (res.next()) {
                        String storedHash = res.getString("password");
                        return BCrypt.checkpw(password, storedHash);
        }
                 return false;
        }
            
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }


    }
    public boolean  addUser(String username , String password){
        String hashedPassword = BCrypt.hashpw(password, BCrypt.gensalt());

        String sql="INSERT INTO users (username , password) values (?,?)";
        try (Connection conn = DatabaseConnection.getConnection();
         PreparedStatement stmt = conn.prepareStatement(sql)) {

        stmt.setString(1,username);
        stmt.setString(2,hashedPassword);

        return stmt.executeUpdate()==1;
    
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }


    }
   
}
