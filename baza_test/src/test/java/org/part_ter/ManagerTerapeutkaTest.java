package org.part_ter;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class ManagerTerapeutkaTest {
	ManagerTerapeutkaMysql man;
	Terapeutka ter1;
	Terapeutka ter2;
	
	public ManagerTerapeutkaTest() {
		man = new ManagerTerapeutkaMysql();
		ter1 = new Terapeutka(2, "Kamila", "Bednarczuk", "298 099 009", "kamila@bednarczuk.pl",2);
	}
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		
	}

	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void gettest() {
		ter2 = man.get(2);
		assertEquals("Kowalewska", ter2.getNazwisko());
		assertEquals("123456789", ter2.getNr_tel());
	}
	@Test
	public void getAlltest() {
		List<Terapeutka> lista;
		lista = man.getAll();
		ter1 = (Terapeutka)lista.get(0);
		assertEquals("Justyna", ter1.getImie());
	}
	
}
