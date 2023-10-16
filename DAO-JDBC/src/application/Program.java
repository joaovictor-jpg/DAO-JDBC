package application;

import model.dao.DaoFactory;
import model.dao.DepartmentDao;

public class Program {

	public static void main(String[] args) {
		DepartmentDao depGenercs = DaoFactory.createDepartmentDao();
		
		System.out.println(depGenercs.findById(3));
	}

}
