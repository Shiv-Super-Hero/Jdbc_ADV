
package com.durgasoft.jdbc;

import java.io.FileWriter;
import javax.sql.rowset.RowSetProvider;
import javax.sql.rowset.WebRowSet;

public class JdbcApp69 {

    public static void main(String[] args) {
        WebRowSet rowSet = null;
        FileWriter fw = null;
        try {
            rowSet = RowSetProvider.newFactory().createWebRowSet();
            rowSet.setUrl("jdbc:mysql://localhost:3306/durgadb");
            rowSet.setUsername("root");
            rowSet.setPassword("durga");
            rowSet.setCommand("select * from emp1");
            rowSet.execute();
            fw = new FileWriter("C:\\Users\\Shiv Narayan Singh\\Music\\Bhajan\\abc.xml");
            rowSet.writeXml(fw);
            
        } catch (Exception e) {
            e.printStackTrace();
        }finally{
            try {
                rowSet.close();
                fw.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
    
}
