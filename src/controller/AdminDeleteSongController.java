package controller;

import java.io.File;
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.bean.Song;
import model.dao.SongDAO;
import util.AuthUtil;

public class AdminDeleteSongController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private SongDAO songDAO;

	public AdminDeleteSongController() {
		super();
		songDAO = new SongDAO();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
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

		if (songDAO.deleteSong(id) > 0) {
			final String dirPartName = request.getServletContext().getRealPath("/files");
			String picture = song.getPicture();
			if (!picture.isEmpty()) {
				String filePathName = dirPartName + File.separator + picture;
				File file = new File(filePathName);
				if (file.exists()) {
					file.delete();
				}
			}

			response.sendRedirect(request.getContextPath() + "/admin/songs?msg=3");
			return;
		} else {
			response.sendRedirect(request.getContextPath() + "/admin/songs?err=2");
			return;
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
	}

}
