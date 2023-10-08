package application;

import java.util.List;

import model.dao.DaoFactory;
import model.dao.SellerDao;
import model.entities.Department;
import model.entities.Seller;

public class Program {

	public static void main(String[] args) {
	
		SellerDao sellerDao = DaoFactory.creteSellerDao();
		
		Seller seller = sellerDao.findById(3);
		
		System.out.println(seller);
		
		Department dp = new Department(2, null);
		List<Seller> sellers = sellerDao.findByDepartment(dp);
		
		sellers.forEach(System.out::println);
		
	}

}
