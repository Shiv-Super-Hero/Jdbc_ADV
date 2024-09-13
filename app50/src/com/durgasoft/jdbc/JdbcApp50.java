
package com.durgasoft.jdbc;

import java.io.File;
import java.io.FileReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

public class JdbcApp50 {

    public static void main(String[] args) {
        Connection conn = null;
        PreparedStatement pst = null;
        FileReader fr = null;
        try {
            Class.forName("oracle.jdbc.OracleDriver");
            conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system","durga");
            pst = conn.prepareStatement("insert into apps values(?,?)");
            pst.setString(1, "App1");
            File file = new File("D:\\mywebapp\\testapp\\WEB-INF\\web.xml");
            fr = new FileReader(file);
            pst.setCharacterStream(2, fr,file.length());
            pst.executeUpdate();
            System.out.println("Document Stored in DB Successfully !!!");
            
            
        } catch (Exception e) {
            e.printStackTrace();
        }finally{
            try {
                fr.close();
                conn.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
    
}
