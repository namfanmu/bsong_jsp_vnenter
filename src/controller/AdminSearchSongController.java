package controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.bean.Song;
import model.dao.SongDAO;
import util.AuthUtil;
import util.DefineUtil;

public class AdminSearchSongController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private SongDAO songDAO;

	public AdminSearchSongController() {
		super();
		songDAO = new SongDAO();
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
		// check login
		if (!AuthUtil.checkLogin(request, response)) {
			response.sendRedirect(request.getContextPath() + "/login");
			return;
		}
		String text = request.getParameter("text");
		if (text == "") {
			response.sendRedirect(request.getContextPath() + "/admin/songs");
		} else {
			int songAmount = songDAO.getAmountByName(text);
			int pages = (int) Math.ceil((float) songAmount / DefineUtil.NUMBER_PER_PAGE);
			int currentPage = 1;
			try {
				currentPage = Integer.parseInt(request.getParameter("page"));
			} catch (NumberFormatException e) {
			}
			if (currentPage > pages || currentPage < 1) {
				currentPage = 1;
			}
			int offset = (currentPage - 1) * DefineUtil.NUMBER_PER_PAGE;
			request.setAttribute("songAmount", songAmount);
			request.setAttribute("pages", pages);
			request.setAttribute("currentPage", currentPage);

			ArrayList<Song> songList = songDAO.getAllSongByNamePaging(text, offset);
			request.setAttribute("songList", songList);
			
			int setPageShow = 3;
			request.setAttribute("setPageShow", setPageShow);
			request.getRequestDispatcher("/admin/song/index.jsp").forward(request, response);
		}
	}
}
