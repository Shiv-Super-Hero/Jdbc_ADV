
package com.durgasoft.jdbc;

import java.io.FileReader;
import java.sql.Connection;
import java.sql.DriverManager;
import javax.sql.rowset.RowSetProvider;
import javax.sql.rowset.WebRowSet;

public class JdbcApp70 {

    public static void main(String[] args) {
        Connection conn = null;
        WebRowSet rowSet = null;
        FileReader reader = null;
        
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/durgadb","root","durga");
            conn.setAutoCommit(false);
            rowSet = RowSetProvider.newFactory().createWebRowSet();
            rowSet.setCommand("select * from emp1");
            rowSet.execute(conn);
            
            reader = new FileReader("C:\\Users\\Shiv Narayan Singh\\Music\\Bhajan\\abc.xml");
            rowSet.readXml(reader);
            rowSet.moveToCurrentRow();
            rowSet.acceptChanges();
            
            
        } catch (Exception e) {
            e.printStackTrace();
        }finally{
            try {
                reader.close();
                rowSet.close();
                conn.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
    
}
