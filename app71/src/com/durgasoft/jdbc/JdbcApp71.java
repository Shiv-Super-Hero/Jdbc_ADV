
package com.durgasoft.jdbc;

import java.io.FileOutputStream;
import javax.sql.rowset.WebRowSet;
import javax.sql.rowset.RowSetFactory;
import javax.sql.rowset.RowSetProvider;

public class JdbcApp71 {
    
    public static void main(String[] args) {
        WebRowSet rowSet = null;
        FileOutputStream fos = null;
        try {
            RowSetFactory factory = RowSetProvider.newFactory();
            rowSet = factory.createWebRowSet();
            rowSet.setUrl("jdbc:mysql://localhost:3306/durgadb");
            rowSet.setUsername("root");
            rowSet.setPassword("durga");
            rowSet.setCommand("select * from emp1");
            rowSet.execute();
            fos = new FileOutputStream("C:\\Users\\Shiv Narayan Singh\\Music\\Bhajan\\emp.xml");
            rowSet.writeXml(fos);
            System.out.println("Employee data retrieved from Database and send to C:\\Users\\Shiv Narayan Singh\\Music\\Bhajan\\emp.xml");
            
        } catch (Exception e) {
            e.printStackTrace();
        }finally{
            try {
                fos.close();
                rowSet.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
    
}
