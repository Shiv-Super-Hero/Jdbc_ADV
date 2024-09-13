
package com.durgasoft.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Savepoint;
import java.sql.Statement;

public class JdbcApp55 {
    
    public static void main(String[] args) {
        Connection conn = null;
        Statement st = null;
        
        try {
//            Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
//            conn = DriverManager.getConnection("jdbc:odbc:shiv","system","durga");

//            Class.forName("oracle.jdbc.OracleDriver");
//            conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system","durga");

            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/durgadb","root","durga");
            
            conn.setAutoCommit(false);
            st = conn.createStatement();
            st.executeUpdate("insert into emp1 values(111,'AAA',5000,'Hyd')");
            Savepoint sp = conn.setSavepoint();
            st.executeUpdate("insert into emp1 values(222,'BBB',6000,'Hyd')");
            //conn.rollback(sp);
            conn.releaseSavepoint(sp);
            st.executeUpdate("insert into emp1 values(333,'CCC',7000,'Hyd')");
            conn.commit();
            System.out.println("Transaction Successful");
            
            
        } catch (Exception e) {
            try {
                conn.rollback();
                System.out.println("Transaction Failure !!!");
            } catch (Exception e1) {
                e1.printStackTrace();
            }
            e.printStackTrace();
            System.out.println("Transaction Failure");
        }finally{
            try {
                conn.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
    
}
