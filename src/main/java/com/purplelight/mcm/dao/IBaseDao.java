package com.purplelight.mcm.dao;

import java.io.Serializable;
import java.lang.reflect.InvocationTargetException;
import java.util.Collection;
import java.util.List;

import com.purplelight.mcm.exception.McmException;
import com.purplelight.mcm.query.PageInfo;
import com.purplelight.mcm.query.Strategy;

public interface IBaseDao<T, ID extends Serializable> {
	public void save(T t);
	
	public void saveOrUpdate(T t);
	
	public T loadById(ID id);
	
	public T getById(ID id);
	
	public boolean contains(T t);
	
	public void update(T t);
	
	public void delete(T t);
	
	public boolean deleteById(ID id);
	
	public void deleteAll(Collection<T> list);
	
	public List<T> getAll();
	
	public List<T> find(Strategy strategy) throws McmException, IllegalAccessException, IllegalArgumentException, InvocationTargetException;
	
	public PageInfo<T> find(Strategy strategy, int startPos, int pageNo, int pageSize) throws McmException, IllegalAccessException, IllegalArgumentException, InvocationTargetException;
}
