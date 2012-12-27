package org.part_ter;

import java.sql.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;
import org.joda.time.*;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

public class Manager_Wizyta_mysql implements Manager_db<Wizyta> {
	private Connection connection;
	private Statement stmt;
	private PreparedStatement getWiz;
	private PreparedStatement getListWiz;
	private PreparedStatement getWizTer;
	private PreparedStatement getWizDzien;
	private PreparedStatement getWizTerDzien;
	private PreparedStatement change;
	private PreparedStatement insert;
	private PreparedStatement delete;
	private PreparedStatement getLastId;
	private DateTime dt = new DateTime();
	private DateTime dzis = new DateTime();
	private String dzisiaj = dzis.toString();
	
	public Manager_Wizyta_mysql() {
		Connection_mysql cmp = new Connection_mysql();
		connection = cmp.connect;
		stmt = cmp.stmt;
	    
		try {
			getWiz = connection.prepareStatement("" + "SELECT * FROM `Poradnia_Wizyty` WHERE `id_wizyta` =?");
			getListWiz = connection.prepareStatement("" + "SELECT * FROM `Poradnia_Wizyty` WHERE `data` > ? AND (`czy_odbyla`=1 OR `czy_odbyla`=99)");
			getWizTer = connection.prepareStatement("" + "SELECT * FROM `Poradnia_Wizyty` WHERE `id_terap` =? AND data > ? AND (`czy_odbyla`=1 OR `czy_odbyla`=99)");
			getWizDzien = connection.prepareStatement("" + "SELECT * FROM `Poradnia_Wizyty` WHERE `data` = ? AND (`czy_odbyla`=1 OR `czy_odbyla`=99)");
			getWizTerDzien = connection.prepareStatement("" + "SELECT * FROM `Poradnia_Wizyty` WHERE `data` = ? AND (`czy_odbyla`=1 OR `czy_odbyla`=99) AND `id_terap`=?");
			change = connection.prepareStatement("" + "UPDATE `Poradnia_Wizyty` SET `id_terap`=?,`id_klient`=?,`data`=?,`godzina`=?,`pierwsza_kolejna`=?,`oplata`=?, `czy_odbyla`=?,`notka`=?,`trwa`=? WHERE id_wizyta=?");
			insert = connection.prepareStatement("" + "INSERT INTO `Poradnia_Wizyty` (`id_terap`, `id_klient`, `data`, `godzina`, `pierwsza_kolejna`,`oplata`, `czy_odbyla`, `notka`, `trwa`) VALUES (" +
					"?,?,?,?,?,?,?,?,?)");
			delete = connection.prepareStatement("" + "DELETE FROM `Poradnia_Wizyty` WHERE `id_wizyta` = ?");
			getLastId = connection.prepareStatement("" +
					"SELECT `id_wizyta` FROM `Poradnia_Wizyty` ORDER BY `id_wizyta` DESC Limit 1");
		} catch (SQLException e) {
			e.printStackTrace();
			System.err.println("Coś poszło źle 5!");
		}
	}
	
	public Connection getConnection(){
		return connection;
	}
	
	@Override
	public Wizyta get(int id) {
			Wizyta bb = null;
		try {
			getWiz.setInt(1, id);
			ResultSet rs = getWiz.executeQuery();
			while (rs.next()){
				String ld =rs.getString(4);
				DateTimeFormatter formatter = DateTimeFormat.forPattern("yyyy-MM-dd");
				LocalDate dt;
				dt = LocalDate.parse(ld, formatter);
				bb = new Wizyta(rs.getInt(1), rs.getInt(2), rs.getInt(3), dt, rs.getString(5), rs.getInt(7), rs.getInt(14), rs.getInt(9), rs.getString(13), rs.getInt(12));
			}
			return bb;
		} 
		catch (SQLException e) {
			e.printStackTrace();
			System.err.println("Coś poszło źle 4!");
			return null;
		}

	}
	
// Wyświetla wszystkie przyszłe, aktualne (nie odwołane) wizyty
	@Override
	public List<Wizyta> getAll() {
		List<Wizyta> lista = new ArrayList();
		try {
			getListWiz.setString(1, dzisiaj);
			ResultSet rs = getWizTer.executeQuery();
			while (rs.next()) {
				String ld =rs.getString(4);
				DateTimeFormatter formatter = DateTimeFormat.forPattern("yyyy-MM-dd");
				LocalDate dt;
				dt = LocalDate.parse(ld, formatter);
				lista.add(new Wizyta(rs.getInt(1), rs.getInt(2), rs.getInt(3), dt, rs.getString(5), rs.getInt(7), rs.getInt(14), rs.getInt(9), rs.getString(13), rs.getInt(12)));
			}
			return lista;
		} 
		catch (SQLException e) {
			e.printStackTrace();
			System.err.println("Coś poszło źle 3!");
		}
		return null;
	}
	
// Wyswietla przyszłe, aktualne (nie odwołane) wizyty danej terapeutki 	
	protected List<Wizyta> getAllTer(Terapeutka ter) {
		List<Wizyta> lista = new ArrayList();
		try {
			getWizTer.setInt(1, ter.getId());
			getWizTer.setString(2, dzisiaj);
			ResultSet rs = getWizTer.executeQuery();
			while (rs.next()) {
				String ld =rs.getString(4);
				DateTimeFormatter formatter = DateTimeFormat.forPattern("yyyy-MM-dd");
				LocalDate dt;
				dt = LocalDate.parse(ld, formatter);
				lista.add(new Wizyta(rs.getInt(1), rs.getInt(2), rs.getInt(3), dt, rs.getString(5), rs.getInt(7), rs.getInt(14), rs.getInt(9), rs.getString(13), rs.getInt(12)));
			}
			return lista;
		} 
		catch (SQLException e) {
			e.printStackTrace();
			System.err.println("Coś poszło źle 2!");
		}
		
		return null;
	}
	
// Wyświetla aktualne (nie odwołane) wizyta danego dnia
	protected List<Wizyta> getAllDzien(String dzien) {
		List<Wizyta> lista = new ArrayList();
		try {
			getWizDzien.setString(1, dzien);
			ResultSet rs = getWizDzien.executeQuery();
			while (rs.next()) {
				String ld =rs.getString(4);
				DateTimeFormatter formatter = DateTimeFormat.forPattern("yyyy-MM-dd");
				LocalDate dt;
				dt = LocalDate.parse(ld, formatter);
				lista.add(new Wizyta(rs.getInt(1), rs.getInt(2), rs.getInt(3), dt, rs.getString(5), rs.getInt(7), rs.getInt(14), rs.getInt(9), rs.getString(13), rs.getInt(12)));
			}
			return lista;
		} 
		catch (SQLException e) {
			System.err.println("Coś poszło źle!");
			e.printStackTrace();
		}
		
		return null;
	}	

// Wyświetla aktualne (nie odwołane) wizyta danego dnia danej terapeutki
	protected List<Wizyta> getAllDzienTer(String dzien, Terapeutka ter) {
		List<Wizyta> lista = new ArrayList();
		try {
			getWizTerDzien.setString(1, dzien);
			getWizTerDzien.setInt(2, ter.getId());
			ResultSet rs = getWizTerDzien.executeQuery();
			while (rs.next()) {
				String ld =rs.getString(4);
				DateTimeFormatter formatter = DateTimeFormat.forPattern("yyyy-MM-dd");
				LocalDate dt;
				dt = LocalDate.parse(ld, formatter);
				lista.add(new Wizyta(rs.getInt(1), rs.getInt(2), rs.getInt(3), dt, rs.getString(5), rs.getInt(7), rs.getInt(14), rs.getInt(9), rs.getString(13), rs.getInt(12)));
			}
			return lista;
		} 
		catch (SQLException e) {
			e.printStackTrace();
			System.err.println("Coś poszło bardzo źle!");
		}
			
		return null;
	}	

	@Override
	public boolean change(int id, Wizyta wiz) {
		return false;
	}
	
	public boolean change(int id, Wizyta wiz, Terapeutka ter, Klient kli) {

		try {
			change.setInt(1, ter.getId());
			change.setInt(2, kli.getId());
			change.setString(3, wiz.getData().toString());
			change.setString(4, wiz.getGodzina());
			change.setInt(5, wiz.getKtora());
			change.setInt(6, wiz.getOplata());
			change.setInt(7, wiz.getStatus());
			change.setString(8, wiz.getNotka());
			change.setFloat(9, wiz.getTrwa());
			change.setInt(10, id);
			change.executeUpdate();
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}
	
	@Override
	public boolean save(Wizyta wiz) {
		return false;
	}
	
	public boolean save(Wizyta wiz, Terapeutka ter, Klient kli) {

		try {
			insert.setInt(1, ter.getId());
			insert.setInt(2, kli.getId());
			insert.setString(3, wiz.getData().toString());
			insert.setString(4, wiz.getGodzina());
			insert.setInt(5, wiz.getKtora());
			insert.setInt(6, wiz.getOplata());
			insert.setInt(7, wiz.getStatus());
			insert.setString(8, wiz.getNotka());
			insert.setFloat(9, wiz.getTrwa());
			insert.executeUpdate();
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}
	
	@Override
	public boolean delete(int id) {
		try {
			delete.setInt(1, id);
			delete.executeUpdate();
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
		
	}
	public int GetLastId() {

		int id=0;
		try {
			ResultSet rs=getLastId.executeQuery();
			while(rs.next()) {
				id = rs.getInt(1);
			}
			return id;
		} catch (SQLException e) {
			e.printStackTrace();
			return id;
		}
	}
		
}
