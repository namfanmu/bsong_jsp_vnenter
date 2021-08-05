package controller;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import model.bean.Category;
import model.bean.Song;
import model.dao.CatDAO;
import model.dao.SongDAO;
import util.AuthUtil;
import util.FileUtil;

@MultipartConfig
public class AdminEditSongController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private CatDAO catDAO;
	private SongDAO songDAO;

	public AdminEditSongController() {
		super();
		catDAO = new CatDAO();
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
		int id = 0;
		try {
			id = Integer.parseInt(request.getParameter("id"));
		} catch (NumberFormatException e) {
			response.sendRedirect(request.getContextPath() + "/admin/songs?err=1");
			return;
		}
		Song song = songDAO.getSongById(id);
		if (song == null) {
			response.sendRedirect(request.getContextPath() + "/admin/songs?err=1");
			return;
		}
		request.setAttribute("song", song);
		ArrayList<Category> categoryList = catDAO.getAllCategories();
		request.setAttribute("categoryList", categoryList);
		request.getRequestDispatcher("/admin/song/edit.jsp").forward(request, response);
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
		int catId = 0;
		int id = 0;
		try {
			id = Integer.parseInt(request.getParameter("id"));
			catId = Integer.parseInt(request.getParameter("category"));
		} catch (NumberFormatException e) {
			response.sendRedirect(request.getContextPath() + "/admin/songs?err=1");
			return;
		}
		String name = request.getParameter("name");
		String preview = request.getParameter("preview");
		String detail = request.getParameter("detail");

		Song song = songDAO.getSongById(id);
		if (song == null) {
			response.sendRedirect(request.getContextPath() + "/admin/songs?err=1");
			return;
		}
		Part filePart = request.getPart("picture");
		// tao thu muc luu anh
		final String dirPartName = request.getServletContext().getRealPath("/files");
		File dirFile = new File(dirPartName);
		if (!dirFile.exists()) {
			dirFile.mkdirs();
		}
		String fileName = FileUtil.getName(filePart);

		String picture = "";
		if (fileName.isEmpty()) {
			picture = song.getPicture();
		} else {
			picture = FileUtil.rename(fileName);
		}

		String filePartName = dirPartName + File.separator + picture;

		Song item = new Song(id, name, preview, detail, null, picture, 0, catId);
		if (songDAO.editSong(item) > 0) {
			if (!fileName.isEmpty()) {
				String oldFilePathName = dirPartName + File.separator + song.getPicture();
				File oldFile = new File(oldFilePathName);
				if (oldFile.exists()) {
					oldFile.delete();
				}

				filePart.write(filePartName);
			}
			response.sendRedirect(request.getContextPath() + "/admin/songs?msg=2");
			return;

		} else {
			ArrayList<Category> categoryList = catDAO.getAllCategories();
			request.setAttribute("categoryList", categoryList);
			request.getRequestDispatcher("/admin/song/edit.jsp?err=1").forward(request, response);
			return;
		}
	}

}
