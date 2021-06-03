/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pkg18_b18dccn100;

import com.microsoft.sqlserver.jdbc.SQLServerDataSource;
import java.sql.Connection;
import java.sql.SQLException;

/**
 *
 * @author ASUS
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
          var server = "DUNG-NT\\SQLEXPRESS";
        var user = "sa";
        var pass = "dung031200";
        var db = "Restaurant";
        var port = 1433;
        SQLServerDataSource ds = new SQLServerDataSource();
        ds.setUser(user);
        ds.setPassword(pass);
        ds.setDatabaseName(db);
        ds.setServerName(server);
        ds.setPortNumber(port);
        try( Connection conn =  ds.getConnection()){
            System.out.println("thanh cong");
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
    }
    

