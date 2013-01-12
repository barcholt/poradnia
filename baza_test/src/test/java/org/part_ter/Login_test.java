package org.part_ter;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class Login_test {
	Login lo; 
	User us;
	Role rola;
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
		lo = new Login("barcholt", "haslo_mpr");
//		User us = lo.setUser();
//		Role rola = lo.setRole();

	}

	@Test
	public void test() {
		Boolean a = lo.log_in();
		assertTrue(a);
		us = lo.setUser();
		assertEquals("Bartek", us.getImie());
		assertEquals("Nowaczyk", us.getNazwisko());
	
		rola = lo.setRole();
		assertEquals(1, rola.getRole());
		
		
		
	}

}
