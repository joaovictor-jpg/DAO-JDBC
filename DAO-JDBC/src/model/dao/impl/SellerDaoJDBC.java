package model.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

	@Override
	public List<Seller> findByDepartment(Department dep) {
		PreparedStatement ps = null;
		ResultSet rs = null;

		try {
			ps = conn.prepareStatement(
					"SELECT seller.*,department.Name as DepName " + "FROM seller INNER JOIN department "
							+ "ON seller.DepartmentId = department.Id " + "WHERE DepartmentId = ? " + "ORDER BY Name");

			ps.setInt(1, dep.getId());

			rs = ps.executeQuery();

			List<Seller> sellers = new ArrayList<>();
			Map<Integer, Department> map = new HashMap<>();

			while (rs.next()) {
				Department dp = map.get(rs.getInt("DepartmentId"));
				if (dp == null) {
					dp = new Department(rs.getInt("DepartmentId"), rs.getString("DepName"));
					map.put(rs.getInt("DepartmentId"), dp);
				}

				sellers.add(new Seller(rs.getInt("Id"), rs.getString("Name"), rs.getString("Email"),
						rs.getDate("BirthDate"), rs.getDouble("BaseSalary"), dp));
			}
			return sellers;

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

	@Override
	public List<Seller> findAll() {
		PreparedStatement ps = null;
		ResultSet rs = null;

		try {
			ps = conn.prepareStatement(
					"SELECT seller.*,department.Name as DepName "
							+ "FROM seller INNER JOIN department "
							+ "ON seller.DepartmentId = department.Id " 
							+ "ORDER BY Name ");
			rs = ps.executeQuery();
			
			List<Seller> sellers = new ArrayList<>();
			
			while(rs.next()) {
				Department db = new Department(rs.getInt("DepartmentId"), rs.getString("DepName"));
				Seller seller = new Seller(rs.getInt("Id"), rs.getString("Name"), rs.getString("Email"),
						rs.getDate("BirthDate"), rs.getDouble("BaseSalary"), db);
				sellers.add(seller);
			}
			
			return sellers;
		} catch (SQLException e) {
			throw new DbException(e.getMessage());
		}
	}
}
