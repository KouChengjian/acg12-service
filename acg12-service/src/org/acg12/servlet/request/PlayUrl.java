package org.acg12.servlet.request;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.acg12.utils.ReptileUtils;

public class PlayUrl extends HttpServlet {

	private static final long serialVersionUID = -6532259880495252927L;

	public PlayUrl() {
		super();
	}
	
	public void init() throws ServletException {
		
	}

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
        ReptileUtils.getPlayUrl(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

	public void destroy() {
		super.destroy(); 
	}
}
