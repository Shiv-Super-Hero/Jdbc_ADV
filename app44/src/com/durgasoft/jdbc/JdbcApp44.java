/*
create or replace function getAVG(no1 IN number, no2 IN number) return float
as
    sal1 float;
    sal2 float;
BEGIN
    select ESAL into sal1 from emp1 where ENO = no1;
    select ESAL into sal2 from emp1 where ENO = no2;
    return (sal1+sal2)/2;
END getAVG;
/
*/
package com.durgasoft.jdbc;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Types;

public class JdbcApp44 {

    public static void main(String[] args) {
        Connection conn = null;
        CallableStatement cst = null;
        BufferedReader br = null;
        
        try {
            Class.forName("oracle.jdbc.OracleDriver");
            conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system","durga");
            br = new BufferedReader(new InputStreamReader(System.in));
            cst = conn.prepareCall("{? = call getAVG(?,?)}");
            System.out.print("First Employee Number  : ");
            int eno1 = Integer.parseInt(br.readLine());
            System.out.print("Second Employee Number  : ");
            int eno2 = Integer.parseInt(br.readLine());
            cst.setInt(2, eno1);
            cst.setInt(3, eno2);
            cst.registerOutParameter(1, Types.FLOAT);
            cst.execute();
            System.out.print("AVG Salary  : "+cst.getFloat(1));
            
        } catch (Exception e) {
            e.printStackTrace();
        }finally{
            try {
                
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
    
}
