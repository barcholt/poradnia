/**
 * 
 */
package org.part_ter;


import static org.junit.Assert.*;


import java.util.List;
import java.util.Calendar;
import java.util.Date;

import org.joda.time.LocalDate;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 * @author bartek
 *
 */

	

public class Manager_Wizyta_Mysql_Test {

	Manager_Wizyta_mysql man;
	Klient klient;
	Terapeutka ter;
	Wizyta wizyta;
	Wizyta wizyta2;
	LocalDate ld;
	LocalDate ld2;
	
	/**
	 * @throws java.lang.Exception
	 */
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	
	}

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		ld = new LocalDate();
		ld2 = new LocalDate(2012, 12, 22);
				
		man = new Manager_Wizyta_mysql();
		klient = new Klient(192, "Janek", "Nowak","jan@nowak.pl", "888 000 999", 1);
		ter = new Terapeutka(2, "Kamila", "Bednarczuk", "298 099 009", "kamila@bednarczuk.pl",2);
		wizyta = new Wizyta (3, 192, ld, "18:00", 1, 2, 100, "", 1);
		wizyta2 = new Wizyta (3, 192, ld2, "10:00", 1, 2, 100, "", 1);
		if (man.getConnection() == null) {
			System.out.println("Błąd połączenia!");	
			
		}
		else {
			System.out.println("Połączenie nawiązano");

		}
	}

	@Test
	public void testGetAllTerap() {
		List<Wizyta> list = man.getAllDzienTer(ld2.toString(),ter);
		Wizyta wiz = (Wizyta)list.get(0);
		assertNotSame(wizyta2, wiz);
		assertEquals("20:12", wiz.getGodzina());
	}
	
	
	@Test
	public void test() {
		fail("Not yet implemented");
	}

	@Test
	public void testAdd() {
		man.save(wizyta, ter, klient);
		int id = man.GetLastId();
		Wizyta zBazy = man.get(id);
		System.out.println("OPŁATA " + zBazy.getOplata());
		assertNotNull("Pusto", zBazy);
		assertEquals("Nie równe 1", wizyta.getKtora(), zBazy.getKtora());
		assertEquals("Nie równe 1,5",100, zBazy.getOplata());
	
		zBazy.setOplata(110);
		zBazy.setKtora(6);

		man.change(id, wizyta2, ter, klient);
		Wizyta zBazy2 = man.get(id);
		assertEquals("Nie równe2 ", zBazy2.getData(), wizyta2.getData());
		man.delete(id);
	}
	
	@Test 
	public void testGetAllDzien() {
		List<Wizyta> list = man.getAllDzien("2012-12-22");
		Wizyta wiz = (Wizyta)list.get(2);
		System.out.println(wiz.getId());
		assertEquals("Nie równe", "22:00", wiz.getGodzina());
		Wizyta wiz2 = (Wizyta)list.get(5);
		assertEquals("Nie równe 2",200,  wiz2.getOplata());
		assertEquals("Nie równe 3",10312,  wiz2.getId());
		assertEquals("Nie równe 4",2,  wiz2.getId_terap());
		assertEquals("Nie równe 5",192,  wiz2.getId_klient());
	}
	
	
	
}
