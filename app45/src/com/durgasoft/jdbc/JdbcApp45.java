/*
create or replace function getEmpTotalSalary(salRange IN float) return float
as
totalSalary float;
BEGIN
    select sum(ESAL) into totalSalary from emp1 where ESAL < sal_Range;
    return totalSalary;
END getEmpTotalSalary;
/
*/
package com.durgasoft.jdbc;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Types;

public class JdbcApp45 {

    public static void main(String[] args) {
        Connection conn = null;
        CallableStatement cst = null;
        BufferedReader br = null;
        try {
            Class.forName("oracle.jdbc.OracleDriver");
            conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system","durga");
            br = new BufferedReader(new InputStreamReader(System.in));
            cst = conn.prepareCall("{? = call getEmpTotalSalary(?)}");
            System.out.print("Employee Salary Range  : ");
            float sal_Range = Float.parseFloat(br.readLine());
            cst.setFloat(2, sal_Range);
            cst.registerOutParameter(1, Types.FLOAT);
            cst.execute();
            System.out.println("Total Employees Salary  : "+cst.getFloat(1));
            
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
