package model.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import db.DbException;
import db.DbIntegrityException;
import model.dao.DepartmentDao;
import model.dao.impl.genercs.RepositoryGenerics;
import model.entities.Department;

public class DepartmentDaoJDBC extends RepositoryGenerics<Department> implements DepartmentDao {

	public DepartmentDaoJDBC(Connection conn) {
		super(conn);
	}

	@Override
	public Department findById(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void insert(Department obj) {
		PreparedStatement ps = null;

		try {
			ps = conn.prepareStatement("INSERT INTO department (Id, Name) VALUES (?, ?)");
			ps.setInt(1, obj.getId());
			ps.setString(2, obj.getName());
			ps.executeUpdate();
		} catch (SQLException e) {
			throw new DbException(e.getMessage());
		} finally {
			if (ps != null) {
				try {
					ps.close();
				} catch (SQLException e) {
					throw new DbIntegrityException(e.getMessage());
				}
			}
		}

	}

	@Override
	public void update(Department obj) {
		PreparedStatement ps = null;

		try {
			ps = conn.prepareStatement("UPDATE department SET NAME = ? WHERE ID = ?");
			ps.setString(1, obj.getName());
			ps.setInt(2, obj.getId());
			ps.executeUpdate();
		} catch (SQLException e) {
			throw new DbException(e.getMessage());
		} finally {
			if (ps != null) {
				try {
					ps.close();
				} catch (SQLException e) {
					throw new DbIntegrityException(e.getMessage());
				}
			}
		}
	}

	@Override
	public void deleteById(int id) {
		// TODO Auto-generated method stub

	}

	@Override
	public List<Department> findAll() {
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			List<Department> dp = new ArrayList<>();
			ps = conn.prepareStatement("SELECT * FROM department");
			rs = ps.executeQuery();
			while (rs.next()) {
				dp.add(new Department(rs.getInt("Id"), rs.getString("Name")));
			}
			return dp;
		} catch (SQLException e) {
			throw new DbException(e.getMessage());
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException e) {
					throw new DbIntegrityException(e.getMessage());
				}
			}
			if (ps != null) {
				try {
					ps.close();
				} catch (SQLException e) {
					throw new DbIntegrityException(e.getMessage());
				}
			}
		}

	}
}
