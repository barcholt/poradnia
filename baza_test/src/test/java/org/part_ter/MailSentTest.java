package org.part_ter;

import static org.junit.Assert.*;

import java.sql.Connection;

import org.joda.time.LocalDate;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.mockito.Mockito.*;



public class MailSentTest {

	Terapeutka ter;
	Connection conn;
	Klient klient;
	Wizyta wizyta;
	Wizyta wizyta2;
	ManagerWizytaMysql man;
	LocalDate ld;
	ManagerTerapeutkaMysql meen;
	ObserverClass obs;

	
	
	public MailSentTest()  {
		ld = new LocalDate();
		
		man = new ManagerWizytaMysql();
		meen = new ManagerTerapeutkaMysql();
		
		klient = new Klient(192, "Janek", "Nowak","jan@nowak.pl", "888 000 999", 1);
		ter = meen.get(2); 
		wizyta = new Wizyta (ter, klient, ld, "18:00", 1, 2, 100, "", 1);
		wizyta2 = new Wizyta (ter, klient, ld, "19:00", 1, 2, 100, "", 1);

		if (man.getConnection() == null) {
			System.out.println("Błąd połączenia!");	
		}
		obs = new ObserwatorMail(man);

	}

	@Before
	public void setUp() throws Exception {
		
		}

	@Test 
	public void test() {
		System.out.println(ter.getNazwisko());
		man.change(10452, wizyta2);
		man.save(wizyta);
		man.delete(man.get(man.GetLastId()));
	}


	@Test 
	public void test2() {

		man.delete(man.get(10451));
	}
}
