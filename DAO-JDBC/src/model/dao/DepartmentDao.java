package model.dao;

import model.dao.generics.IGenercs;
import model.entities.Department;

public interface DepartmentDao extends IGenercs<Department> {
	public Department findById(int id);
}
