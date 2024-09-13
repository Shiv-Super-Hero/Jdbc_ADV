package com.durgasoft.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.Statement;

public class JdbcApp20 {

	public static void main(String[] args) {
		Connection conn  = null;
		Statement st = null;
		ResultSet rs = null;
		
		try {
			Class.forName("oracle.jdbc.OracleDriver");
			conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system","durga");
			st = conn.createStatement();
			rs = st.executeQuery("select * from emp1");
			ResultSetMetaData md = rs.getMetaData();
			int count = md.getColumnCount();
			for(int i=1;i<=count;i++) {
				System.out.print(md.getColumnName(i)+"\t");
				
			}
			System.out.println();
			System.out.println("---------------------------------");
			while(rs.next()) {
				for(int i=1;i<=count;i++) {
					System.out.print(rs.getString(i)+"\t");
				}
				System.out.println();
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
