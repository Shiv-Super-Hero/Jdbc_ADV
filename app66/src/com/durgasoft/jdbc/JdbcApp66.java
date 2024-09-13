
package com.durgasoft.jdbc;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.DriverManager;
import javax.sql.rowset.CachedRowSet;
import javax.sql.rowset.RowSetFactory;
import javax.sql.rowset.RowSetProvider;

public class JdbcApp66 {


    public static void main(String[] args) {
        Connection conn = null;
        CachedRowSet rowSet = null;
        BufferedReader br = null;
        
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/durgadb","root","durga");
            conn.setAutoCommit(false);
            RowSetFactory factory = RowSetProvider.newFactory();
            rowSet = factory.createCachedRowSet();
            rowSet.setCommand("select * from emp1");
            rowSet.execute(conn);
            rowSet.moveToInsertRow();
            br = new BufferedReader(new InputStreamReader(System.in));
            while(true){
                System.out.print("Employee Number  : ");
                int eno = Integer.parseInt(br.readLine());
                System.out.print("Employee Name  : ");
                String ename = br.readLine();
                System.out.print("Employee Salary  : ");
                float esal = Float.parseFloat(br.readLine());
                System.out.print("Employee Address  : ");
                String eaddr = br.readLine();
                
                rowSet.updateInt(1, eno);
                rowSet.updateString(2, ename);
                rowSet.updateFloat(3, esal);
                rowSet.updateString(4, eaddr);
                
                rowSet.insertRow();
                System.out.println("Employee "+eno+" Inserted Successfully");
                System.out.print("Onemore Employee[Yes/No] ? :");
                String option = br.readLine();
                if(option.equalsIgnoreCase("yes")){
                    continue;
                }else{
                    break;
                }
                
            }
            rowSet.moveToCurrentRow();
            rowSet.acceptChanges();
            
        } catch (Exception e) {
            e.printStackTrace();
        }finally{
            try {
                
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
    
}
