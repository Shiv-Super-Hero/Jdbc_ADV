
package com.durgasoft.jdbc;

import com.sun.rowset.JdbcRowSetImpl;
import javax.sql.rowset.JdbcRowSet;

public class JdbcApp64 {

    public static void main(String[] args) {
        JdbcRowSet rowSet = null;
        try {
            rowSet = new JdbcRowSetImpl();
            rowSet.setUrl("jdbc:mysql://localhost:3306/durgadb");
            rowSet.setUsername("root");
            rowSet.setPassword("durga");
            rowSet.setCommand("select * from emp1");
            rowSet.execute();
            
            while(rowSet.next()){
                float esal = rowSet.getFloat("ESAL");
                int eno = rowSet.getInt("ENO");
                if(esal<6500){
                    rowSet.deleteRow();
                    System.out.println("Employee "+eno+" Deleted Successfully");
                }
                
            }
            
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
