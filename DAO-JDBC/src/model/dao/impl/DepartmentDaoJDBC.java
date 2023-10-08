package model.dao.impl;

import java.sql.Connection;
import java.util.List;

import model.dao.DepartmentDao;
import model.dao.impl.genercs.RepositoryGenerics;
import model.entities.Department;

public class DepartmentDaoJDBC extends RepositoryGenerics<Department> implements DepartmentDao {

	public DepartmentDaoJDBC(Connection conn) {
		super(conn);
	}

	@Override
	public Department findById(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void insert(Department obj) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void update(Department obj) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteById(int id) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<Department> findAll() {
		// TODO Auto-generated method stub
		return null;
	}
}
