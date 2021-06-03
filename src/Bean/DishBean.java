/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Bean;


import java.io.Serializable;
import java.sql.*;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author ASUS
 */
public class DishBean implements Serializable{
    private int ID;
    private String type;
    private String name;
    private String discrip;
    private float price;
    private Connection cn;

    public DishBean() {
    }

    public DishBean(int ID, String type, String name, String discrip, float price) {
        this.ID = ID;
        this.type = type;
        this.name = name;
        this.discrip = discrip;
        this.price = price;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDiscrip() {
        return discrip;
    }

    public void setDiscrip(String discrip) {
        this.discrip = discrip;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }
    
    public void connect(){
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            cn = DriverManager.getConnection("jdbc:sqlserver://localhost:1433;databaseName=Restaurant","sa","dung031200");
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
    
    public ArrayList<DishBean> searchDish(String name){
        connect();
        ArrayList<DishBean> list = new ArrayList<>();
        String sql = "SELECT * FROM Dish WHERE name LIKE "+"'%"+name+"%'";
        try{
            PreparedStatement ps = this.cn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                DishBean d = new DishBean();
                d.setID(rs.getInt("ID"));
                d.setType(rs.getString("type"));
                d.setName(rs.getString("name"));
                d.setDiscrip(rs.getString("discrip"));
                d.setPrice(rs.getFloat("price"));
                list.add(d);
            }
            cn.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        
        return list;
    }
    
    public void editDish(){
        connect();
        String sql="UPDATE Dish SET type=?,name=?,discrip=?,price=? WHERE ID=?";
        try{
             PreparedStatement ps = cn.prepareStatement(sql);
             ps.setString(1, this.getType());
             ps.setString(2, this.getName());
             ps.setString(3, this.getDiscrip());
             ps.setFloat(4, this.getPrice());
             ps.setInt(5, this.getID());
             
             ps.executeUpdate();
             cn.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
//          try{
//             PreparedStatement ps = this.cn.prepareStatement(sql);
//             ps.setString(1, "starter");
//             ps.setString(2, "sup meo");
//             ps.setString(3, "chi co ga thoi");
//             ps.setFloat(4, 70000);
//             ps.setInt(5, 1);
//             
//            ps.executeUpdate();
//            cn.close();
//        } catch (Exception ex) {
//            ex.printStackTrace();
//        }
    }
}
