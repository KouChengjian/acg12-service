<%@ page language="java" import="java.util.*" errorPage="jsps/erroe/error.jsp" pageEncoding="UTF-8"%>
<html>
  <head>
    <title>测试page指令的errorPage属性</title>
  </head>
  <body>
    <%
      //这行代码肯定会出错，因为除数是0，一运行就会抛出异常
      int x = 1/0;
    %>
    
    <h1>sddsadas</h1>
  </body>
</html>