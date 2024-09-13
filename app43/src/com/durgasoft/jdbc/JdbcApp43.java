/*
create or replace procedure getSal(no IN number , sal OUT float)
as
BEGIN
    select ESAL into sal from emp1 where ENO = no;
END getSal;
/
*/
package com.durgasoft.jdbc;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Types;

public class JdbcApp43 {

    public static void main(String[] args) {
        Connection conn = null;
        CallableStatement cst = null;
        BufferedReader br = null;
        try {
            Class.forName("oracle.jdbc.OracleDriver");
            conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system","durga");
            br = new BufferedReader(new InputStreamReader(System.in));
            cst = conn.prepareCall("{call getSal(?,?)}");
            System.out.print("Employee Number  : ");
            int eno = Integer.parseInt(br.readLine());
            cst.setInt(1,eno);
            cst.registerOutParameter(2, Types.FLOAT);
            cst.execute();
            System.out.println("Employee Salary  : "+cst.getFloat(2));
            
            
        } catch (Exception e) {
            e.printStackTrace();
        }finally{
            try {
                br.close();
                conn.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
    
}
