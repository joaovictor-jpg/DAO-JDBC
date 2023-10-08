package model.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import db.DB;
import db.DbException;
import model.dao.SellerDao;
import model.dao.impl.genercs.RepositoryGenerics;
import model.entities.Department;
import model.entities.Seller;

public class SellerDaoJDBC extends RepositoryGenerics<Seller> implements SellerDao {

	public SellerDaoJDBC(Connection conn) {
		super(conn);
	}

	@Override
	public Seller findById(int id) {
		PreparedStatement ps = null;
		ResultSet rs = null;

		try {
			ps = conn.prepareStatement(
					"SELECT seller.*,department.Name as DepName " + "FROM seller INNER JOIN department "
							+ "ON seller.DepartmentId = department.Id " + "WHERE seller.Id = ? ");
			ps.setInt(1, id);
			rs = ps.executeQuery();

			if (rs.next()) {
				Department db = new Department(rs.getInt("DepartmentId"), rs.getString("DepName"));
				Seller seller = new Seller(rs.getInt("Id"), rs.getString("Name"), rs.getString("Email"),
						rs.getDate("BirthDate"), rs.getDouble("BaseSalary"), db);
				return seller;
			}
			return null;

		} catch (SQLException e) {
			throw new DbException(e.getMessage());
		} finally {
			try {
				if (ps != null) {
					ps.close();
				}
				if (rs != null) {
					rs.close();
				}
			} catch (SQLException e) {
				throw new DbException(e.getMessage());
			}
		}
	}
}
