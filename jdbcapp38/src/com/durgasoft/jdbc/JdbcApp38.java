package com.durgasoft.jdbc;

import java.sql.Connection;
import java.util.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

public class JdbcApp38 {

	public static void main(String[] args) {
		Connection conn = null;
		PreparedStatement pst = null;
		
		try {
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/durgadb","root","durga");
			pst = conn.prepareStatement("insert into student values(?,?,?,?)");
			
			pst.setString(1, "S-111");
			pst.setString(2, "AAA");
			java.sql.Date dob = java.sql.Date.valueOf("1999-10-30");
			pst.setDate(3, dob);
			
			Date d1 = new Date();
			long val = d1.getTime();
			java.sql.Date doj = new java.sql.Date(val);
			pst.setDate(4, doj);
			
			int rowCount = pst.executeUpdate();
			if(rowCount == 1) {
				System.out.println("Student Inserted Successfully");
			}else {
				System.out.println("Student Insertion Failure");
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			try {
				conn.close();
			} catch (Exception e) {
				e.printStackTrace();			}
		}

	}

}
