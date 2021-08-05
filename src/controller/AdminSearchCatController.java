package controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.bean.Category;
import model.dao.CatDAO;
import util.AuthUtil;
import util.DefineUtil;

public class AdminSearchCatController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private CatDAO catDAO;

	public AdminSearchCatController() {
		super();
		catDAO = new CatDAO();
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
			response.sendRedirect(request.getContextPath() + "/admin/cats");
		} else {
			int catAmount = catDAO.getAmountByName(text);
			int pages = (int) Math.ceil((float) catAmount / DefineUtil.NUMBER_PER_PAGE);
			int currentPage = 1;
			try {
				currentPage = Integer.parseInt(request.getParameter("page"));
			} catch (NumberFormatException e) {
			}
			if (currentPage > pages || currentPage < 1) {
				currentPage = 1;
			}
			int offset = (currentPage - 1) * DefineUtil.NUMBER_PER_PAGE;
			request.setAttribute("catAmount", catAmount);
			request.setAttribute("pages", pages);
			request.setAttribute("currentPage", currentPage);

			ArrayList<Category> catList = catDAO.getAllCatByNamePaging(text, offset);
			request.setAttribute("catList", catList);
			request.getRequestDispatcher("/admin/cat/index.jsp").forward(request, response);
		}
	}

}
