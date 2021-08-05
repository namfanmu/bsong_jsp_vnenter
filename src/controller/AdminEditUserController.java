package controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.bean.User;
import model.dao.UserDAO;
import util.AuthUtil;
import util.StringUtil;

public class AdminEditUserController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private UserDAO userDAO;

	public AdminEditUserController() {
		super();
		userDAO = new UserDAO();
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
		int id = 0;
		try {
			id = Integer.parseInt(request.getParameter("id"));
		} catch (NumberFormatException e) {
			response.sendRedirect(request.getContextPath() + "/admin/users?err=2");
			return;
		}

		HttpSession session = request.getSession();
		User userLogin = (User) session.getAttribute("userLogin");
		if ("admin".equals(userDAO.getUserById(userLogin.getId()).getUsername()) || id == userLogin.getId()) {
			User item = userDAO.getUserById(id);
			if (item != null) {
				request.setAttribute("item", item);
				request.getRequestDispatcher("/admin/user/edit.jsp").forward(request, response);
			} else {
				response.sendRedirect(request.getContextPath() + "/admin/users?err=2");
				return;
			}

		} else {
			response.sendRedirect(request.getContextPath() + "/admin/users?err=4");
			return;
		}

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
		int id = 0;
		try {
			id = Integer.parseInt(request.getParameter("id"));
		} catch (NumberFormatException e) {
			response.sendRedirect(request.getContextPath() + "/admin/users?err=2");
			return;
		}

		HttpSession session = request.getSession();
		User userLogin = (User) session.getAttribute("userLogin");
		if ("admin".equals(userDAO.getUserById(userLogin.getId()).getUsername()) || id == userLogin.getId()) {
			String username = request.getParameter("username");
			String password = request.getParameter("password");
			password = StringUtil.md5(password);
			String fullname = request.getParameter("fullname");
			User item = new User(id, username, password, fullname);
			if (userDAO.updateUser(item) > 0) {
				response.sendRedirect(request.getContextPath() + "/admin/users?msg=2");
				return;
			} else {
				request.getRequestDispatcher("/admin/user/edit?err=1").forward(request, response);
				return;
			}
		} else {
			response.sendRedirect(request.getContextPath() + "/admin/users?err=4");
			return;
		}

	}

}
