package application;

import java.util.ArrayList;
import java.util.List;

import model.dao.DaoFactory;
import model.dao.generics.IGenercs;
import model.entities.Department;

public class Program {

	public static void main(String[] args) {
		IGenercs<Department> depGenercs = DaoFactory.createDepartmentDao();

		List<Department> depList = new ArrayList<>();

		depGenercs.update(new Department(5, "Jogos"));

		depList = depGenercs.findAll();

		depList.forEach(System.out::println);
	}

}
