
package com.durgasoft.jdbc;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

public class JdbcApp54 {

    public static void main(String[] args) {
        Connection oracleConn = null;
        Connection mysqlConn = null;
        
        PreparedStatement oraclePst = null;
        PreparedStatement mysqlPst = null;
        
        BufferedReader br = null;
        
        try {
            Class.forName("oracle.jdbc.OracleDriver");
            Class.forName("com.mysql.cj.jdbc.Driver");
            
            oracleConn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system","durga");
            mysqlConn = DriverManager.getConnection("jdbc:mysql://localhost:3306/durgadb","root","durga");
            
            oracleConn.setAutoCommit(false);
            mysqlConn.setAutoCommit(false);
            
            oraclePst = oracleConn.prepareStatement("update account set BALANCE = BALANCE - ? where ACCNO = ?");
            mysqlPst =  mysqlConn.prepareStatement("update account set BALANCE = BALANCE + ? where ACCNO = ?");
            
            br = new BufferedReader(new InputStreamReader(System.in));
            System.out.print("Source Account  : ");
            String sourceAccount = br.readLine();
            System.out.print("Target Account  : ");
            String targetAccount = br.readLine();
            System.out.print("Transfer Amount  : ");
            int transferAmount = Integer.parseInt(br.readLine());
            
            oraclePst.setInt(1, transferAmount);
            oraclePst.setString(2, sourceAccount);
            
            mysqlPst.setInt(1, transferAmount);
            mysqlPst.setString(2, targetAccount);
            
            int rowCount1 = oraclePst.executeUpdate();
            int rowCount2 = mysqlPst.executeUpdate();
            
            if(rowCount1 == 1 && rowCount2 == 1){
                oracleConn.commit();
                mysqlConn.commit();
                System.out.println("Transaction Success !!!");
            }else{
                oracleConn.rollback();
                mysqlConn.rollback();
                System.out.println("Transaction Failure !!!");
            }
            
        } catch (Exception e) {
            try {
                oracleConn.rollback();
                mysqlConn.rollback();
                System.out.println("Transaction Phailure !!!");
            } catch (Exception e1) {
                e1.printStackTrace();
            }
            e.printStackTrace();
        }finally{
            try {
                br.close();
                oracleConn.close();
                mysqlConn.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
    
}
