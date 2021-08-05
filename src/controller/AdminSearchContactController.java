package controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.bean.Contact;
import model.dao.ContactDAO;
import util.AuthUtil;
import util.DefineUtil;

public class AdminSearchContactController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private ContactDAO contactDAO;

	public AdminSearchContactController() {
		super();
		contactDAO = new ContactDAO();
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
			response.sendRedirect(request.getContextPath() + "/admin/contacts");
		} else {
			int contactAmount = contactDAO.getAmountByName(text);
			int pages = (int) Math.ceil((float) contactAmount / DefineUtil.NUMBER_PER_PAGE);
			int currentPage = 1;
			try {
				currentPage = Integer.parseInt(request.getParameter("page"));
			} catch (NumberFormatException e) {
			}
			if (currentPage > pages || currentPage < 1) {
				currentPage = 1;
			}
			int offset = (currentPage - 1) * DefineUtil.NUMBER_PER_PAGE;
			request.setAttribute("contactAmount", contactAmount);
			request.setAttribute("pages", pages);
			request.setAttribute("currentPage", currentPage);

			ArrayList<Contact> contactList = contactDAO.getAllContactByNamePaging(text, offset);
			request.setAttribute("contactList", contactList);
			request.getRequestDispatcher("/admin/contact/index.jsp").forward(request, response);
		}

	}

}
