package model.dao.generics;

import java.util.List;

public interface IGenercs<T> {
	public void insert(T obj);
	public void update(T obj);
	public void deleteById(int id);
	public List<T> findAll();
}
