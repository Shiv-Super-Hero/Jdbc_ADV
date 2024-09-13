package com.durgasoft.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

public class JdbcApp41 {

	public static void main(String[] args) {
		Connection conn = null;
		PreparedStatement pst = null;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/durgadb","root","durga");
			pst = conn.prepareStatement("insert into emp1 values(?,?,?,?)");
			
			pst.setInt(1, 111);
			pst.setString(2, "AAA");
			pst.setFloat(3, 5000);
			pst.setString(4, "Hyd");
			pst.addBatch();
			
			pst.setInt(1, 666);
			pst.setString(2, "FFF");
			pst.setFloat(3, 9000);
			pst.setString(4, "Hyd");
			pst.addBatch();
			
			pst.setInt(1, 777);
			pst.setString(2, "GGG");
			pst.setFloat(3, 9500);
			pst.setString(4, "Hyd");
			pst.addBatch();
			
			pst.setInt(1, 888);
			pst.setString(2, "HHH");
			pst.setFloat(3, 10000);
			pst.setString(4, "Hyd");
			pst.addBatch();
			
			int[] rowCounts = pst.executeBatch();
			for(int rowCount:rowCounts) {
				System.out.println("Records Inserted : "+rowCount);
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
