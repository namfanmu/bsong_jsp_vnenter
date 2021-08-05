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

public class AdminAddUserController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private UserDAO userDAO;

	public AdminAddUserController() {
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

		HttpSession session = request.getSession();
		User userLogin = (User) session.getAttribute("userLogin");
		if (!"admin".equals(userLogin.getUsername())) {
			response.sendRedirect(request.getContextPath() + "/admin/users?err=4");
			return;
		}
		request.getRequestDispatcher("/admin/user/add.jsp").forward(request, response);
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
		HttpSession session = request.getSession();
		User userLogin = (User) session.getAttribute("userLogin");
		if (!"admin".equals(userLogin.getUsername())) {
			response.sendRedirect(request.getContextPath() + "/admin/users?err=4");
			return;
		}
		String username = request.getParameter("username");
		if (userDAO.haveUser(username)) {
			request.getRequestDispatcher("/admin/user/add.jsp?err=2").forward(request, response);
			return;
		}
		String password = request.getParameter("password");
		String fullname = request.getParameter("fullname");
		password = StringUtil.md5(password);
		User item = new User(0, username, password, fullname);
		if (userDAO.addUser(item) > 0) {
			response.sendRedirect(request.getContextPath() + "/admin/users?msg=1");
			return;
		} else {
			request.getRequestDispatcher("/admin/user/add.jsp?err=1").forward(request, response);
			return;
		}
	}

}
