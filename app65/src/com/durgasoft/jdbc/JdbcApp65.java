
package com.durgasoft.jdbc;

import javax.sql.rowset.CachedRowSet;
import javax.sql.rowset.RowSetFactory;
import javax.sql.rowset.RowSetProvider;

public class JdbcApp65 {

    public static void main(String[] args) {
        CachedRowSet rowSet = null;
        
        try {
            RowSetFactory factory = RowSetProvider.newFactory();
            rowSet = factory.createCachedRowSet();
            rowSet.setUrl("jdbc:mysql://localhost:3306/durgadb");
            rowSet.setUsername("root");
            rowSet.setPassword("durga");
            rowSet.setCommand("select * from emp1");
            rowSet.execute();
            System.out.println("Emmployee Details in Forward Direction");
            System.out.println("ENO\tENAME\tESAL\tEADDR");
            System.out.println("-------------------------");
            while(rowSet.next()){
                System.out.print(rowSet.getInt("ENO")+"\t");
                System.out.print(rowSet.getString("ENAME")+"\t");
                System.out.print(rowSet.getFloat("ESAL")+"\t");
                System.out.print(rowSet.getString("EADDR")+"\n");
            }
            System.out.println();
            
            System.out.println("Emmployee Details in Backward Direction");
            System.out.println("ENO\tENAME\tESAL\tEADDR");
            System.out.println("-------------------------");
            while(rowSet.previous()){
                System.out.print(rowSet.getInt("ENO")+"\t");
                System.out.print(rowSet.getString("ENAME")+"\t");
                System.out.print(rowSet.getFloat("ESAL")+"\t");
                System.out.print(rowSet.getString("EADDR")+"\n");
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
