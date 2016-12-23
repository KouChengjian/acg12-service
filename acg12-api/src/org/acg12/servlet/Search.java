package org.acg12.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.acg12.utils.ReptileUtils;

public class Search extends HttpServlet {

	private static final long serialVersionUID = 7769150504778328631L;

	public Search() {
		super();
	}
	
	public void init() throws ServletException {
		// Put your code here
	}

	/**
	 * Destruction of the servlet. <br>
	 */
	public void destroy() {
		super.destroy(); // Just puts "destroy" string in log
	}

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String action = request.getParameter("action");
		if(action.equals("album")) {
			ReptileUtils.getSearchAlbum(request, response);
		} else if(action.equals("palette")) {
			ReptileUtils.getSearchPalette(request, response);
		} else if(action.equals("video")) {
			ReptileUtils.getSearchVideo(request, response);
		} else if(action.equals("bangumi")) {
			ReptileUtils.getSearchBangunmi(request, response);
		}
		
	}
	

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

	

}
