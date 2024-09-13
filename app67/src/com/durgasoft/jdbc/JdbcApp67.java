
package com.durgasoft.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import javax.sql.rowset.CachedRowSet;
import javax.sql.rowset.RowSetProvider;

public class JdbcApp67 {

    public static void main(String[] args) {
        Connection conn = null;
        CachedRowSet rowSet = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/durgadb","root","durga");
            conn.setAutoCommit(false);
            
            rowSet = RowSetProvider.newFactory().createCachedRowSet();
            rowSet.setCommand("select * from emp1");
            rowSet.execute(conn);
            
            while(rowSet.next()){
                float esal = rowSet.getFloat("ESAL");
                if(esal<6500){
                    float newSal = esal+500;
                    rowSet.updateFloat(3, newSal);
                    rowSet.updateRow();
                    System.out.println("Employee "+rowSet.getInt("ENO")+" Updated Successfully !!!");
                }
            }
            rowSet.moveToCurrentRow();
            rowSet.acceptChanges();
            
        } catch (Exception e) {
            e.printStackTrace();
        }finally{
            try {
                rowSet.close();
                conn.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
    
}
