<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" errorPage="/erroe.jsp"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
log(path);
log("basePath=="+basePath);
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <TITLE>登录页面</TITLE> 
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	
	<link rel="stylesheet" type="text/css" href="css/login.css">
    <script type="text/javascript" src="js/jquery-1.9.1.min.js"></script>
    <script type="text/javascript" src="js/login.js"></script>
  </head>
  
  <body>
      <div class="top_div"></div>
      <div class="content_div">
          <div style="width: 165px; height: 96px; position: absolute; background-color: #ffffff">
              <div class="tou"></div>
              <div class="initial_left_hand" id="left_hand"></div>
              <div class="initial_right_hand" id="right_hand"></div>
          </div>
          
          <p style="padding: 30px 0px 10px ; position: relative; background-color: #ffffff"">
              <span class="u_logo"></span>
              <input class="ipt" type="text" placeholder="请输入用户名或邮箱" value=""> 
          </p>
          
          <P style="position: relative;">
              <span class="p_logo"></span>
              <input class="ipt" id="password" type="password" placeholder="请输入密码" value="">
          </P>
          
          <div class="btn_div">
              <p style="margin: 0px 35px 20px 45px;">
                  <span style="float: left;">
                      <a style="color: rgb(204, 204, 204);" href="#">忘记密码?</a>
                  </span>
                  <span style="float: right;">
                      <a class="btn_reg" href="${basePath}/jsp/register.jsp">注册</a>
                      <a class="btn_login" href="#">登录</a>
                  </span>
              </p>
          </div>
      </div>
  </body>
</html>
