package org.acg12.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.acg12.utils.ReptileUtils;

public class HomeMore extends HttpServlet {

	private static final long serialVersionUID = -3371558165260833251L;

	public HomeMore() {
		super();
	}
	
	public void init() throws ServletException {
	}

	public void destroy() {
		super.destroy(); 
	}

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String action = request.getParameter("action");
		if(action.equals("album")) {
			ReptileUtils.getHomeAlbumMore(request, response);
		} else if(action.equals("palette")) {
			ReptileUtils.getHomePaletteMore(request, response);
		} else if(action.equals("palettealbum")) {
			ReptileUtils.getHomePaletteAlbumMore(request, response);
		} else if(action.equals("video")) {
			ReptileUtils.getHomeVideoMore(request, response);
		} else if(action.equals("bangumi")) {
			ReptileUtils.getHomeVideoMore(request, response);
		} 
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
