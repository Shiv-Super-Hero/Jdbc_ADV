
package com.durgasoft.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import javax.sql.rowset.CachedRowSet;
import javax.sql.rowset.JoinRowSet;
import javax.sql.rowset.RowSetFactory;
import javax.sql.rowset.RowSetProvider;

public class JdbcApp73 {

    public static void main(String[] args) {
        CachedRowSet rowSet1 = null;
        CachedRowSet rowSet2 = null;
        JoinRowSet rowSet = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/durgadb","root","durga");
            conn.setAutoCommit(false);
            
            RowSetFactory factory = RowSetProvider.newFactory();
            rowSet1 = factory.createCachedRowSet();
            rowSet1.setCommand("select * from student");
            rowSet1.execute(conn);
            
            rowSet2 = factory.createCachedRowSet();
            rowSet2.setCommand("select * from course");
            rowSet2.execute(conn);
            
            rowSet = factory.createJoinRowSet();
            rowSet.addRowSet(rowSet1,"CID");
            rowSet.addRowSet(rowSet2,"CID");
            
            System.out.println("SID\tSANME\tSADDR\tCID\tCNAME\tCCOST");
            System.out.println("-------------------------------------");
            while(rowSet.next()){
                System.out.print(rowSet.getString(1)+"\t");
                System.out.print(rowSet.getString(2)+"\t");
                System.out.print(rowSet.getString(3)+"\t");
                System.out.print(rowSet.getString(4)+"\t");
                System.out.print(rowSet.getString(5)+"\t");
                System.out.print(rowSet.getInt(6)+"\n");   
            }
            
        } catch (Exception e) {
            e.printStackTrace();
        }finally{
            try {
                rowSet1.close();
                rowSet2.close();
                rowSet.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
    
}
