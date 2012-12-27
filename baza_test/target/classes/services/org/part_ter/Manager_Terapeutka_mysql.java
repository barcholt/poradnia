package org.part_ter;

import java.sql.*;
import java.util.*;

import org.junit.Test;

public class Manager_Terapeutka_mysql implements Manager_db<Terapeutka> {
	
	Connection connection;
	Statement stmt;
	PreparedStatement getTer;
	PreparedStatement getAllTer;
	
	public Manager_Terapeutka_mysql() {
		 
		try {
			Connection_mysql cmp = new Connection_mysql();
			connection = cmp.connect;
			stmt = cmp.stmt;
			
			getTer = connection.prepareStatement("" +
					"SELECT * FROM `Poradnia_Terapeuci` WHERE `id_terap` =?");

			getAllTer = connection.prepareStatement("" +
					"SELECT * FROM `Poradnia_Terapeuci`");
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public Connection getConnection() {
		return connection;
	}

	@Override
	public Terapeutka get(int id) {
		Terapeutka res=null;
		
		try {
			getTer.setInt(1, id);
			ResultSet rs = getTer.executeQuery();
			while (rs.next()) {
				res = new Terapeutka(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(5), rs.getString(4), rs.getInt(14) );
			}
			return res;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public List<Terapeutka> getAll() {
		List<Terapeutka> ListT= new ArrayList<Terapeutka>();
		
		try {
			ResultSet rs = getAllTer.executeQuery();
			while (rs.next()) {
				ListT.add(new Terapeutka(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getInt(14)));
			}
			return ListT;
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}

	}

	@Override
	public boolean save(Terapeutka obj) {
		return false;
	}

	@Override
	public boolean change(int id, Terapeutka ter) {
		return false;
	}

	@Override
	public boolean delete(int id) {
		return false;
	}

	
	
}

