package model.dao.impl;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import db.DbException;
import db.DbIntegrityException;
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
					"SELECT seller.*,department.Name as DepName " + "FROM seller INNER JOIN department "
							+ "ON seller.DepartmentId = department.Id " + "ORDER BY Name ");
			rs = ps.executeQuery();

			List<Seller> sellers = new ArrayList<>();

			while (rs.next()) {
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

	@Override
	public void insert(Seller obj) {
		PreparedStatement ps = null;

		try {
			conn.setAutoCommit(false);
			ps = conn.prepareStatement("INSERT INTO seller " + "(Name, Email, BirthDate, BaseSalary, DepartmentId) "
					+ "VALUES " + "(?, ?, ?, ?, ?) ", Statement.RETURN_GENERATED_KEYS);

			ps.setString(1, obj.getName());
			ps.setString(2, obj.getEmail());
			ps.setDate(3, new Date(obj.getBirthDate().getTime()));
			ps.setDouble(4, obj.getBaseSalary());
			ps.setInt(5, obj.getDepartment().getId());

			int rowsAffects = ps.executeUpdate();

			if (rowsAffects > 0) {
				ResultSet rs = ps.getGeneratedKeys();
				if (rs.next()) {
					obj.setId(rs.getInt(1));
				}
				rs.close();
			} else {
				throw new DbException("Unexpected error! no rows affected!");
			}

			conn.commit();

		} catch (SQLException e) {
			try {
				conn.rollback();
				throw new DbException("Error: " + e.getMessage());
			} catch (SQLException e1) {
				throw new DbIntegrityException("Error: " + e1.getMessage());
			}
		} finally {
			if (ps != null) {
				try {
					ps.close();
				} catch (SQLException e) {
					throw new DbException(e.getMessage());
				}
			}
		}
	}

	@Override
	public void update(Seller obj) {
		PreparedStatement ps = null;

		try {
			conn.setAutoCommit(false);

			ps = conn.prepareStatement("UPDATE seller "
					+ "SET Name = ?, Email = ?, BirthDate = ?, BaseSalary = ?, DepartmentId = ? " + "WHERE Id = ?");

			ps.setString(1, obj.getName());
			ps.setString(2, obj.getEmail());
			ps.setDate(3, new Date(obj.getBirthDate().getTime()));
			ps.setDouble(4, obj.getBaseSalary());
			ps.setInt(5, obj.getDepartment().getId());
			ps.setInt(6, obj.getId());

			ps.executeUpdate();

			conn.commit();
		} catch (SQLException e) {
			try {
				conn.rollback();
				throw new DbException(e.getMessage());
			} catch (SQLException e1) {
				throw new DbIntegrityException("Error: " + e.getMessage());
			}
		} finally {
			if (ps != null) {
				try {
					ps.close();
				} catch (SQLException e) {
					throw new DbException(e.getMessage());
				}
			}
		}
	}

	@Override
	public void deleteById(int id) {
		
	}
}
