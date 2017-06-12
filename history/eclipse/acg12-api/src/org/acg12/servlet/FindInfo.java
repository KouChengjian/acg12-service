package org.acg12.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.acg12.utils.ReptileUtils;

public class FindInfo extends HttpServlet {

	private static final long serialVersionUID = 6752952237325041195L;

	public FindInfo() {
		super();
	}
	
	public void init() throws ServletException {
	}

	public void destroy() {
		super.destroy(); 
	}
	
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
        ReptileUtils.getFindInfo(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

	

}
