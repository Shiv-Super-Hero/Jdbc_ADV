package com.durgasoft.jdbc;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;


public class JdbcApp35 {

	public static void main(String[] args) {
		Connection conn = null;
		PreparedStatement pst = null;
		BufferedReader br = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/durgadb","root","durga");
			pst = conn.prepareStatement("update emp1 set ESAL = ESAL+? where ESAL < ?");
			br = new BufferedReader(new InputStreamReader(System.in));
			System.out.print("Bonus Amount  : ");
			int bonus = Integer.parseInt(br.readLine());
			System.out.print("Salary Range  : ");
			float sal_Range = Float.parseFloat(br.readLine());
			
			pst.setInt(1, bonus);
			pst.setFloat(2, sal_Range);
			
			int rowCount = pst.executeUpdate();
			System.out.println("Employees Updated  : "+rowCount);
			
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
