package com.durgasoft.jdbc;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class JdbcApp31 {

	public static void main(String[] args) {
		Connection conn = null;
		Statement st = null;
		ResultSet rs = null;
		BufferedReader br = null;
		try {
//			Class.forName("com.mysql.cj.jdbc.Driver");
//			conn  = DriverManager.getConnection("jdbc:mysql://localhost:3306/durgadb","root","durga");
			
//			Class.forName("oracle.jdbc.OracleDriver");
//			conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system","durga");
			
			Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
			conn = DriverManager.getConnection("jdbc:odbc:shiv","system","durga");
			st = conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);
			rs = st.executeQuery("select * from emp1");
			rs.moveToInsertRow();
			br = new BufferedReader(new InputStreamReader(System.in));
			
			while(true) {
				System.out.print("Employee Number  : ");
				int eno = Integer.parseInt(br.readLine());
				System.out.print("Employee Name  : ");
				String ename = br.readLine();
				System.out.print("Employee Salary  : ");
				float esal = Float.parseFloat(br.readLine());
				System.out.print("Employee Address  : ");
				String eaddr = br.readLine();
				
				rs.updateInt(1, eno);
				rs.updateString(2, ename);
				rs.updateFloat(3, esal);
				rs.updateString(4, eaddr);
				
				rs.insertRow();
				System.out.println("Employee Inserted Successfully !!!");
				
				System.out.print("One more Employee[Yes/No]?   :");
				String option = br.readLine();
				if(option.equalsIgnoreCase("yes")) {
					continue;
				}else {
					break;
				}
			}
			
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
