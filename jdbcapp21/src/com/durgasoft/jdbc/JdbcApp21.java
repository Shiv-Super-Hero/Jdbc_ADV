package com.durgasoft.jdbc;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.Statement;

public class JdbcApp21 {

	public static void main(String[] args) {
		Connection conn = null;
		Statement st = null;
		ResultSet rs = null;
		BufferedReader br = null;
		try {
			Class.forName("oracle.jdbc.OracleDriver");
			conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system","durga");
			st = conn.createStatement();
			br = new BufferedReader(new InputStreamReader(System.in));
			System.out.print("SQL Query : ");
			String query = br.readLine();
			boolean b = st.execute(query);
			if(b==true) {
				rs = st.getResultSet();
				ResultSetMetaData md = rs.getMetaData();
				int count = md.getColumnCount();
				for(int i=1;i<=count;i++) {
					System.out.print(md.getColumnName(i)+"\t");
				}
				System.out.println();
				while(rs.next()) {
					for(int i=1;i<=count;i++) {
						System.out.print(rs.getString(i)+"\t");
					}
					System.out.println();
				}
			}else {
				int rowCount = st.getUpdateCount();
				System.out.println("RowCount : "+rowCount);
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
