package com.durgasoft.service;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import com.durgasoft.beans.Employee;

public class PlayerService {
	Connection conn;
	Statement st;
	ResultSet rs;
	boolean state;
	public PlayerService() {
		try {
			Class.forName("oracle.jdbc.OracleDriver");
			conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system","durga");
			st = conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);
			rs = st.executeQuery("select * from emp1");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public Employee getEmployeeDetails(String label) {
		Employee emp = null;
		state = false;
		try {
			if(label.equals("First")) {
				state = rs.first();
			}
			if(label.equals("Next")) {
				state = rs.next();
			}
			if(label.equals("Previous")) {
				state = rs.previous();
			}
			if(label.equals("Last")) {
				state = rs.last();	
			}
			if(state == false) {
				emp = null;
			}else {
				emp = new Employee();
				emp.setEno(rs.getInt("ENO"));
				emp.setEname(rs.getString("ENAME"));
				emp.setEsal(rs.getFloat("ESAL"));
				emp.setEaddr(rs.getString("EADDR"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return emp;
		
	}
}
