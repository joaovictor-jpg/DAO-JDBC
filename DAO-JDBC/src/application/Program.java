package application;

import java.text.ParseException;
import java.text.SimpleDateFormat;

import model.dao.DaoFactory;
import model.dao.SellerDao;
import model.dao.generics.IGenercs;
import model.entities.Department;
import model.entities.Seller;

public class Program {

	public static void main(String[] args) {

		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

		Department dp = new Department(1, "Books");
		System.out.println(dp);

		try {
			Seller seller = new Seller(1, "João Victor", "joao@gmail.com", sdf.parse("16/03/2000"), 2000.0, dp);
			System.out.println(seller);
		} catch (ParseException e) {
			System.out.println("Error: " + e.getMessage());
		}
		
		SellerDao sellerDao = DaoFactory.creteSellerDao();
	}

}
