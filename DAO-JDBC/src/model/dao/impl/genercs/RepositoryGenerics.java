package model.dao.impl.genercs;

import java.sql.Connection;

import model.dao.generics.IGenercs;

public abstract class RepositoryGenerics<T> implements IGenercs<T> {
	
	protected Connection conn;
	
	public RepositoryGenerics(Connection conn) {
		this.conn = conn;
	}

}
