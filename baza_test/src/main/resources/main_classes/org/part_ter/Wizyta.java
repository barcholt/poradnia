package org.part_ter;

import java.text.*;
import java.util.*;

import org.joda.time.DateTime;
import org.joda.time.LocalDate;

public class Wizyta {

	private int id;
	private int id_terap;
	private int id_klient;
	private LocalDate data;
	private String godzina;
	private int ktora;
	private float trwa; 
	private int status;
	private int oplata;
	private String notka;
	
	public Wizyta(int id, int id_terap, int id_klient, LocalDate data, String godzina, int ktora,
			float trwa, int oplata, String notka, int status) {
		super();
		this.id = id;
		this.id_terap = id_terap;
		this.id_klient = id_klient;
		this.data = data;
		this.godzina = godzina;
		this.ktora = ktora;
		this.trwa = trwa;
		this.status = status;
		this.oplata = oplata;
		this.notka = notka;
	}

	public Wizyta(int id_terap, int id_klient, LocalDate data, String godzina, int ktora,
			float trwa, int oplata, String notka, int status) {
		super();
		this.id_terap = id_terap;
		this.id_klient = id_klient;
		this.data = data;
		this.godzina = godzina;
		this.ktora = ktora;
		this.trwa = trwa;
		this.status = status;
		this.oplata = oplata;
		this.notka = notka;
	}
	
	public Wizyta() {
		super();
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getId_terap() {
		return id_terap;
	}

	public void setId_terap(int id_terap) {
		this.id_terap = id_terap;
	}

	public int getId_klient() {
		return id_klient;
	}

	public void setId_klient(int id_klient) {
		this.id_klient = id_klient;
	}

	public LocalDate getData() {
		return data;
	}

	public void setData(LocalDate data) {
		this.data = data;
	}

	public String getGodzina() {
		return godzina;
	}

	public void setGodzina(String godzina) {
		this.godzina = godzina;
	}

	
	public int getKtora() {
		return ktora;
	}

	public void setKtora(int ktora) {
		this.ktora = ktora;
	}

	public String getNotka() {
		return notka;
	}

	public void setNotka(String notka) {
		this.notka = notka;
	}

	public float getTrwa() {
		return trwa;
	}

	public void setTrwa(float trwa) {
		this.trwa = trwa;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public int getOplata() {
		return oplata;
	}

	public void setOplata(int oplata) {
		this.oplata = oplata;
	}

	public Wizyta getWizyta() {
		return this;
	}

}
