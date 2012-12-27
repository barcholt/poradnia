package org.part_ter;

public class Klient {
	private int id;
	private	String imie;
	private String nazwisko;
	private String email;
	private String nr_tel;
	private int id_terap;

	
	public Klient(int id, String imie, String nazwisko, String email, String nr_tel,
			int id_terap) {
		super();
		this.id = id;
		this.imie = imie;
		this.nazwisko = nazwisko;
		this.email = email;
		this.nr_tel = nr_tel;
		this.id_terap = id_terap;
	}

	public Klient(String imie, String nazwisko, String email, String nr_tel, int id_terap) {
		super();
		this.imie = imie;
		this.nazwisko = nazwisko;
		this.email = email;
		this.nr_tel = nr_tel;
		this.id_terap = id_terap;
	}
	
	public Klient() {
		super();
	}
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getImie() {
		return imie;
	}
	public void setImie(String imie) {
		this.imie = imie;
	}

	public String getNazwisko() {
		return nazwisko;
	}
	public void setNazwisko(String nazwisko) {
		this.nazwisko = nazwisko;
	}

	

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getNr_tel() {
		return nr_tel;
	}

	public void setNr_tel(String nr_tel) {
		this.nr_tel = nr_tel;
	}

	public int getId_terap() {
		return id_terap;
	}

	public void setId_terap(int id_terap) {
		this.id_terap = id_terap;
	}

}
