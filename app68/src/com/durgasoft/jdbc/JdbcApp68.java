
package com.durgasoft.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import javax.sql.rowset.CachedRowSet;
import javax.sql.rowset.RowSetProvider;

public class JdbcApp68 {

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
                int eno = rowSet.getInt("ENO");
                float esal = rowSet.getFloat("ESAL");
                if(esal < 5500){
                    rowSet.deleteRow();
                    System.out.println("Employee "+eno+" Deleted Successfully !!!");
                }
            }
            rowSet.moveToCurrentRow();
            rowSet.acceptChanges();
            
        } catch (Exception e) {
            e.printStackTrace();
        }finally{
            try {
                conn.close();
                rowSet.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
    
}
