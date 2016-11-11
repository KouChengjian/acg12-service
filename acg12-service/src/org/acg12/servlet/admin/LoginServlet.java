package org.acg12.servlet.admin;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.acg12.Constant;
import org.acg12.bean.User;
import org.acg12.impl.AcgServiceImpl;
import org.acg12.utils.StringUtil;

import com.google.gson.Gson;

public class LoginServlet extends HttpServlet {

	private static final long serialVersionUID = -5078825933993089829L;

	public LoginServlet() {
		super();
	}

	public void init() throws ServletException {
	}

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		System.out.println(username +"============="+password);
		AcgServiceImpl serviceImpl = new AcgServiceImpl();
		User user = new User();
		user.setUsername(username);
		user.setPassword(password);
		user = serviceImpl.login(user);
		Gson gson = new Gson();
		String content = gson.toJson(user);
		if(user == null){
			StringUtil.weiteData(content,Constant.ERROR,response);
		}else{
			StringUtil.weiteData(content,Constant.SUCCESS,response);
		}
	}

	public void destroy() {
		super.destroy(); 
	}

}
