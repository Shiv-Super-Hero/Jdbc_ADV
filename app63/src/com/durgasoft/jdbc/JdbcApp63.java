
package com.durgasoft.jdbc;

import com.sun.rowset.JdbcRowSetImpl;
import javax.sql.rowset.JdbcRowSet;

public class JdbcApp63 {

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
                if(esal<7000){
                    float newSal = esal+500;
                    rowSet.updateFloat(3, newSal);
                    rowSet.updateRow();
                    System.out.println("Employee "+rowSet.getInt("ENO")+" Updated Successfully");
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
