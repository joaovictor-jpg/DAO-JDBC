package application;

import model.dao.DaoFactory;
import model.dao.generics.IGenercs;
import model.entities.Department;

public class Program {

	public static void main(String[] args) {
		IGenercs<Department> depGenercs = DaoFactory.createDepartmentDao();

		depGenercs.insert(new Department(5, "GAMES"));
	}

}
