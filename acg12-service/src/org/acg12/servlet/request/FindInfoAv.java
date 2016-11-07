package org.acg12.servlet.request;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.acg12.utils.ReptileUtils;

public class FindInfoAv extends HttpServlet {

	private static final long serialVersionUID = 4691720265709695445L;

	public FindInfoAv() {
		super();
	}
	
	public void init() throws ServletException {
	}

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		ReptileUtils.getFindInfoAv(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
        doGet(request, response);
	}

	public void destroy() {
		super.destroy(); 
	}
	
}
