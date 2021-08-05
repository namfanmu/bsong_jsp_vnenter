package controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.bean.User;
import model.dao.UserDAO;
import util.AuthUtil;
import util.DefineUtil;

public class AdminSearchUserController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private UserDAO userDAO;

	public AdminSearchUserController() {
		super();
		userDAO = new UserDAO();
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
			response.sendRedirect(request.getContextPath() + "/admin/users");
		} else {
			int userAmount = userDAO.getAmountByName(text);
			int pages = (int) Math.ceil((float) userAmount / DefineUtil.NUMBER_PER_PAGE);
			int currentPage = 1;
			try {
				currentPage = Integer.parseInt(request.getParameter("page"));
			} catch (NumberFormatException e) {
			}
			if (currentPage > pages || currentPage < 1) {
				currentPage = 1;
			}
			int offset = (currentPage - 1) * DefineUtil.NUMBER_PER_PAGE;
			request.setAttribute("userAmount", userAmount);
			request.setAttribute("pages", pages);
			request.setAttribute("currentPage", currentPage);

			ArrayList<User> userList = userDAO.getAllUserByNamePaging(text, offset);
			request.setAttribute("userList", userList);
			request.getRequestDispatcher("/admin/user/index.jsp").forward(request, response);
		}
	}

}
