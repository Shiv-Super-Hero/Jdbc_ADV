package com.durgasoft.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

public class JdbcApp40 {

	public static void main(String[] args) {
		Connection conn = null;
		Statement st = null;
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/durgadb","root","durga");
			st = conn.createStatement();
			st.addBatch("insert into emp1 values(555,'EEE',8500,'Hyd')");
			st.addBatch("update emp1 set ESAL = ESAL - 299 where ESAL < 6500");
			st.addBatch("delete from emp1 where ENO = 111");
			int[] rowCounts = st.executeBatch();
			for(int rowCount: rowCounts) {
				System.out.println("records Manipulated : "+rowCount);
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
