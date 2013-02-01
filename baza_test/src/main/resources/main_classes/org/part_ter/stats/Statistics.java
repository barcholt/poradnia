package org.part_ter.stats;

import java.util.ArrayList;

import org.joda.time.LocalDate;
import org.part_ter.Terapeutka;

public interface Statistics {

	public int WizytyMiesiac (LocalDate data);
	public int WizytyMiesiac (LocalDate data, Terapeutka ter);
	public int WizytyTydzien (LocalDate data);
	public int WizytyTydzien (LocalDate data, Terapeutka ter);
	public int WizytyRok (LocalDate data);
	public int WizytyRok (LocalDate data, Terapeutka ter);
}
