package controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.bean.Comment;
import model.bean.Song;
import model.dao.CatDAO;
import model.dao.CommentDAO;
import model.dao.SongDAO;

public class PublicDetailController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private SongDAO songDAO;
	private CatDAO catDAO;
	private CommentDAO commentDAO;

	public PublicDetailController() {
		super();
		songDAO = new SongDAO();
		catDAO = new CatDAO();
		commentDAO = new CommentDAO();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html");
		response.setCharacterEncoding("UTF-8");
		request.setCharacterEncoding("UTF-8");
		int id = 0;
		try {
			id = Integer.parseInt(request.getParameter("id"));
		} catch (NumberFormatException e) {
			response.sendRedirect(request.getContextPath() + "/404");
			return;
		}

		Song song = songDAO.getSongById(id);
		if (song == null) {
			response.sendRedirect(request.getContextPath() + "/404");
			return;
		}

		HttpSession session = request.getSession();
		String hasVisited = (String) session.getAttribute("hasVisited: " + id);
		if (hasVisited == null) {
			session.setAttribute("hasVisited: " + id, "Yes");
			session.setMaxInactiveInterval(900);
		}
		songDAO.increaseView(id);

		ArrayList<Song> songList = songDAO.getRelativeSong(song, 5);
		request.setAttribute("songList", songList);
		request.setAttribute("song", song);

		ArrayList<Comment> cmtList = commentDAO.getAllCommentBySongId(id);
		request.setAttribute("cmtList", cmtList);

		request.getRequestDispatcher("/public/detail.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
	}

}
