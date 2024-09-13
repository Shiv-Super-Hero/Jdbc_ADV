package com.durgasoft.jdbc;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

public class JdbcApp36 {

	public static void main(String[] args) {
		Connection conn = null;
		PreparedStatement pst = null;
		BufferedReader br = null;
		
		try {
			Class.forName("oracle.jdbc.OracleDriver");
			conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system","durga");
			pst = conn.prepareStatement("delete from emp1 where ESAL < ?");
			br = new BufferedReader(new InputStreamReader(System.in));
			System.out.print("Salary Range  : ");
			float sal_Range = Float.parseFloat(br.readLine());
			
			pst.setFloat(1, sal_Range);
			int rowCount = pst.executeUpdate();
			System.out.println("Employees Deleted  : "+rowCount);
			
			
		} catch (Exception e) {
			e.printStackTrace();		
			}finally {
			try {
				br.close();
				conn.close();
			} catch (Exception e) {
				e.printStackTrace();
			}	
			}
	}

}
