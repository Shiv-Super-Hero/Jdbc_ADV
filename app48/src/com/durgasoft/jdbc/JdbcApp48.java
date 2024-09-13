
package com.durgasoft.jdbc;

import java.io.File;
import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

public class JdbcApp48 {

    public static void main(String[] args) {
        Connection conn = null;
        PreparedStatement pst = null;
        FileInputStream fis = null;
        try {
            Class.forName("oracle.jdbc.OracleDriver");
            conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system","durga");
            pst =  conn.prepareStatement("insert into emp2 values(?,?)");
            pst.setInt(1, 111);
            File file = new File("C:\\Users\\Shiv Narayan Singh\\OneDrive\\Pictures\\Saved Pictures\\IMG_2877.JPG");
            fis = new FileInputStream(file);
            pst.setBinaryStream(2, fis,file.length());
            pst.executeUpdate();
            System.out.println("Image Stored Successfully !!!");
            
        } catch (Exception e) {
            e.printStackTrace();
        }finally{
            try {
                fis.close();
                conn.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
    
}
