package model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import model.bean.User;
import util.DBConnectionUtil;
import util.DefineUtil;

public class UserDAO {
	private Connection conn;
	private PreparedStatement ps;
	private Statement st;
	private ResultSet rs;

	public ArrayList<User> getAllUser() {
		ArrayList<User> userList = new ArrayList<User>();
		conn = DBConnectionUtil.getConnection();
		String query = "SELECT * FROM `users` ORDER BY id DESC";
		try {
			st = conn.createStatement();
			rs = st.executeQuery(query);
			while (rs.next()) {
				User user = new User(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4));
				userList.add(user);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return userList;
	}

	public int addUser(User item) {
		int result = 0;
		conn = DBConnectionUtil.getConnection();
		String query = "INSERT INTO users(username,password,fullname) VALUES(?,?,?)";
		try {
			ps = conn.prepareStatement(query);
			ps.setString(1, item.getUsername());
			ps.setString(2, item.getPassword());
			ps.setString(3, item.getFullname());
			result = ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}

	public boolean haveUser(String username) {
		boolean checkUser = false;
		conn = DBConnectionUtil.getConnection();
		String query = "SELECT * FROM `users` WHERE username = ?";
		try {
			ps = conn.prepareStatement(query);
			ps.setString(1, username);
			rs = ps.executeQuery();
			while (rs.next()) {
				checkUser = true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return checkUser;
	}

	public User getUserById(int id) {
		User user = new User();
		conn = DBConnectionUtil.getConnection();
		String query = "SELECT * FROM `users` WHERE id = ?";
		try {
			ps = conn.prepareStatement(query);
			ps.setInt(1, id);
			rs = ps.executeQuery();
			if (rs.next()) {
				user = new User(id, rs.getString(2), rs.getString(3), rs.getString(4));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return user;
	}

	public int updateUser(User item) {
		int result = 0;
		conn = DBConnectionUtil.getConnection();
		String query = "UPDATE users SET username=?,password=?,fullname=? WHERE id=?";
		try {
			ps = conn.prepareStatement(query);
			ps.setString(1, item.getUsername());
			ps.setString(2, item.getPassword());
			ps.setString(3, item.getFullname());
			ps.setInt(4, item.getId());
			result = ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}

	public int deleteUser(int id) {
		int result = 0;
		conn = DBConnectionUtil.getConnection();
		String query = "DELETE FROM users\r\n" + "WHERE id =?";
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
		String Query = "SELECT COUNT(id) FROM users";
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

	public User existUser(String username, String password) {
		User user = null;
		conn = DBConnectionUtil.getConnection();
		String query = "SELECT * FROM users\r\n" + "WHERE username = ? AND password = ?";
		try {
			ps = conn.prepareStatement(query);
			ps.setString(1, username);
			ps.setString(2, password);
			rs = ps.executeQuery();
			if (rs.next()) {
				user = new User(rs.getInt(1), username, password, rs.getString(4));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return user;
	}

	public ArrayList<User> getAllSongPaging(int offset) {
		ArrayList<User> userList = new ArrayList<User>();
		conn = DBConnectionUtil.getConnection();
		String query = "SELECT * FROM users ORDER BY id DESC LIMIT ?, ?";
		try {
			ps = conn.prepareStatement(query);
			ps.setInt(1, offset);
			ps.setInt(2, DefineUtil.NUMBER_PER_PAGE);
			rs = ps.executeQuery();
			while (rs.next()) {
				User user = new User(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4));
				userList.add(user);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return userList;
	}

	public int getAmountByName(String text) {
		int amount = 0;
		conn = DBConnectionUtil.getConnection();
		String Query = "SELECT COUNT(id) FROM users WHERE username LIKE ?";
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

	public ArrayList<User> getAllUserByNamePaging(String text, int offset) {
		ArrayList<User> userList = new ArrayList<User>();
		conn = DBConnectionUtil.getConnection();
		String query = "SELECT * FROM users WHERE username LIKE ? ORDER BY id DESC LIMIT ?, ?";
		try {
			ps = conn.prepareStatement(query);
			ps.setString(1, "%" + text + "%");
			ps.setInt(2, offset);
			ps.setInt(3, DefineUtil.NUMBER_PER_PAGE);
			rs = ps.executeQuery();
			while (rs.next()) {
				User user = new User(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4));
				userList.add(user);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return userList;
	}

}
