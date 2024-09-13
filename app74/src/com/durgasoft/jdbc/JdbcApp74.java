
package com.durgasoft.jdbc;

import com.durgasoft.factory.ConnectionFactory;
import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Properties;
import oracle.jdbc.OracleDriver;

public class JdbcApp74 {

    public static void main(String[] args)throws Exception {
        
        //Class.forName("oracle.jdbc.OracleDriver");
        //OracleDriver driver = new OracleDriver();
        //DriverManager.registerDriver(new OracleDriver());
        
        //Connection conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system","durga");
//        Properties p = new Properties();
//        p.setProperty("user", "system");
//        p.setProperty("password", "durga");
        
//        Properties p = new Properties();
//        p.load(new FileInputStream("D:\\Project\\jdbc\\NetBeans\\jdbcapp43\\app74\\src\\db.properties"));
//        Class.forName(p.getProperty("driverClass"));
//        Connection conn = DriverManager.getConnection(p.getProperty("driverUrl"),p.getProperty("user"),p.getProperty("password"));

        Connection conn = ConnectionFactory.getConnection();
        Statement st = conn.createStatement();
        ResultSet rs = st.executeQuery("select *  from emp1");
        System.out.println("ENO\tENAME\tESAL\tEADDR");
        System.out.println("---------------------------");
        while(rs.next()){
            System.out.print(rs.getInt("ENO")+"\t");
            System.out.print(rs.getString("ENAME")+"\t");
            System.out.print(rs.getFloat("ESAL")+"\t");
            System.out.print(rs.getString("EADDR")+"\n");
        }
        conn.close();
    }
    
}
