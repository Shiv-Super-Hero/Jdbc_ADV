
package com.durgasoft.jdbc;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

public class JdbcApp53 {
    
    public static void main(String[] args) {
        Connection conn = null;
        Statement st = null;
        BufferedReader br = null;
        
        try{
            Class.forName("oracle.jdbc.OracleDriver");
            conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system","durga");
            conn.setAutoCommit(false);
            st = conn.createStatement();
            br = new BufferedReader(new InputStreamReader(System.in));
            System.out.print("Source Account  : ");
            String sourceAccount = br.readLine();
            System.out.print("Target Account  : ");
            String targetAccount = br.readLine();
            System.out.print("Transfer Amount  : ");
            int transferAmount = Integer.parseInt(br.readLine());
            
            int rowCount1 = st.executeUpdate("update account set BALANCE = BALANCE - "+transferAmount+" where ACCNO = '"+sourceAccount+"'");
            int rowCount2 = st.executeUpdate("update account set BALANCE = BALANCE + "+transferAmount+" where ACCNO = '"+targetAccount+"'");
            if(rowCount1 == 1 && rowCount2 == 1){
                conn.commit();
                System.out.println("Transaction Success !!!");
            }else{
                conn.rollback();
                System.out.println("Transaction Failure");
            }
            
            
        }catch(Exception e){
            try {
                conn.rollback();
                System.out.println("Transaction Failure !!!");
            } catch (Exception e1) {
                e1.printStackTrace();
            }
            
        }finally{
            try {
                br.close();
                conn.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
    
}
