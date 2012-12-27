package org.part_ter;

import java.util.*;

public class test {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Manager_Terapeutka_mysql ter = new Manager_Terapeutka_mysql();
			Manager_Klient_mysql kli = new Manager_Klient_mysql();
		if (ter.getConnection() == null) {
			System.out.println("Błąd połączenia!");	
		}
		else {
			System.out.println("Połączenie nawiązano");
			
		}
		Terapeutka nowa = ter.get(1);
		System.out.println(nowa.getImie());
		List<Terapeutka> list = ter.getAll();
		Terapeutka b = list.get(0);
		System.out.println(b.getEmail());
		
		Klient nowkl = kli.get(2);
		System.out.println(nowkl.getImie());
		List<Klient> listK = kli.getAll();
		Klient a = listK.get(3);
		System.out.println(a.getImie());
	}
}