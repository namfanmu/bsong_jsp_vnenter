package controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.dao.CatDAO;
import model.dao.ContactDAO;
import model.dao.SongDAO;
import model.dao.UserDAO;
import util.AuthUtil;

public class AdminIndexController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private CatDAO catDAO;
	private SongDAO songDAO;
	private UserDAO userDAO;
	private ContactDAO contactDAO;

	public AdminIndexController() {
		super();
		catDAO = new CatDAO();
		songDAO = new SongDAO();
		userDAO = new UserDAO();
		contactDAO = new ContactDAO();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// check login
		if (!AuthUtil.checkLogin(request, response)) {
			response.sendRedirect(request.getContextPath() + "/login");
			return;
		}
		int catAmount = catDAO.getAmount();
		request.setAttribute("catAmount", catAmount);

		int songAmount = songDAO.getAmount();
		request.setAttribute("songAmount", songAmount);

		int userAmount = userDAO.getAmount();
		request.setAttribute("userAmount", userAmount);
		
		int contactAmount = contactDAO.getAmount();
		request.setAttribute("contactAmount", contactAmount);

		request.getRequestDispatcher("/admin/index.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
	}

}
