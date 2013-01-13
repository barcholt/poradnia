package org.part_ter;

import java.sql.*;
public class Login {

	private String user;
	private String passwd;
	private Connection connection;
	private Statement stmt;
	private PreparedStatement log;
	private PreparedStatement usss;
	private PreparedStatement ro;
	public Login(String user, String passwd) {
		super();
		this.user = user;
		this.passwd = passwd;
		ConnectionMysql cmp = new ConnectionMysql();
		connection = cmp.connect;
		stmt = cmp.stmt;
		
	 try {
		log = connection.prepareStatement("" + "SELECT `id`, `imie`, `nazwisko` FROM `Poradnia_admin` WHERE `login` = ? AND `haslo` = ? LIMIT 1");
		ro = connection.prepareStatement("" + "SELECT `uprawnienia` FROM `Poradnia_admin` WHERE `login` = ? AND `haslo` = ?");
	 } catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
		
		
	}

	public boolean logIn() {
		ResultSet r;
		try {
			log.setString(1, user);
			log.setString(2, passwd);
			r = log.executeQuery();
			if(r.next()) {
				return true;			
			}
			else {
				return false;
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}

			
	} 
	
	User setUser(){
		User us=null;
		
		boolean li = logIn();
		if (li == true) {		
			try {
				log.setString(1, user);
				log.setString(2, passwd);
				ResultSet r = log.executeQuery();

				while (r.next()){
					us = new User(r.getInt(1), r.getString(2), r.getString(3), true);
				}
				
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return us;
	}	
	Role setRole(){
		Role role=new Role();
		ResultSet r;
		boolean li = logIn();
		if (li == true) {
			try {
				ro.setString(1, user);
				ro.setString(2, passwd);
				r = ro.executeQuery();
				while (r.next()) {
					role.setRole(r.getInt(1));
				}

			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return role;
	}	
	
}
