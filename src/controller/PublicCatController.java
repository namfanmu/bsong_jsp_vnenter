package controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.bean.Category;
import model.bean.Song;
import model.dao.CatDAO;
import model.dao.SongDAO;
import util.DefineUtil;

public class PublicCatController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private SongDAO songDAO;
	private CatDAO catDAO;

	public PublicCatController() {
		super();
		songDAO = new SongDAO();
		catDAO = new CatDAO();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html");
		response.setCharacterEncoding("UTF-8");
		request.setCharacterEncoding("UTF-8");
		int catId = 0;

		try {
			catId = Integer.parseInt(request.getParameter("cat_id"));
		} catch (NumberFormatException e) {
			response.sendRedirect(request.getContextPath() + "/404");
			return;
		}

		int currentPage = 1;
		try {
			currentPage = Integer.parseInt(request.getParameter("page"));
		} catch (Exception e) {
			currentPage = 1;
		}
		int amountSong = songDAO.getAmount(catId);
		int pages = (int) Math.ceil((float) amountSong / DefineUtil.NUMBER_PER_PAGE);
		if (currentPage > pages || currentPage < 1) {
			currentPage = 1;
		}
		int offset = (currentPage - 1) * DefineUtil.NUMBER_PER_PAGE;
		request.setAttribute("amountSong", amountSong);
		request.setAttribute("pages", pages);
		request.setAttribute("currentPage", currentPage);

		ArrayList<Song> songList = songDAO.getAllSongByCatIdPaging(offset, catId);
		request.setAttribute("songs", songList);
		Category category = catDAO.getCatById(catId);
		if (category == null) {
			response.sendRedirect(request.getContextPath() + "/404");
			return;
		}
		request.setAttribute("category", category);
		request.getRequestDispatcher("/public/cat.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
	}

}
