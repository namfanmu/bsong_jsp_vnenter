package model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import model.bean.Contact;
import util.DBConnectionUtil;
import util.DefineUtil;

public class ContactDAO {
	private Connection conn;
	private PreparedStatement ps;
	private Statement st;
	private ResultSet rs;

	public ArrayList<Contact> getAllContact() {
		ArrayList<Contact> contactList = new ArrayList<Contact>();
		conn = DBConnectionUtil.getConnection();
		String query = "SELECT * FROM `contacts` ORDER BY id DESC";
		try {
			st = conn.createStatement();
			rs = st.executeQuery(query);
			while (rs.next()) {
				Contact contact = new Contact(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4),
						rs.getString(5));
				contactList.add(contact);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return contactList;
	}

	public int deleteContact(int id) {
		int result = 0;
		conn = DBConnectionUtil.getConnection();
		String query = "DELETE FROM contacts\r\n" + "WHERE id = ?";
		try {
			ps = conn.prepareStatement(query);
			ps.setInt(1, id);
			result = ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}

	public int addContact(Contact contact) {
		int result = 0;
		conn = DBConnectionUtil.getConnection();
		String query = "INSERT INTO contacts(name,email,website,message)\r\n" + "VALUES(?,?,?,?)";
		try {
			ps = conn.prepareStatement(query);
			ps.setString(1, contact.getName());
			ps.setString(2, contact.getEmail());
			ps.setString(3, contact.getWebsite());
			ps.setString(4, contact.getMessage());
			result = ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}

	public int getAmount() {
		int amount = 0;
		conn = DBConnectionUtil.getConnection();
		String Query = "SELECT COUNT(id) FROM contacts";
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

	public ArrayList<Contact> getAllContactPaging(int offset) {
		ArrayList<Contact> contactList = new ArrayList<Contact>();
		conn = DBConnectionUtil.getConnection();
		String query = "SELECT * FROM contacts ORDER BY id DESC LIMIT ?, ?";
		try {
			ps = conn.prepareStatement(query);
			ps.setInt(1, offset);
			ps.setInt(2, DefineUtil.NUMBER_PER_PAGE);
			rs = ps.executeQuery();
			while (rs.next()) {
				Contact contact = new Contact(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4),
						rs.getString(5));
				contactList.add(contact);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return contactList;
	}

	public int getAmountByName(String text) {
		int amount = 0;
		conn = DBConnectionUtil.getConnection();
		String Query = "SELECT COUNT(id) FROM contacts WHERE name LIKE ?";
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

	public ArrayList<Contact> getAllContactByNamePaging(String text, int offset) {
		ArrayList<Contact> contactList = new ArrayList<Contact>();
		conn = DBConnectionUtil.getConnection();
		String query = "SELECT * FROM contacts WHERE name LIKE ? ORDER BY id DESC LIMIT ?, ?";
		try {
			ps = conn.prepareStatement(query);
			ps.setString(1, "%" + text + "%");
			ps.setInt(2, offset);
			ps.setInt(3, DefineUtil.NUMBER_PER_PAGE);
			rs = ps.executeQuery();
			while (rs.next()) {
				Contact contact = new Contact(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4),
						rs.getString(5));
				contactList.add(contact);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return contactList;
	}

}
