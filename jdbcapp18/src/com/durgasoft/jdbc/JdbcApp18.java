package com.durgasoft.jdbc;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;

public class JdbcApp18 {

	public static void main(String[] args) {
		Connection conn  = null;
		
		try {
			Class.forName("oracle.jdbc.OracleDriver");
			conn  = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system","durga");
			DatabaseMetaData md = conn.getMetaData();
			System.out.println(md.getDatabaseProductName());
			System.out.println(md.getDatabaseProductVersion());
			System.out.println(md.getDriverMajorVersion());
			System.out.println(md.getDriverMinorVersion());
			System.out.println(md.getSQLKeywords());
			System.out.println(md.getStringFunctions());
			System.out.println(md.getNumericFunctions());
			System.out.println(md.supportsBatchUpdates());
			System.out.println(md.supportsStoredProcedures());
	
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
