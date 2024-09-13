
package com.durgasoft.jdbc;

import java.io.FileReader;
import java.sql.Connection;
import java.sql.DriverManager;
import javax.sql.rowset.RowSetFactory;
import javax.sql.rowset.RowSetProvider;
import javax.sql.rowset.WebRowSet;

public class JdbcApp72 {

    public static void main(String[] args) {
        WebRowSet rowSet = null;
        FileReader fr = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/durgadb","root","durga");
            conn.setAutoCommit(false);
            RowSetFactory factory = RowSetProvider.newFactory();
            rowSet = factory.createWebRowSet();
            rowSet.setCommand("select * from emp1");
            rowSet.execute(conn);
            fr = new FileReader("C:\\Users\\Shiv Narayan Singh\\Music\\Bhajan\\emp.xml");
            rowSet.readXml(fr);
            rowSet.moveToCurrentRow();
            rowSet.acceptChanges();
            System.out.println("Insert,Update and delete operations are Successful");
        } catch (Exception e) {
            e.printStackTrace();
        }
        finally{
            try {
                fr.close();
                rowSet.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
    
}
