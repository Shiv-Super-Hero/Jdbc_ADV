package com.durgasoft.jdbc;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

public class JdbcApp34 {

	public static void main(String[] args) {
		Connection conn = null;
		PreparedStatement pst = null;
		BufferedReader br = null;
		
		try {
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/durgadb","root","durga");
			pst = conn.prepareStatement("insert into emp1 values(?,?,?,?)");
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
				
				pst.setInt(1, eno);
				pst.setString(2, ename);
				pst.setFloat(3, esal);
				pst.setString(4, eaddr);
				
				int rowCount = pst.executeUpdate();
				if(rowCount==1) {
					System.out.println(eno+" Employee Inserted Successfully");
				}else {
					System.out.println("Employee Insertion Failure");
				}
				System.out.print("Onemore Employee[Yes/No]? :");
				String option = br.readLine();
				
				if(option.equalsIgnoreCase("Yes")) {
					continue;
				}else {
					break;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
