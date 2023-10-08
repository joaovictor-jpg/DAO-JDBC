package model.dao;

import model.dao.impl.DepartmentDaoJDBC;
import model.dao.impl.SellerDaoJDBC;

public class DaoFactory {

	public static SellerDao creteSellerDao() {
		return new SellerDaoJDBC();
	}
	
	public static DepartmentDao createDepartmentDao() {
		return new DepartmentDaoJDBC();
	}
	
}
