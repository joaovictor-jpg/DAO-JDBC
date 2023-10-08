package application;

import java.text.SimpleDateFormat;
import java.util.List;

import model.dao.DaoFactory;
import model.dao.SellerDao;
import model.entities.Department;
import model.entities.Seller;

public class Program {

	public static void main(String[] args) {

		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

		SellerDao sellerDao = DaoFactory.creteSellerDao();

		Seller seller = sellerDao.findById(3);

//		System.out.println(seller);

		Department dp = new Department(2, null);
		List<Seller> sellers = sellerDao.findByDepartment(dp);

//		sellers.forEach(System.out::println);

		sellers = sellerDao.findAll();

//		sellers.forEach(System.out::println);

		// INSERT SELLER;

//		try {
//			Seller sellerInsert = new Seller(null, "João Victor", "joaovictor@gmail.com", sdf.parse("16/03/2000"), 3500.00, dp);
//			sellerDao.insert(sellerInsert);
//		} catch (ParseException e) {
//			System.out.println(e.getMessage());
//		}
//		
//		sellers.forEach(System.out::println);

		seller = sellerDao.findById(9);
		seller.setName("Thalita");
		seller.setEmail("thalita@gmail.com");
		sellerDao.update(seller);

		sellerDao.deleteById(9);
		sellers.forEach(System.out::println);

	}

}
