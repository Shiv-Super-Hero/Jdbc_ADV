package com.durgasoft.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

public class JdbcApp52 {

    public static void main(String[] args) {
        Connection conn = null;
        Statement st = null;
        
        try{
            Class.forName("oracle.jdbc.OracleDriver");
            conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system","durga");
            conn.setAutoCommit(false);
            st = conn.createStatement();
            st.executeUpdate("insert into student1 values('S111','AAA',77)");
            st.executeUpdate("insert into student1 values('S222','BBB',78)");
            st.executeUpdate("insert into student1 values('S333','CCC',79)");
            conn.commit();
            System.out.println("Transaction Success !!!");
        }catch(Exception e){
            try {
                conn.rollback();
            } catch (Exception e1) {
                e1.printStackTrace();
            }
            System.out.println("Transaction Failure");
            e.printStackTrace();
        }finally{
            try {
                    conn.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
    
}
