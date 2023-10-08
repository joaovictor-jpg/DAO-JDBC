package model.dao;

import java.util.List;

import model.dao.generics.IGenercs;
import model.entities.Department;
import model.entities.Seller;

public interface SellerDao extends IGenercs<Seller> {
	public Seller findById(int id);
	
	public List<Seller> findByDepartment(Department dep);
}
