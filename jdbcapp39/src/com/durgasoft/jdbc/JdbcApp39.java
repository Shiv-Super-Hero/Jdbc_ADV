package com.durgasoft.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class JdbcApp39 {

	public static void main(String[] args) {
		Connection conn = null;
		PreparedStatement pst = null;
		ResultSet rs = null;
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/durgadb","root","durga");
			pst = conn.prepareStatement("select * from student where SID = ?");
			pst.setString(1, "S-111");
			rs = pst.executeQuery();
			boolean b = rs.next();
			if(b == true) {
				System.out.println("Student Details");
				System.out.println("------------------------");
				System.out.println("Student Id  : "+rs.getString("SID"));
				System.out.println("Student NAME  : "+rs.getString("SID"));
				System.out.println("Student DOB  : "+rs.getDate("SDOB"));
				System.out.println("Student DOJ  : "+rs.getDate("SDOJ"));
			}else {
				System.out.println("Student Not Existed");
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			try {
				conn.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

	}

}
