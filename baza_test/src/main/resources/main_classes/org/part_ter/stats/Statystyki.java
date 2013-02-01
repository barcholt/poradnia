package org.part_ter.stats;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

import org.apache.commons.math.stat.descriptive.DescriptiveStatistics;
import org.joda.time.LocalDate;
import org.part_ter.ManagerWizytaMysql;
import org.part_ter.Terapeutka;
import org.part_ter.Wizyta;

public class Statystyki implements Statistics {
	ManagerWizytaMysql man;
	int ilosc;
	int wplaty;
	float trwa;
	float wyplata;
	public Statystyki (){
		man = new ManagerWizytaMysql();
	}
	
	@Override
	public int WizytyMiesiac(LocalDate data) {
	List<Wizyta> list = new ArrayList();
		LocalDate pie = data.dayOfMonth().withMinimumValue();
		LocalDate ost = data.dayOfMonth().withMaximumValue();
		DescriptiveStatistics desc = new DescriptiveStatistics();
		list = man.GetWizytyDaty(pie, ost);
		ilosc = list.size();
		Iterator<Wizyta> it = list.iterator();
		while (it.hasNext()){
			Wizyta w = it.next();
			wplaty += w.getOplata();			
			desc.addValue(w.getOplata());
			trwa += w.getTrwa();
			wyplata += w.getTrwa()*30;
		}
		
		System.out.println("Statystyki wizyt w miesiącu " + data.getMonthOfYear() + ". Ilość wizyt: " + ilosc + 
				", łączne wpłaty: " + wplaty + ", średnia wpłata: " + desc.getMean() + ", wpłata dla terapeutek w miesiącu " + wyplata );
		return 0;
	}

	@Override
	public int WizytyMiesiac(LocalDate data, Terapeutka ter) {
		List<Wizyta> list = new ArrayList();
		LocalDate pie = data.dayOfMonth().withMinimumValue();
		LocalDate ost = data.dayOfMonth().withMaximumValue();
		DescriptiveStatistics desc = new DescriptiveStatistics();
		list = man.GetWizytyDatyTer(pie, ost, ter);
		ilosc = list.size();
		Iterator<Wizyta> it = list.iterator();
		while (it.hasNext()){
			Wizyta w = it.next();
			wplaty += w.getOplata();			
			desc.addValue(w.getOplata());
			trwa += w.getTrwa();
			wyplata += w.getTrwa()*30;
		}
		
		System.out.println("Statystyki wizyt w miesiącu " + data.getMonthOfYear() + " dla " + ter.getImie() + " " + ter.getNazwisko() +". Ilość wizyt: " + ilosc + 
				", łączne wpłaty: " + wplaty + ", średnia wpłata: " + desc.getMean() + ", wpłata dla terapeutek w miesiącu " + wyplata );
		return 0;	}

	@Override
	public int WizytyTydzien(LocalDate data) {
		List<Wizyta> list = new ArrayList();
		LocalDate pie = data.dayOfWeek().withMinimumValue();
		LocalDate ost = data.dayOfWeek().withMaximumValue();
		DescriptiveStatistics desc = new DescriptiveStatistics();
		list = man.GetWizytyDaty(pie, ost);
		ilosc = list.size();
		Iterator<Wizyta> it = list.iterator();
		while (it.hasNext()){
			Wizyta w = it.next();
			wplaty += w.getOplata();			
			desc.addValue(w.getOplata());
			trwa += w.getTrwa();
			wyplata += w.getTrwa()*30;
		}
		
		System.out.println("Statystyki wizyt w tygodniu zawierającym dzień:" + data + ". Ilość wizyt: " + ilosc + 
				", łączne wpłaty: " + wplaty + ", średnia wpłata: " + desc.getMean() + ", wpłata dla terapeutek w tygodniu " + wyplata );
		return 0;
	}

	@Override
	public int WizytyTydzien(LocalDate data, Terapeutka ter) {
		List<Wizyta> list = new ArrayList();
		LocalDate pie = data.dayOfWeek().withMinimumValue();
		LocalDate ost = data.dayOfWeek().withMaximumValue();
		DescriptiveStatistics desc = new DescriptiveStatistics();
		list = man.GetWizytyDatyTer(pie, ost, ter);
		ilosc = list.size();
		Iterator<Wizyta> it = list.iterator();
		while (it.hasNext()){
			Wizyta w = it.next();
			wplaty += w.getOplata();			
			desc.addValue(w.getOplata());
			trwa += w.getTrwa();
			wyplata += w.getTrwa()*30;
		}
		
		System.out.println("Statystyki wizyt w tygodniu zawierającym dzień:" + data + " dla " + ter.getImie() + " " + ter.getNazwisko() + 
				". Ilość wizyt: " + ilosc +	", łączne wpłaty: " + wplaty + ", średnia wpłata: " + desc.getMean() + ", wpłata dla " +
						"terapeutek w tygodniu " + wyplata );
		return 0;
	}

	@Override
	public int WizytyRok(LocalDate data) {
		List<Wizyta> list = new ArrayList();
		LocalDate pie = data.dayOfYear().withMinimumValue();
		LocalDate ost = data.dayOfYear().withMaximumValue();
		DescriptiveStatistics desc = new DescriptiveStatistics();
		list = man.GetWizytyDaty(pie, ost);
		ilosc = list.size();
		Iterator<Wizyta> it = list.iterator();
		while (it.hasNext()){
			Wizyta w = it.next();
			wplaty += w.getOplata();			
			desc.addValue(w.getOplata());
			trwa += w.getTrwa();
			wyplata += w.getTrwa()*30;
		}
		
		System.out.println("Statystyki wizyt w roku " + data.getYear() + ". Ilość wizyt: " + ilosc + 
				", łączne wpłaty: " + wplaty + ", średnia wpłata: " + desc.getMean() + ", wpłata dla terapeutek w roku " + wyplata );
		return 0;
	}

	@Override
	public int WizytyRok(LocalDate data, Terapeutka ter) {
		List<Wizyta> list = new ArrayList();
		LocalDate pie = data.dayOfYear().withMinimumValue();
		LocalDate ost = data.dayOfYear().withMaximumValue();
		DescriptiveStatistics desc = new DescriptiveStatistics();
		list = man.GetWizytyDatyTer(pie, ost, ter);
		ilosc = list.size();
		Iterator<Wizyta> it = list.iterator();
		while (it.hasNext()){
			Wizyta w = it.next();
			wplaty += w.getOplata();			
			desc.addValue(w.getOplata());
			trwa += w.getTrwa();
			wyplata += w.getTrwa()*30;
		}
		
		System.out.println("Statystyki wizyt w roku " + data.getYear() + " dla "  + ter.getImie() + " " + ter.getNazwisko() + ". Ilość wizyt: " + ilosc + 
				", łączne wpłaty: " + wplaty + ", średnia wpłata: " + desc.getMean() + ", wpłata dla terapeutek w roku " + wyplata );
		return 0;
	}
}
