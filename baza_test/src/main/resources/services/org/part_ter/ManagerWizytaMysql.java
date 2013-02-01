package org.part_ter;

import java.sql.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

import org.joda.time.*;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

public class ManagerWizytaMysql implements ManagerDb<Wizyta>, Subject<Wizyta> {
	private Connection connection;
	private Statement stmt;
	private ManagerKlientMysql ManKlient;
	private ManagerTerapeutkaMysql ManTer;
	private Klient klient;
	private Terapeutka terapeutka;
	private PreparedStatement getWiz;
	private PreparedStatement getListWiz;
	private PreparedStatement getWizTer;
	private PreparedStatement getWizDzien;
	private PreparedStatement getWizTerDzien;
	private PreparedStatement change;
	private PreparedStatement insert;
	private PreparedStatement delete;
	private PreparedStatement getLastId;
	private PreparedStatement getListWizDaty;
	private PreparedStatement getListWizDatyTer;
	private PreparedStatement getListSum; 
	private DateTime dt = new DateTime();
	private DateTime dzis = new DateTime();
	private String dzisiaj = dzis.toString();
	private ArrayList observers;
	private String zmiana;
	private Wizyta wizyta_zmiana;
	
	public ManagerWizytaMysql() {
		ConnectionMysql cmp = new ConnectionMysql();
		connection = cmp.connect;
		stmt = cmp.stmt;
		observers = new ArrayList();
		ManKlient = new ManagerKlientMysql();
		ManTer = new ManagerTerapeutkaMysql();
		
		try {
			getWiz = connection.prepareStatement("" + "SELECT `id_wizyta`, `id_terap`, `id_klient`, `data`, `godzina`, `pierwsza_kolejna`, `oplata`, `czy_odbyla`, `notka`, `trwa`  FROM `Poradnia_Wizyty` WHERE `id_wizyta` =?");
			getListWiz = connection.prepareStatement("" + "SELECT * FROM `Poradnia_Wizyty` WHERE `data` > ? AND (`czy_odbyla`=1 OR `czy_odbyla`=99)");
			getWizTer = connection.prepareStatement("" + "SELECT * FROM `Poradnia_Wizyty` WHERE `id_terap` =? AND data > ? AND (`czy_odbyla`=1 OR `czy_odbyla`=99)");
			getWizDzien = connection.prepareStatement("" + "SELECT * FROM `Poradnia_Wizyty` WHERE `data` = ? AND (`czy_odbyla`=1 OR `czy_odbyla`=99)");
			getWizTerDzien = connection.prepareStatement("" + "SELECT * FROM `Poradnia_Wizyty` WHERE `data` = ? AND (`czy_odbyla`=1 OR `czy_odbyla`=99) AND `id_terap`=?");
			getListWizDaty = connection.prepareStatement("" + "SELECT * FROM `Poradnia_Wizyty` WHERE `data` BETWEEN ? AND ? AND (`czy_odbyla` =1 OR `czy_odbyla` =99)");
			getListWizDatyTer = connection.prepareStatement("" + "SELECT * FROM `Poradnia_Wizyty` WHERE `data` BETWEEN ? AND ? AND (`czy_odbyla` =1 OR `czy_odbyla` =99) AND id_terap=?");
			change = connection.prepareStatement("" + "UPDATE `Poradnia_Wizyty` SET `id_terap`=?,`id_klient`=?,`data`=?,`godzina`=?,`pierwsza_kolejna`=?,`oplata`=?, `czy_odbyla`=?,`notka`=?,`trwa`=? WHERE id_wizyta=?");
			insert = connection.prepareStatement("" + "INSERT INTO `Poradnia_Wizyty` (`id_terap`, `id_klient`, `data`, `godzina`, `pierwsza_kolejna`,`oplata`, `czy_odbyla`, `notka`, `trwa`) VALUES (" +
					"?,?,?,?,?,?,?,?,?)");
			delete = connection.prepareStatement("" + "DELETE FROM `Poradnia_Wizyty` WHERE `id_wizyta` = ?");
			getLastId = connection.prepareStatement("" + "SELECT `id_wizyta` FROM `Poradnia_Wizyty` ORDER BY `id_wizyta` DESC Limit 1");			
		} catch (SQLException e) {
			e.printStackTrace();
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
				LocalDate dt = LocalDate.parse(ld, formatter);
				klient = ManKlient.get(192);
				terapeutka = ManTer.get(rs.getInt(2));
				bb = new Wizyta(rs.getInt(1), terapeutka, klient, dt, rs.getString(5), rs.getInt(6), rs.getFloat(10), rs.getInt(7), rs.getString(9), rs.getInt(8));
			}
			return bb;
		} 
		catch (SQLException e) {
			e.printStackTrace();
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
				klient = ManKlient.get(rs.getInt(3));
				terapeutka = ManTer.get(rs.getInt(2));
				lista.add(new Wizyta(rs.getInt(1), terapeutka, klient, dt, rs.getString(5), rs.getInt(7), rs.getFloat(14), rs.getInt(9), rs.getString(13), rs.getInt(12)));
			}
			return lista;
		} 
		catch (SQLException e) {
			e.printStackTrace();
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
				klient = ManKlient.get(rs.getInt(3));
				terapeutka = ManTer.get(rs.getInt(2));
				lista.add(new Wizyta(rs.getInt(1), terapeutka, klient, dt, rs.getString(5), rs.getInt(7), rs.getInt(14), rs.getInt(9), rs.getString(13), rs.getInt(12)));
			}
			return lista;
		} 
		catch (SQLException e) {
			e.printStackTrace();
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
				klient = ManKlient.get(rs.getInt(3));
				terapeutka = ManTer.get(rs.getInt(2));
				lista.add(new Wizyta(rs.getInt(1), terapeutka, klient, dt, rs.getString(5), rs.getInt(7), rs.getInt(14), rs.getInt(9), rs.getString(13), rs.getInt(12)));
			}
			return lista;
		} 
		catch (SQLException e) {
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
				int idk = rs.getInt(3);
				klient = ManKlient.get(idk);
				lista.add(new Wizyta(rs.getInt(1), ter, klient, dt, rs.getString(5), rs.getInt(7), rs.getInt(14), rs.getInt(9), rs.getString(13), rs.getInt(12)));
			}
			return lista;
		} 
		catch (SQLException e) {
			e.printStackTrace();
		}
			
		return null;
	}	

	@Override
	public boolean change(int id, Wizyta wiz) {
		try {
			change.setInt(1, wiz.getterap().getId());
			change.setInt(2, wiz.get_klient().getId());
			change.setString(3, wiz.getData().toString());
			change.setString(4, wiz.getGodzina());
			change.setInt(5, wiz.getKtora());
			change.setInt(6, wiz.getOplata());
			change.setInt(7, wiz.getStatus());
			change.setString(8, wiz.getNotka());
			change.setFloat(9, wiz.getTrwa());
			change.setInt(10, id);
			change.executeUpdate();
			this.wizyta_zmiana = wiz;
			this.zmiana = "Informujemy, że w systemie została zmodyfikowana wizyta: ";
			notifyObservers();
			
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}
	
	@Override
	
	public boolean save(Wizyta wiz) {
		try {

			insert.setInt(1, wiz.getterap().getId());
			insert.setInt(2, wiz.get_klient().getId());
			insert.setString(3, wiz.getData().toString());
			insert.setString(4, wiz.getGodzina());
			insert.setInt(5, wiz.getKtora());
			insert.setInt(6, wiz.getOplata());
			insert.setInt(7, wiz.getStatus());
			insert.setString(8, wiz.getNotka());
			insert.setFloat(9, wiz.getTrwa());
			insert.executeUpdate();
			this.wizyta_zmiana = wiz;                                 
			this.zmiana = "Informujemy, że do systemu została dodana wizyta: ";
			notifyObservers();

			
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}
	
	@Override
	public boolean delete(int id) {
			return false;
	}

	public boolean delete(Wizyta wiz) {
		try {
			delete.setInt(1, wiz.getId());
			delete.executeUpdate();
			this.wizyta_zmiana = wiz;
			this.zmiana = "Informujemy, że z systemu została usunięta następująca wizyta: ";
			notifyObservers();
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

	public List GetWizytyDaty(LocalDate d1, LocalDate d2){
		List<Wizyta> lista = new ArrayList<Wizyta>();
		ResultSet rs;
		try {
			getListWizDaty.setString(1, d1.toString());
			getListWizDaty.setString(2, d2.toString());
			rs=getListWizDaty.executeQuery();
			while (rs.next()){
				String ld =rs.getString(4);
				DateTimeFormatter formatter = DateTimeFormat.forPattern("yyyy-MM-dd");
				LocalDate dt;
				dt = LocalDate.parse(ld, formatter);
				lista.add(new Wizyta(rs.getInt(1), terapeutka, klient, dt, rs.getString(5), rs.getInt(7), rs.getFloat(14), rs.getInt(9), rs.getString(13), rs.getInt(12)));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return lista;
	}
	public List GetWizytyDatyTer(LocalDate d1, LocalDate d2, Terapeutka ter){
		List<Wizyta> lista = new ArrayList<Wizyta>();
		ResultSet rs;
		try {
			getListWizDatyTer.setString(1, d1.toString());
			getListWizDatyTer.setString(2, d2.toString());
			getListWizDatyTer.setInt(3, ter.getId());
			rs=getListWizDatyTer.executeQuery();
			while (rs.next()){
				String ld =rs.getString(4);
				DateTimeFormatter formatter = DateTimeFormat.forPattern("yyyy-MM-dd");
				LocalDate dt;
				dt = LocalDate.parse(ld, formatter);
				lista.add(new Wizyta(rs.getInt(1), terapeutka, klient, dt, rs.getString(5), rs.getInt(7), rs.getFloat(14), rs.getInt(9), rs.getString(13), rs.getInt(12)));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return lista;
	}

	
	@Override
	public boolean addObservers(ObserverClass obs) {
		observers.add(obs);
		return true;
	}

	@Override
	public boolean deleteObservers(ObserverClass obs) {
		observers.remove(obs);
		return false;
	}

	@Override
	public boolean notifyObservers() {
		for (int i=0; i<observers.size(); i++) {
			ObserverClass ob = (ObserverClass)observers.get(i);
			ob.notify(this.wizyta_zmiana, zmiana);
		}
		return false;
	}
}
