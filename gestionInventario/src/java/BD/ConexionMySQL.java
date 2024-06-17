/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package BD;

import java.sql.Connection;
import java.sql.DriverManager;

/**
 *
 * @author yazmi
 */
public class ConexionMySQL {
    Connection conn;
     public Connection open(){
        String user = "root";
        String password = "123456";
        String url="jdbc:mysql://127.0.0.1:3306/inventarioCas?allowPublicKeyRetrieval=true&useSSL=false&useUnicode=true&characterEncoding=utf-8";
       
       try {
              Class.forName("com.mysql.cj.jdbc.Driver");
            conn =  DriverManager.getConnection(url, user, password);
            return conn;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    
     public void close()
    {
        if (conn!= null) 
        {
            try {
                conn.close();
            } 
            catch (Exception e) 
            {
                e.printStackTrace();
                System.out.println("Exception controlada.");
            }
        }
    }
    
}
