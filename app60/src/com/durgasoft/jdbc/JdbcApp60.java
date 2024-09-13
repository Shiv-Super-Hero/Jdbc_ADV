
package com.durgasoft.jdbc;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import org.logicalcobwebs.proxool.ProxoolDataSource;

public class JdbcApp60 {

    public static void main(String[] args) {
        Connection conn = null;
        Statement st = null;
        ResultSet rs = null;
        try {
            ProxoolDataSource ds = new ProxoolDataSource();
            ds.setDriver("oracle.jdbc.OracleDriver");
            ds.setDriverUrl("jdbc:oracle:thin:@localhost:1521:xe");
            ds.setUser("system");
            ds.setPassword("durga");
            ds.setMinimumConnectionCount(10);
            ds.setMaximumConnectionCount(20);
            
            conn = ds.getConnection();
            st = conn.createStatement();
            rs = st.executeQuery("select * from emp1");
            System.out.println("ENO\tENAME\tESAL\tEADDR");
            System.out.println("--------------------------");
            while(rs.next()){
                System.out.print(rs.getInt("ENO")+"\t");
                System.out.print(rs.getString("ENAME")+"\t");
                System.out.print(rs.getFloat("ESAL")+"\t");
                System.out.print(rs.getString("EADDR")+"\n");
            }
            
        } catch (Exception e) {
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
//            ProxoolDataSource ds = new ProxoolDataSource();
//            ds.setDriver("oracle.jdbc.OracleDriver");
//            ds.setDriverUrl("jdbc:oracle:thin:@localhost:1521:xe");
//            ds.setUser("system");
//            ds.setPassword("durga");
//            ds.setMinimumConnectionCount(10);
//            ds.setMaximumConnectionCount(20);
//            
//            conn = ds.getConnection();
//            st = conn.createStatement();
//            rs = st.executeQuery("select * from emp1");
//            System.out.println("ENO\tENAME\tESAL\tEADDR");
//            System.out.println("--------------------------");
//            while(rs.next()){
//                System.out.print(rs.getInt("ENO")+"\t");
//                System.out.print(rs.getString("ENAME")+"\t");
//                System.out.print(rs.getFloat("ESAL")+"\t");
//                System.out.print(rs.getString("EADDR")+"\n");