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
	Manager_Wizyta_mysql man;
	LocalDate ld;
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
		
		ld = new LocalDate();
				
		man = new Manager_Wizyta_mysql();
		Manager_Terapeutka_mysql meen = new Manager_Terapeutka_mysql();
		
		klient = new Klient(192, "Janek", "Nowak","jan@nowak.pl", "888 000 999", 1);
		ter = meen.get(1); 
		wizyta = new Wizyta (1, 192, ld, "18:00", 1, 2, 100, "", 1);
		wizyta2 = new Wizyta (1, 192, ld, "19:00", 1, 2, 100, "", 1);

		if (man.getConnection() == null) {
			System.out.println("Błąd połączenia!");	
		}
		ObserverClass obs = new ObserwatorMail(man);
	}
		
	

	@Test 
	public void test() {
		man.change(10452, wizyta2, ter, klient);
		man.save(wizyta, ter, klient );
		man.delete(man.get(man.GetLastId()));
	}


	@Test 
	public void test2() {

		man.delete(man.get(10451));
	}
}
