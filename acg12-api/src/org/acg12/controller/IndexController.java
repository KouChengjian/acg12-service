package org.acg12.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.acg12.utils.HttpUtlis;
import org.acg12.utils.ReptileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;


@Controller
public class IndexController {

	@Autowired  
    private HttpServletRequest request;
	
	@Autowired  
    private HttpServletResponse response;
	
	@ResponseBody
	@RequestMapping(value = "/index",method = RequestMethod.GET)
	public void index() {
        ReptileUtils.getHomeContent(request , response);
	}
	
}
