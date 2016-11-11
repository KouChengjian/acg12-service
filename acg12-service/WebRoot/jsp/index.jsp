<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
  
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <title>New Tab</title>
    <meta name="description" content="">
    <meta name="viewport" content="width=device-width">

    <link rel="stylesheet" href="css/default.css">


  </head>
  
      <body background="<%=path %>/imag
      s/bg.png">

      <div id="top-right" class="right">
        <div class="" id="topMenu">
          <ul class="nav navbar-nav navbar-right">
              <li>
                  <a class="chrome-link" data-url="chrome://history" data-action="Chrome History" id="historyLink" href="">History</a>
              </li>
              <li>
                  <a class="chrome-link" data-url="chrome://bookmarks" data-action="Chrome Bookmarks" id="bookmarkLink" href="">Bookmarks</a>
              </li>
              <li>
                  <a class="chrome-link" data-url="chrome://apps" data-action="Chrome Apps" id="appsLink" href="">Apps</a>
              </li>
              
          </ul>
          </div>
       </div>

	<div id="header">
		<h1>City Gallery</h1>
	</div>

	<div id="nav">
		London<br> Paris<br> Tokyo<br>
	</div>

	<div id="section">
		<h1>London</h1>
		<p>London is the capital city of England. It is the most populous
			city in the United Kingdom, with a metropolitan area of over 13
			million inhabitants.</p>
		<p>Standing on the River Thames, London has been a major
			settlement for two millennia, its history going back to its founding
			by the Romans, who named it Londinium.</p>
	</div>

	<div id="footer">Copyright W3School.com.cn</div>

</body>
</html>
