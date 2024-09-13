
package com.durgasoft.jdbc;

import java.io.FileOutputStream;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class JdbcApp49 {
    
    public static void main(String[] args) {
        Connection conn = null;
        PreparedStatement pst = null;
        FileOutputStream fos = null;
        InputStream is = null;
        ResultSet rs = null;
        
        try {
            Class.forName("oracle.jdbc.OracleDriver");
            conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system","durga");
            pst = conn.prepareStatement("select * from emp2  where ENO = ?");
            pst.setInt(1, 111);
            rs = pst.executeQuery();
            rs.next();
            System.out.println("Employee Number  : "+rs.getInt("ENO"));
            fos = new FileOutputStream("C:\\Users\\Shiv Narayan Singh\\OneDrive\\Pictures\\Saved Pictures\\xyz.JPG");
            is = rs.getBinaryStream(2);
            int val = is.read();
            while(val != -1){
                fos.write(val);
                val = is.read();
            }
            
        } catch (Exception e) {
            e.printStackTrace();
        }finally{
            try {
                is.close();
                fos.close();
                conn.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
    
}
