/*
create or replace function getAllStudents return SYS_REFCURSOR
AS
students SYS_REFCURSOR;
BEGIN
    open students for select * from student;
    return students;
END getAllStudents;
/
*/
package com.durgasoft.jdbc;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import oracle.jdbc.internal.OracleTypes;


public class JdbcApp47 {
    public static void main(String[] args) {
        Connection conn = null;
        CallableStatement cst = null;
        ResultSet rs = null;
        try {
            Class.forName("oracle.jdbc.OracleDriver");
            conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system","durga");
            cst = conn.prepareCall("{? = call getAllStudents}");
            cst.registerOutParameter(1, OracleTypes.CURSOR);
            cst.execute();
            rs = (ResultSet)cst.getObject(1);
            System.out.println("SID\tSNAME\tSADDR");
            System.out.println("-----------------------");
            while(rs.next()){
                System.out.print(rs.getString("SID")+"\t");
                System.out.print(rs.getString("SNAME")+"\t");
                System.out.print(rs.getString("SADDR")+"\n");
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
