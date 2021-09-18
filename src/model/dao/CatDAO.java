package model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import model.bean.Category;
import util.DBConnectionUtil;
import util.DefineUtil;

public class CatDAO {
	private Connection conn;
	private ResultSet rs;
	private Statement st;
	private PreparedStatement ps;

	public ArrayList<Category> getAllCategories() {
		ArrayList<Category> catList = new ArrayList<>();
		conn = DBConnectionUtil.getConnection();
		String query = "SELECT * FROM `categories` ORDER BY id DESC";
		try {
			ps = conn.prepareStatement(query);
			rs = ps.executeQuery();
			while (rs.next()) {
				Category category = new Category(rs.getInt(1), rs.getString(2));
				catList.add(category);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return catList;
	}

	public int addCat(Category item) {
		int result = 0;
		conn = DBConnectionUtil.getConnection();
		String query = "INSERT INTO categories(name)\r\n" + "VALUES(?)";
		try {
			ps = conn.prepareStatement(query);
			ps.setString(1, item.getName());
			result = ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}

	public Category getCatById(int id) {
		conn = DBConnectionUtil.getConnection();
		Category item = new Category();
		String query = "SELECT * FROM `categories` WHERE id = ?";
		try {
			ps = conn.prepareStatement(query);
			ps.setInt(1, id);
			rs = ps.executeQuery();
			if (rs.next()) {
				item = new Category(rs.getInt(1), rs.getString(2));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return item;
	}

	public int editCat(Category item) {
		int result = 0;
		conn = DBConnectionUtil.getConnection();
		String query = "UPDATE categories SET name=? WHERE id = ?";
		try {
			ps = conn.prepareStatement(query);
			ps.setString(1, item.getName());
			ps.setInt(2, item.getId());
			result = ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}

	public int deleteCat(int id) {
		int result = 0;
		conn = DBConnectionUtil.getConnection();
		String query = "DELETE FROM categories WHERE id = ?";
		try {
			ps = conn.prepareStatement(query);
			ps.setInt(1, id);
			result = ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}

	public int getAmount() {
		int amount = 0;
		conn = DBConnectionUtil.getConnection();
		String Query = "SELECT COUNT(id) FROM categories";
		try {
			st = conn.createStatement();
			rs = st.executeQuery(Query);
			while (rs.next()) {
				amount = rs.getInt(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return amount;
	}

	public ArrayList<Category> getAllCatPaging(int offset) {
		ArrayList<Category> catList = new ArrayList<Category>();
		conn = DBConnectionUtil.getConnection();
		String query = "SELECT * FROM categories ORDER BY id DESC LIMIT ?, ?";
		try {
			ps = conn.prepareStatement(query);
			ps.setInt(1, offset);
			ps.setInt(2, DefineUtil.NUMBER_PER_PAGE);
			rs = ps.executeQuery();
			while (rs.next()) {
				Category category = new Category(rs.getInt(1), rs.getString(2));
				catList.add(category);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return catList;
	}

	public ArrayList<Category> getAllCatByNamePaging(String text, int offset) {
		ArrayList<Category> catList = new ArrayList<Category>();
		conn = DBConnectionUtil.getConnection();
		String query = "SELECT * FROM categories WHERE name LIKE ? ORDER BY id DESC LIMIT ?, ?";
		try {
			ps = conn.prepareStatement(query);
			ps.setString(1, "%" + text + "%");
			ps.setInt(2, offset);
			ps.setInt(3, DefineUtil.NUMBER_PER_PAGE);
			rs = ps.executeQuery();
			while (rs.next()) {
				Category category = new Category(rs.getInt(1), rs.getString(2));
				catList.add(category);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return catList;
	}

	public int getAmountByName(String text) {
		int amount = 0;
		conn = DBConnectionUtil.getConnection();
		String Query = "SELECT COUNT(id) FROM categories WHERE name LIKE ?";
		try {
			ps = conn.prepareStatement(Query);
			ps.setString(1, "%" + text + "%");
			rs = ps.executeQuery();
			while (rs.next()) {
				amount = rs.getInt(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return amount;
	}

	public static void main(String[] args) {
		CatDAO catDAO = new CatDAO();
		ArrayList<Category> categories = catDAO.getAllCategories();
		System.out.println(categories);
	}

}
