package controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.bean.Comment;
import model.dao.CommentDAO;

public class PublicCommentController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private CommentDAO commentDAO;

	public PublicCommentController() {
		super();
		commentDAO = new CommentDAO();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html");
		response.setCharacterEncoding("UTF-8");
		request.setCharacterEncoding("UTF-8");
		String name = request.getParameter("name");
		String comment = request.getParameter("cmt");
		int song_id = Integer.parseInt(request.getParameter("song_id"));
		Comment comment2 = new Comment(0, name, comment, null, song_id);
		if (commentDAO.addComment(comment2) > 0) {
			ArrayList<Comment> commentList = commentDAO.getAllCommentBySongId(song_id);
			request.setAttribute("cmtList", commentList);
			if (commentList.size() > 0) {
				for (Comment comment3 : commentList) {
					response.getWriter().println("<div class=\"item-cmt\">" + "<p class=\"name-cmt\">" + comment3.getName() + "</p>"
								+ "<p class=\"content-cmt\">" + comment3.getComment() + "</p>" + "<p class=\"time-cmt\">"
								+ comment3.getDatePost() + "</p>" + "</div>");
				}
			}
		}
	}

}
