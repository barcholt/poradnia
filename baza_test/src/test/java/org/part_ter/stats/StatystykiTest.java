package org.part_ter.stats;

import static org.junit.Assert.*;

import org.joda.time.LocalDate;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.part_ter.ManagerTerapeutkaMysql;
import org.part_ter.Terapeutka;

public class StatystykiTest {
	LocalDate data;
	Statystyki stat;
	ManagerTerapeutkaMysql man;
	Terapeutka ter;
	
	public StatystykiTest (){
		data = new LocalDate(2012, 12, 19);
		stat = new Statystyki();
		man = new ManagerTerapeutkaMysql();
		ter = man.get(2);
	}
	
	
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void testWizMsc() {
		stat.WizytyMiesiac(data);
		assertEquals(82, stat.ilosc);
		assertEquals(8450, stat.wplaty);
		assertEquals(164, stat.trwa, 0.1);
	}
	@Test
	public void testWizMscTer() {
		stat.WizytyMiesiac(data, ter);
		assertEquals(82, stat.ilosc);
		assertEquals(8450, stat.wplaty);
		assertEquals(164, stat.trwa, 0.1);
	}
	@Test
	public void testWizTyd(){
		stat.WizytyTydzien(data);
		assertEquals(82, stat.ilosc);
		
	}
	
	@Test
	public void testWizTydTer(){
		stat.WizytyTydzien(data, ter);
		assertEquals(82, stat.ilosc);
	}
	
	@Test
	public void testWizRok(){
		stat.WizytyRok(data);
		assertEquals(82, stat.ilosc);
		
	}
	
	@Test
	public void testWizRokTer(){
		stat.WizytyRok(data, ter);
		assertEquals(82, stat.ilosc);
		
	}
	
	
}
