package application;

import java.util.List;

import model.dao.DaoFactory;
import model.dao.DepartmentDao;
import model.entities.Department;

public class Program {

	public static void main(String[] args) {
		DepartmentDao depGenercs = DaoFactory.createDepartmentDao();
		
		List<Department> dp = depGenercs.findAll();
		
		dp.forEach(System.out::println);
	}

}
