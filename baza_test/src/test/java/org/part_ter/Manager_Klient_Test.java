package org.part_ter;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class Manager_Klient_Test {
	Manager_Klient_mysql kli;
	Klient klient;
	
	public Manager_Klient_Test() {
		kli = new Manager_Klient_mysql();
		klient = new Klient("Janek", "Nowak","jan@nowak.pl", "888 000 999", 1);	
		if (kli.getConnection() == null) {
			System.out.println("Błąd połączenia!");	
		}
		else {
			System.out.println("Połączenie nawiązano");
		}
	}
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
	}
	
	@Test
	public void changeKlienTest(){
		kli.save(klient);
		int id = kli.LastId();
		System.out.println(id);
		Klient klientZBazy = kli.get(id);
		assertNotNull(klientZBazy);
		assertEquals(klientZBazy.getEmail(), klient.getEmail());
		kli.delete(id);
	}
}
