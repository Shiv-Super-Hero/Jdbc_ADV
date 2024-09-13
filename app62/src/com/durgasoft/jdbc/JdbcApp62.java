
package com.durgasoft.jdbc;

import com.sun.rowset.JdbcRowSetImpl;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import javax.sql.rowset.JdbcRowSet;

public class JdbcApp62 {

    public static void main(String[] args) {
        JdbcRowSet rowSet = null;
        BufferedReader br = null;
        try {
            rowSet = new JdbcRowSetImpl();
            rowSet.setUrl("jdbc:mysql://localhost:3306/durgadb");
            rowSet.setUsername("root");
            rowSet.setPassword("durga");
            rowSet.setCommand("select * from emp1");
            rowSet.execute();
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
                System.out.println("Employee inserted Successfully");
                System.out.print("One more Employee[Yes/No] ? : ");
                String option = br.readLine();
                if(option.equalsIgnoreCase("Yes")){
                    continue;
                }else{
                    break;
                }
                
            }
            
        } catch (Exception e) {
            e.printStackTrace();
        }finally{
            try {
                rowSet.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
    
}
