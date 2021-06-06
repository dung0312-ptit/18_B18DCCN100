/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Bean;

import java.sql.*;

/**
 *
 * @author ASUS
 */
public class UserBean {
    private int ID;
    private String userName;
    private String password;
    private String fullName;
    private String position;
     private Connection cn;

    public UserBean() {
    }

    public UserBean(int ID, String userName, String password, String fullName, String position) {
        this.ID = ID;
        this.userName = userName;
        this.password = password;
        this.fullName = fullName;
        this.position = position;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }
    
     public void connect(){
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            cn = DriverManager.getConnection("jdbc:sqlserver://localhost:1433;databaseName=Restaurant","sa","dung031200");
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
     
    public UserBean getUser(String username, String password){
        UserBean us = new UserBean();
         connect();
        String sql = "SELECT * FROM UserAccount WHERE username=? AND password=?" ;
        try{
            PreparedStatement ps = this.cn.prepareStatement(sql);
            ps.setString(1, username);
            ps.setString(2, password);
            ResultSet rs = ps.executeQuery();
            while(rs.next()){              
                us.setID(rs.getInt("ID"));
                us.setUserName(rs.getString("username"));
                us.setPassword(rs.getString("password"));
                us.setFullName(rs.getString("fullname"));
                us.setPosition(rs.getString("position"));
            }
            cn.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return us;
    }
}
