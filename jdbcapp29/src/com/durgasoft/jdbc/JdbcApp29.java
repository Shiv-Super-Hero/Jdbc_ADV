package com.durgasoft.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class JdbcApp29 {

	public static void main(String[] args) {
		Connection conn = null;
		Statement st = null;
		ResultSet rs = null;
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/durgadb?useSSL=false&serverTimezone=UTC","root","durga");
			st = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_UPDATABLE);
			rs = st.executeQuery("select * from emp1");
			System.out.println("Employee Details Before Updations");
			System.out.println("ENO\tENAME\tESAL\tEADDR");
			System.out.println("---------------------------------");
			while(rs.next()) {
				System.out.print(rs.getInt("ENO")+"\t");
				System.out.print(rs.getString("ENAME")+"\t");
				System.out.print(rs.getFloat("ESAL")+"\t");
				System.out.print(rs.getString("EADDR")+"\n");
			}
			System.out.println("Application is in Pausing State, please provide updations on DB");
			System.in.read();
			rs.beforeFirst();
			
			System.out.println("Employee Details After Updations");
			System.out.println("ENO\tENAME\tESAL\tEADDR");
			System.out.println("---------------------------------");
			while(rs.next()) {
				rs.refreshRow();
				System.out.print(rs.getInt("ENO")+"\t");
				System.out.print(rs.getString("ENAME")+"\t");
				System.out.print(rs.getFloat("ESAL")+"\t");
				System.out.print(rs.getString("EADDR")+"\n");
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
