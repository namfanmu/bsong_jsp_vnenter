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

public class AdminSortSongController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private SongDAO songDAO;

	public AdminSortSongController() {
		super();
		songDAO = new SongDAO();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html");
		response.setCharacterEncoding("UTF-8");
		request.setCharacterEncoding("UTF-8");
		// check login
		if (!AuthUtil.checkLogin(request, response)) {
			response.sendRedirect(request.getContextPath() + "/login");
			return;
		}

		int songAmount = songDAO.getAmount();
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

		int sort_id = Integer.parseInt(request.getParameter("sort_id"));
		ArrayList<Song> songList = new ArrayList<Song>();
		if (sort_id == 1) {
			songList = songDAO.getAllSongPagingByCounterDESC(offset);
		} else {
			songList = songDAO.getAllSongPagingByCounterASC(offset);
		}
		request.setAttribute("songList", songList);
		
		int setPageShow = 2;
		request.setAttribute("setPageShow", setPageShow);
		request.setAttribute("sort_id", sort_id);
		request.getRequestDispatcher("/admin/song/index.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
	}

}
