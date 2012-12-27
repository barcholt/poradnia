package org.part_ter;

import java.util.List;

public interface Manager_db<TEntity> {

	public TEntity get(int id);
	
	public List<TEntity> getAll();
	
	public boolean change (int id, TEntity obj); 
	
	public boolean save(TEntity obj);
	
	public boolean delete(int id);
	

}
