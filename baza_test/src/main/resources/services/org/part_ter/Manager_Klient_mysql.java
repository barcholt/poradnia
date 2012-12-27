package org.part_ter;

import java.util.ArrayList;
import java.util.List;
import java.sql.*;


public class Manager_Klient_mysql implements Manager_db<Klient> {

	private Connection connection;
	private Statement stmt; 
	private PreparedStatement getKli;
	private PreparedStatement getAllKli;
	private PreparedStatement getAllKliId;
	private PreparedStatement changeKli;
	private PreparedStatement saveKli;
	private PreparedStatement getLastId;
	private PreparedStatement deletestmt;
	
	
	

	
	public Manager_Klient_mysql() {
		try {
			Connection_mysql cmp = new Connection_mysql();
			connection = cmp.connect;
			stmt = cmp.stmt;
			getKli = connection.prepareStatement("" +
					"SELECT * FROM `Poradnia_Klienci` WHERE `id_klient` =?");

			getAllKli = connection.prepareStatement("" +
					"SELECT * FROM `Poradnia_Klienci`");

			changeKli = connection.prepareStatement("" +
					"UPDATE `Poradnia_Klienci` SET`imie`=?,`nazwisko`=?,`nr_tel`=?,`id_terap`=?,`e_mail`=? WHERE `id_klient` = ?");
			
			saveKli = connection.prepareStatement("" + "INSERT INTO `Poradnia_Klienci` (`imie`, `nazwisko`, `nr_tel`, `id_terap`, `e_mail`) VALUES (?,?,?,?,?)");

			getLastId = connection.prepareStatement("" +
					"SELECT `id_klient` FROM `Poradnia_Klienci` ORDER BY `id_klient` DESC Limit 1");
			deletestmt = connection.prepareStatement("" + "DELETE FROM `Poradnia_Klienci` WHERE `id_klient` = ? ");
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public Connection getConnection() {
		return connection;
	}

	@Override
	public Klient get(int id) {
		Klient res = null; 
		try {
			getKli.setInt(1, id);
			ResultSet rs= getKli.executeQuery();
			while (rs.next()){
				res = new Klient(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(6), rs.getString(4), rs.getInt(5));
			}
			return res;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public List<Klient> getAll() {
		try {
			List<Klient> list = new ArrayList<Klient>();
			ResultSet rs = getAllKli.executeQuery();
			while (rs.next()){
				list.add(new Klient(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getInt(6)));
			}
			return list;
		} catch (SQLException e) {
		// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}	
	}

	@Override
	public boolean change(int id, Klient klient) {
		try {
			changeKli.setString(1, klient.getImie());
			changeKli.setString(2, klient.getNazwisko());
			changeKli.setString(3, klient.getNr_tel());
			changeKli.setInt(4, klient.getId_terap());
			changeKli.setString(5, klient.getEmail());
			changeKli.setInt(6, id);
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public boolean delete(int id) {
		try {
			deletestmt.setInt(1, id);
			deletestmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} 
		
		return false;
	}

	@Override
	public boolean save(Klient klient) {
		try {
			saveKli.setString(1, klient.getImie());
			saveKli.setString(2, klient.getNazwisko());
			saveKli.setString(3, klient.getNr_tel());
			saveKli.setInt(4, klient.getId_terap());
			saveKli.setString(5, klient.getEmail());
			saveKli.executeUpdate();
			return true;
		} catch (SQLException e) {
			System.err.println(" Błąd w dodawaniu klienta do bazy");
			e.printStackTrace();
			return false;
		}
	}

	public int LastId() {
		int result =0;
		try {
			ResultSet res = getLastId.executeQuery();
			while (res.next()) {
				result = res.getInt(1);
			}
			return result;
		} catch (SQLException e) {
			e.printStackTrace();
			System.err.println("Błąd w pobieraniu id");
			return 0;
		}

	}

}
