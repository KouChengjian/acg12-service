<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>My JSP 'index.jsp' starting page</title>
  </head>

  <body>
    <h2>JSP开发的几代技术</h2>
    
    <a href="<%=path %>/jsps/test.jsp">第一代JSP开发技术1:纯JSP技术</a><br/><br/><br/>
    <a href="<%=path %>/jsps/Login2.jsp">第一代JSP开发技术2(model1):JSP+EJB技术</a><br/><br/><br/>
    <a href="jsps/login3.jsp">第二代JSP开发技术(model2):MVC框架=JSP+JaveBean+Servlet技术</a><br/>


    <h2>以下演示jsp中bean标记的使用</h2>
    1.申明JavaBean对象必须使用完整类名 
    <jsp:useBean id="u" class="org.acg12.bean.User" scope="session" ></jsp:useBean>
    <!--
         上一句相当于 User u = new User();
    -->
    <%
        u.setName("成吉思汗");
        out.println(u.getName()+"<br/>");
    %>
    2.设置JavaBean属性<br/>
    <jsp:setProperty property="age" name="u" value="21" />
    3.访问JavaBean属性<br/>
    <jsp:getProperty property="name" name="u"/>
    <jsp:getProperty property="age" name="u"/><br/>
         使用"jsp:setProperty"标记为birthday(复杂类型)设置属性:<br/>
    <!--注意,下面的属性名不要用"birth",因为底层是根据下面property中的取值生成对应的setter-getter方式名，再到JavaBean中调用对应方式。  -->
    <!-- 设置属性值 -->
    <jsp:setProperty property="birthday" name="u" value="2016-10-15"/>
    <!-- 拿到属性值 -->
    <jsp:getProperty property="birthday" name="u"/>
    <%out.println(u); %><br/>
    4.通过Parameter属性给JavaBean赋值
    <jsp:useBean id="u2" class="org.acg12.bean.User" scope="session" ></jsp:useBean>
    
    <form action="<%=path %>/jsps/Ope.jsp" method="post" >
        姓名:<input type="text" name="name" /><br/>
        Name:<input type="text" name="nm" /><br/>
        性别:<input type="radio" value="1" name="sex" checked="checked" />男
            <input type="radio" value="0" name="sex" />女<br/>
        年龄:<input type="text" name="age" /><br/>
        <input type="submit" value="登录">
    </form>
  </body>
</html>