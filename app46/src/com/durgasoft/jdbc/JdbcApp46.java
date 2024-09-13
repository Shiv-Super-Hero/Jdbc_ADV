/*
create or replace procedure getAllEmployees(salRange IN float , emps OUT SYS_REFCURSOR)
as
BEGIN
    open emps for
        select * from emp1 where ESAL < salRange;
END getAllEmployees;
/
*/
package com.durgasoft.jdbc;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import oracle.jdbc.OracleTypes;

public class JdbcApp46 {

    public static void main(String[] args) {
        Connection conn = null;
        CallableStatement cst = null;
        ResultSet rs = null;
        
        try {
            Class.forName("oracle.jdbc.OracleDriver");
            conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system","durga");
            cst = conn.prepareCall("{call getAllEmployees(?,?)}");
            cst.setFloat(1, 60000);
            cst.registerOutParameter(2,OracleTypes.CURSOR);
            cst.execute();
            rs = (ResultSet)cst.getObject(2);
            System.out.println("ENO\tENAME\tESAL\tEADDR");
            System.out.println("-------------------------");
            while(rs.next()){
                System.out.print(rs.getInt("ENO")+"\t");
                System.out.print(rs.getString("ENAME")+"\t");
                System.out.print(rs.getFloat("ESAL")+"\t");
                System.out.print(rs.getString("EADDR")+"\n");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }finally{
            try {
                conn.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
    
}
