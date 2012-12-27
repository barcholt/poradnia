package org.part_ter;
import java.util.List;

public interface Manager_Terapeutka<TEntity> {
	
	public TEntity get(int id);
	public List<TEntity> getAll();
	public boolean save(TEntity obj);
	public boolean delete(TEntity obj);
	
}
