package org.part_ter;

public class User {
private int id; 
private String imie;
private String nazwisko;
private boolean enabled;

public User(int id, String imie, String nazwisko, boolean enabled) {
	super();
	this.id = id;
	this.imie = imie;
	this.nazwisko = nazwisko;
	this.enabled = enabled;
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

public boolean isEnabled() {
	return enabled;
}

public void setEnabled(boolean enabled) {
	this.enabled = enabled;
}





}
