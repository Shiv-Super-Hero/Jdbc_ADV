
package com.durgasoft.jdbc;

import java.io.FileWriter;
import java.io.Reader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class JdbcApp51 {
    
    public static void main(String[] args) {
        Connection conn = null;
        PreparedStatement pst = null;
        ResultSet rs = null;
        Reader r = null;
        FileWriter fw = null;
        
        try {
            Class.forName("oracle.jdbc.OracleDriver");
            conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system","durga");
            pst = conn.prepareStatement("select * from apps where APP_NAME = ?");
            pst.setString(1, "App1");
            rs = pst.executeQuery();
            rs.next();
            System.out.println("Application Name  :  "+rs.getString("APP_NAME"));
            fw = new FileWriter("D:\\mywebapp\\testapp\\WEB-INF\\new.xml");
            r = rs.getCharacterStream(2);
            int val = r.read();
            while(val != -1){
                fw.write(val);
                val = r.read();
            }
            
        } catch (Exception e) {
        
            e.printStackTrace();
        }finally{
            try {
                r.close();
                fw.close();
                conn.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
    }
    }
    
}
