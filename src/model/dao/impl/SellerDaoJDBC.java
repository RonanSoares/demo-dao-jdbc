package model.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import db.DB;
import db.DbException;
import model.dao.SellerDao;
import model.entities.Department;
import model.entities.Seller;

public class SellerDaoJDBC implements SellerDao {

	private Connection conn;

	public SellerDaoJDBC(Connection conn) {
		this.conn = conn;
	}

	@Override
	public void insert(Seller dep) {
		PreparedStatement ps = null;
		try {
			ps = conn.prepareStatement(

					"INSERT INTO seller " + "(Name, Email, BirthDate, BaseSalary, DepartmentId) " + "VALUES "
							+ "(?, ?, ?, ?, ?)",
					Statement.RETURN_GENERATED_KEYS); // Para retornar o Id.

			ps.setString(1, dep.getName());
			ps.setString(2, dep.getEmail());
			ps.setDate(3, new java.sql.Date(dep.getBirthDate().getTime()));
			ps.setDouble(4, dep.getBaseSalary());
			ps.setInt(5, dep.getDepartment().getId());

			int linhasAfetadas = ps.executeUpdate(); // variável recebe o nr de linhas e executa comd.

			if (linhasAfetadas > 0) { // Se linha alteradas for maior que 0.
				ResultSet rs = ps.getGeneratedKeys();
				if (rs.next()) {
					int id = rs.getInt(1); // Pega o Id da coluna 1
					dep.setId(id);
				}
				
				DB.closeResultSet(rs);
			}
			else {
				throw new DbException("Erro inesperado! Nenhuma linha foi alterada");
			}

		} catch (SQLException e) {
			throw new DbException(e.getMessage());
		}
		finally {
			DB.closeStatement(ps);
		}

	}

	@Override
	public void update(Seller dep) {
		PreparedStatement ps = null;
		try {
			ps = conn.prepareStatement(

					"UPDATE seller "
					+ "SET Name = ?, Email = ?, BirthDate = ?, BaseSalary = ?, DepartmentId = ? "
					+ "WHERE Id = ?");
	

			ps.setString(1, dep.getName());
			ps.setString(2, dep.getEmail());
			ps.setDate(3, new java.sql.Date(dep.getBirthDate().getTime()));
			ps.setDouble(4, dep.getBaseSalary());
			ps.setInt(5, dep.getDepartment().getId());
			ps.setInt(6, dep.getId());

			ps.executeUpdate(); // variável recebe o nr de linhas e executa comd.			

		} catch (SQLException e) {
			throw new DbException(e.getMessage());
		}
		finally {
			DB.closeStatement(ps);
		}

	}

	@Override
	public void deleteById(Integer id) {
		// TODO Auto-generated method stub

	}

	@Override
	public Seller findById(Integer id) {
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			ps = conn.prepareStatement(
					"SELECT seller.*,department.Name as DepName " + "FROM seller INNER JOIN department "
							+ "ON seller.DepartmentId = department.Id " + "WHERE seller.Id = ?");

			ps.setInt(1, id);
			rs = ps.executeQuery();

			if (rs.next()) {
				Department dep = instantiateDepartment(rs);
				Seller obj = instantiateSeller(rs, dep);
				return obj;

			}
			return null;

		} catch (SQLException e) {
			throw new DbException(e.getMessage());
		} finally {
			DB.closeStatement(ps);
			DB.closeResultSet(rs);
		}
	}

	private Seller instantiateSeller(ResultSet rs, Department dep) throws SQLException {
		Seller obj = new Seller();
		obj.setId(rs.getInt("Id"));
		obj.setName(rs.getString("Name"));
		obj.setEmail(rs.getString("Email"));
		obj.setBaseSalary(rs.getDouble("BaseSalary"));
		obj.setBirthDate(rs.getDate("BirthDate"));
		obj.setDepartment(dep); // Associação com Department
		return obj;
	}

	private Department instantiateDepartment(ResultSet rs) throws SQLException {
		Department dep = new Department();
		dep.setId(rs.getInt("DepartmentId"));
		dep.setName(rs.getString("DepName"));
		return dep;
	}

	@Override
	public List<Seller> findAll() {
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			ps = conn.prepareStatement(
					"SELECT seller.*,department.Name as DepName " + "FROM seller INNER JOIN department "
							+ "ON seller.DepartmentId = department.Id " + "ORDER BY Name");

			rs = ps.executeQuery();

			// Como são varios valores, tem que declarar uma lista.
			List<Seller> list = new ArrayList<>();

			// Estrutura Map para não repetir os departamentos.
			Map<Integer, Department> map = new HashMap<>(); // Cria o map vazio

			while (rs.next()) {

				// guarda no map todo departamento que instanciar.
				Department dep = map.get(rs.getInt("DepartmentId"));

				if (dep == null) {
					dep = instantiateDepartment(rs); // Se for nulo instancia o departamento.
					map.put(rs.getInt("DepartmentId"), dep); // Salva o departamento no dep.
				}

				Seller obj = instantiateSeller(rs, dep);
				list.add(obj);
			}
			return list;
		} catch (SQLException e) {
			throw new DbException(e.getMessage());
		} finally {
			DB.closeStatement(ps);
			DB.closeResultSet(rs);
		}
	}

	@Override
	public List<Seller> findByDepartment(Department department) {
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			ps = conn.prepareStatement(
					"SELECT seller.*,department.Name as DepName " + "FROM seller INNER JOIN department "
							+ "ON seller.DepartmentId = department.Id " + "WHERE DepartmentId = ? " + "ORDER BY Name");

			ps.setInt(1, department.getId());
			rs = ps.executeQuery();

			// Como são varios valores, tem que declarar uma lista.
			List<Seller> list = new ArrayList<>();

			// Estrutura Map para não repetir os departamentos.
			Map<Integer, Department> map = new HashMap<>(); // Cria o map vazio

			while (rs.next()) {

				// guarda no map todo departamento que instanciar.
				Department dep = map.get(rs.getInt("DepartmentId"));

				if (dep == null) {
					dep = instantiateDepartment(rs); // Se for nulo instancia o departamento.
					map.put(rs.getInt("DepartmentId"), dep); // Salva o departamento no dep.
				}

				Seller obj = instantiateSeller(rs, dep);
				list.add(obj);
			}
			return list;
		} catch (SQLException e) {
			throw new DbException(e.getMessage());
		} finally {
			DB.closeStatement(ps);
			DB.closeResultSet(rs);
		}
	}

}
