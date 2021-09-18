package model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import model.bean.Comment;
import model.bean.Song;
import util.DBConnectionUtil;

public class CommentDAO {
	private Connection conn;
	private ResultSet rs;
	private Statement st;
	private PreparedStatement ps;

	public int addComment(Comment comment2) {
		int result = 0;
		conn = DBConnectionUtil.getConnection();
		String query = "INSERT INTO comments(name,comment,song_id) VALUES(?,?,?)";
		try {
			ps = conn.prepareStatement(query);
			ps.setString(1, comment2.getName());
			ps.setString(2, comment2.getComment());
			ps.setInt(3, comment2.getSong_id());
			result = ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}

	public ArrayList<Comment> getAllCommentBySongId(int song_id) {
		ArrayList<Comment> cmtList = new ArrayList<Comment>();
		conn = DBConnectionUtil.getConnection();
		String query = "SELECT * FROM `comments` WHERE song_id = ? ORDER BY id DESC";
		try {
			ps = conn.prepareStatement(query);
			ps.setInt(1, song_id);
			rs = ps.executeQuery();
			while (rs.next()) {
				Comment comment = new Comment(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4),
						rs.getInt(5));
				cmtList.add(comment);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return cmtList;
	}

}
