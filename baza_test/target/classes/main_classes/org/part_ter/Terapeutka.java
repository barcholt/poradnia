package org.part_ter;

public class Terapeutka {
	private int id;
	private String imie;
	private String nazwisko;
	private String nr_tel;
	private String email;
	private int id_umowy;
	
	public Terapeutka(int id, String imie, String nazwisko, String nr_tel,
			String email, int id_umowy) {
		super();
		this.id = id;
		this.imie = imie;
		this.nazwisko = nazwisko;
		this.nr_tel = nr_tel;
		this.email = email;
		this.id_umowy = id_umowy;
	}
	
	public int getId() {
		return id;
	}
	public String getImie() {
		return imie;
	}
	public String getNazwisko() {
		return nazwisko;
	}	
	public String getNr_tel() {
		return nr_tel;
	}

	public void setNr_tel(String nr_tel) {
		this.nr_tel = nr_tel;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public int getId_umowy() {
		return id_umowy;
	}

	public void setId_umowy(int id_umowy) {
		this.id_umowy = id_umowy;
	}
}
