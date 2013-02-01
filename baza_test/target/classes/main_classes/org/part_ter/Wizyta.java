package org.part_ter;

import java.text.*;
import java.util.*;

import org.joda.time.DateTime;
import org.joda.time.LocalDate;

public class Wizyta {
	private Klient klient;
	private Terapeutka terapeutka;
	private int id;																																																			
	private LocalDate data;
	private String godzina;
	private int ktora;
	private float trwa; 
	private int status;
	private int oplata;
	private String notka;

	 
	public Wizyta(int id, Terapeutka terap, Klient klient, LocalDate data, String godzina, int ktora,
			float trwa, int oplata, String notka, int status) {
		this(terap, klient, data, godzina, ktora, trwa, oplata, notka, status);
		this.id = id;

	}

	public Wizyta(Terapeutka terap, Klient klient, LocalDate data, String godzina, int ktora,
			float trwa, int oplata, String notka, int status) {
		this.terapeutka = terap;
		this.klient = klient;
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

	public Terapeutka getterap() {
		return terapeutka ;
	}


	public Klient get_klient() {
		return klient;
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
