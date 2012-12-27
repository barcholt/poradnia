package org.part_ter;

import java.sql.*;

public class Connection_mysql {

	private String url = "jdbc:mysql://mysql-585316.vipserv.org/barcholt_mpr";
	private String user = "barcholt_mpr";
	private String password = "barcholt_mpr";
	Connection connect;
	Statement stmt;
	
	public Connection_mysql() {
		getConnect();
		getStat();

	}
	void getConnect() {
		try {
			connect = DriverManager.getConnection(url, user, password);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	void getStat() {
		try {
			stmt = connect.createStatement();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	
}
