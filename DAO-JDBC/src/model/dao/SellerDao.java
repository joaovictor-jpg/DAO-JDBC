package model.dao;

import model.dao.generics.IGenercs;
import model.entities.Seller;

public interface SellerDao extends IGenercs<Seller> {
	public Seller findById(int id);
}
