package model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import model.bean.Song;
import util.DBConnectionUtil;
import util.DefineUtil;

public class SongDAO {
	private Connection conn;
	private Statement st;
	private PreparedStatement ps;
	private ResultSet rs;

	public ArrayList<Song> getAllSong() {
		ArrayList<Song> songList = new ArrayList<Song>();
		conn = DBConnectionUtil.getConnection();
		String query = "SELECT * FROM `songs`\r\n" + "ORDER BY id DESC";
		try {
			st = conn.createStatement();
			rs = st.executeQuery(query);
			while (rs.next()) {
				Song song = new Song(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5),
						rs.getString(6), rs.getInt(7), rs.getInt(8));
				songList.add(song);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return songList;
	}

	public int addSong(Song item) {
		int result = 0;
		conn = DBConnectionUtil.getConnection();
		String query = "INSERT INTO songs(name,preview_text,detail_text,picture,cat_id)\r\n" + "VALUES(?,?,?,?,?)";
		try {
			ps = conn.prepareStatement(query);
			ps.setString(1, item.getName());
			ps.setString(2, item.getPreviewText());
			ps.setString(3, item.getDetailText());
			ps.setString(4, item.getPicture());
			ps.setInt(5, item.getCatId());
			result = ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}

	public Song getSongById(int id) {
		Song song = new Song();
		conn = DBConnectionUtil.getConnection();
		String query = "SELECT * FROM `songs` WHERE id = ?";
		try {
			ps = conn.prepareStatement(query);
			ps.setInt(1, id);
			rs = ps.executeQuery();
			if (rs.next()) {
				song = new Song(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5),
						rs.getString(6), rs.getInt(7), rs.getInt(8));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return song;
	}

	public int editSong(Song item) {
		int result = 0;
		conn = DBConnectionUtil.getConnection();
		String query = "UPDATE songs\r\n" + "SET name=?,preview_text=?,detail_text=?,picture=?,cat_id=?\r\n"
				+ "WHERE id=?";
		try {
			ps = conn.prepareStatement(query);
			ps.setString(1, item.getName());
			ps.setString(2, item.getPreviewText());
			ps.setString(3, item.getDetailText());
			ps.setString(4, item.getPicture());
			ps.setInt(5, item.getCatId());
			ps.setInt(6, item.getId());
			result = ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}

	public int deleteSong(int id) {
		int result = 0;
		conn = DBConnectionUtil.getConnection();
		String query = "DELETE FROM songs WHERE id = ?";
		try {
			ps = conn.prepareStatement(query);
			ps.setInt(1, id);
			result = ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}

	public ArrayList<Song> getAllSongByCatId(int catId) {
		ArrayList<Song> songList = new ArrayList<Song>();
		conn = DBConnectionUtil.getConnection();
		String query = "SELECT * FROM `songs` WHERE cat_id = ? ORDER BY id DESC";
		try {
			ps = conn.prepareStatement(query);
			ps.setInt(1, catId);
			rs = ps.executeQuery();
			while (rs.next()) {
				Song song = new Song(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5),
						rs.getString(6), rs.getInt(7), rs.getInt(8));
				songList.add(song);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return songList;
	}

	public ArrayList<Song> getRelativeSong(Song song, int i) {
		ArrayList<Song> songList = new ArrayList<Song>();
		conn = DBConnectionUtil.getConnection();
		String query = "SELECT * FROM songs WHERE cat_id = ? && id != ? ORDER BY id DESC LIMIT ?";
		try {
			ps = conn.prepareStatement(query);
			ps.setInt(1, song.getCatId());
			ps.setInt(2, song.getId());
			ps.setInt(3, i);
			rs = ps.executeQuery();
			while (rs.next()) {
				Song song1 = new Song(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5),
						rs.getString(6), rs.getInt(7), rs.getInt(8));
				songList.add(song1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return songList;
	}

	public void increaseView(int id) {
		conn = DBConnectionUtil.getConnection();
		String query = "UPDATE songs SET counter = counter + 1 WHERE id = ?";
		try {
			ps = conn.prepareStatement(query);
			ps.setInt(1, id);
			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public int getAmount() {
		int amount = 0;
		conn = DBConnectionUtil.getConnection();
		String Query = "SELECT COUNT(id) FROM songs";
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

	public ArrayList<Song> getAllSongPaging(int offset) {
		ArrayList<Song> songList = new ArrayList<Song>();
		conn = DBConnectionUtil.getConnection();
		String query = "SELECT * FROM songs ORDER BY id DESC LIMIT ?, ?";
		try {
			ps = conn.prepareStatement(query);
			ps.setInt(1, offset);
			ps.setInt(2, DefineUtil.NUMBER_PER_PAGE);
			rs = ps.executeQuery();
			while (rs.next()) {
				Song song2 = new Song(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5),
						rs.getString(6), rs.getInt(7), rs.getInt(8));
				songList.add(song2);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return songList;
	}

	public int getAmount(int catId) {
		int amount = 0;
		conn = DBConnectionUtil.getConnection();
		String Query = "SELECT COUNT(id) FROM songs WHERE cat_id = ?";
		try {
			ps = conn.prepareStatement(Query);
			ps.setInt(1, catId);
			rs = ps.executeQuery();
			while (rs.next()) {
				amount = rs.getInt(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return amount;
	}

	public ArrayList<Song> getAllSongByCatIdPaging(int offset, int catId) {
		ArrayList<Song> songList = new ArrayList<Song>();
		conn = DBConnectionUtil.getConnection();
		String query = "SELECT * FROM songs WHERE cat_id = ? ORDER BY id DESC LIMIT ?, ?";
		try {
			ps = conn.prepareStatement(query);
			ps.setInt(1, catId);
			ps.setInt(2, offset);
			ps.setInt(3, DefineUtil.NUMBER_PER_PAGE);
			rs = ps.executeQuery();
			while (rs.next()) {
				Song song2 = new Song(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5),
						rs.getString(6), rs.getInt(7), rs.getInt(8));
				songList.add(song2);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return songList;
	}

	public ArrayList<Song> getAllSongByName(String text) {
		ArrayList<Song> songs = new ArrayList<Song>();
		conn = DBConnectionUtil.getConnection();
		String query = "SELECT * FROM `songs` WHERE name LIKE ?";
		try {
			ps = conn.prepareStatement(query);
			ps.setString(1, "%" + text + "%");
			rs = ps.executeQuery();
			while (rs.next()) {
				Song song = new Song(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5),
						rs.getString(6), rs.getInt(7), rs.getInt(8));
				songs.add(song);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return songs;
	}

	public int getAmountByName(String text) {
		int amount = 0;
		conn = DBConnectionUtil.getConnection();
		String Query = "SELECT COUNT(id) FROM songs WHERE name LIKE ?";
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

	public ArrayList<Song> getAllSongByNamePaging(String text, int offset) {
		ArrayList<Song> songList = new ArrayList<Song>();
		conn = DBConnectionUtil.getConnection();
		String query = "SELECT * FROM songs WHERE name LIKE ? ORDER BY id DESC LIMIT ?, ?";
		try {
			ps = conn.prepareStatement(query);
			ps.setString(1, "%" + text + "%");
			ps.setInt(2, offset);
			ps.setInt(3, DefineUtil.NUMBER_PER_PAGE);
			rs = ps.executeQuery();
			while (rs.next()) {
				Song song2 = new Song(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5),
						rs.getString(6), rs.getInt(7), rs.getInt(8));
				songList.add(song2);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return songList;
	}

}
