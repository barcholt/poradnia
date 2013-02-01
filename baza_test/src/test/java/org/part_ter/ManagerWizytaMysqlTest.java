/**
 * 
 */
package org.part_ter;


import static org.junit.Assert.*;


import java.util.ArrayList;
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

	

public class ManagerWizytaMysqlTest {
	ManagerTerapeutkaMysql manTer;
	ManagerWizytaMysql man;
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
		manTer = new ManagerTerapeutkaMysql();
		man = new ManagerWizytaMysql();
		klient = new Klient(192, "Janek", "Nowak","jan@nowak.pl", "888 000 999", 1);
//		ter = new Terapeutka(2, "Kamila", "Bednarczuk", "298 099 009", "kamila@bednarczuk.pl",2);
		ter = manTer.get(2);
		wizyta = new Wizyta (ter, klient, ld, "18:00", 5, 2, 200, "", 3);
		wizyta2 = new Wizyta (ter, klient, ld2, "10:00", 6, 2, 110, "", 1);
		if (man.getConnection() == null) {
			System.out.println("Błąd połączenia!");	
			
		}
		else {
//			System.out.println("Połączenie nawiązano");

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
	public void testAdd() {
		man.save(wizyta);
		int id = man.GetLastId();
		Wizyta zBazy = man.get(id);
		assertNotNull("Pusto", zBazy);
		assertEquals("Nie równe 1", wizyta.getKtora(), zBazy.getKtora());
		assertEquals("Nie równe 1,5",200, zBazy.getOplata());
		assertEquals("18:00", zBazy.getGodzina());
		assertEquals(5, zBazy.getKtora());
		zBazy.setOplata(110);
		zBazy.setKtora(6);

		man.change(id, wizyta2);
		Wizyta zBazy2 = man.get(id);
		assertEquals("Nie równe2 ", zBazy2.getData(), wizyta2.getData());
		assertEquals(110, zBazy2.getOplata());
		assertEquals(6, zBazy2.getKtora());
		man.delete(wizyta2);
	}

	@Test 
	public void testGetAllDzien() {
		List<Wizyta> list = man.getAllDzien("2012-12-22");
		Wizyta wiz = (Wizyta)list.get(2);
		assertEquals("Nie równe", "22:00", wiz.getGodzina());
		Wizyta wiz2 = (Wizyta)list.get(5);
		assertEquals("Nie równe 2",200,  wiz2.getOplata());
		assertEquals("Nie równe 3",10312,  wiz2.getId());
		assertEquals("Nie równe 4",2,  wiz2.getterap().getId());
		assertEquals("Nie równe 5",192,  wiz2.get_klient().getId());
	}
	
	@Test 
	public void testGetListDaty() {
		LocalDate d1 = new LocalDate(2012,12,18);
		LocalDate d2 = new LocalDate(2012,12,19);
		List<Wizyta> list = man.GetWizytyDaty(d1, d2);
		assertEquals(18,list.size());
	}	
	
}
