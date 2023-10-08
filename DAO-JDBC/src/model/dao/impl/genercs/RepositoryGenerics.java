package model.dao.impl.genercs;

import java.sql.Connection;
import java.util.List;

import model.dao.generics.IGenercs;

public class RepositoryGenerics<T> implements IGenercs<T> {
	
	protected Connection conn;
	
	public RepositoryGenerics(Connection conn) {
		this.conn = conn;
	}

	@Override
	public void insert(T obj) {
		// TODO Auto-generated method stub

	}

	@Override
	public void update(T obj) {
		// TODO Auto-generated method stub

	}

	@Override
	public void deleteById(int id) {
		// TODO Auto-generated method stub
	}

	@Override
	public List<T> findAll() {
		// TODO Auto-generated method stub
		return null;
	}

}
