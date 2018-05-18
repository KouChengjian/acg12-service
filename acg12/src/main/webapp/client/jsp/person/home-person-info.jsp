<%@ page import="org.springframework.context.ApplicationContext" %>
<%@ page import="org.springframework.web.context.support.WebApplicationContextUtils" %>
<%@ page import="com.acg12.service.person.PersonService" %>
<%@ page import="com.acg12.service.person.impl.PersonServiceImpl" %>
<%@ page import="org.json.JSONObject" %>
<%@ page import="com.acg12.utils.StringUtil" %>
<%@ page import="com.acg12.entity.po.person.PersonEntity" %>
<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2018/5/4
  Time: 10:42
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <link rel="stylesheet" href="/acg12/view/css/common/home-header.css">
    <link rel="stylesheet" href="/acg12/view/css/home-person-info.css">

    <script type="text/javascript" src="/acg12/view/js/common/jquery-3.2.0.js"></script>
    <script type="text/javascript" src="/acg12/view/js/common/home-header.js"></script>
    <script type="text/javascript" src="/acg12/view/js/common/util.js"></script>

</head>
<body>

<%
    ApplicationContext ctx = WebApplicationContextUtils.getWebApplicationContext(request.getSession().getServletContext());
    PersonService personService = (PersonServiceImpl) ctx.getBean("personServiceImpl");
    PersonEntity result = new PersonEntity();
    String personId = (String) request.getAttribute("personId");
    if (StringUtil.isNumeric(personId)) {
        result = personService.queryByPersonIdJoinDetail(Integer.valueOf(Integer.valueOf(personId).intValue()));
        if (result == null){
            result = new PersonEntity();
        }
    }
%>

<div>
    <header id="header-menu"></header>
    <div class="container">
        <div class="container-center">
            <div id="container-centerA">
                <div class="info-left">
                    <div class="cover">
                        <img src= <%= result.getImage()%> >
                    </div>
                </div>

                <div class="info-right">
                    <p class="title">
                        <a title="<%= result.getNameCn()%>" style="color: #252525;"><%= result.getName()%></a>
                        <a class="name" title=<%= result.getNameCn()%>><%= result.getNameCn()%></a>
                    </p>
                    <p class="subtitle">
                        <a >职业：<%= StringUtil.getJob(result.getType())%></a>
                    </p>
                    <p class="status">
                        <a><%=StringUtil.getPersonInfo(result)%></a>
                    </p>
                    <p class="content">
                        <%=result.getSummary()%>
                    </p>

                    <div class="info-expand">
                        <a id="expand">查看详情>></a>
                    </div>

                </div>

            </div>

            <div class="container-tabs">
                <div class="tabs-content">
                    <a href="/nonononomad/" class="tab active">详情</a>
                    <a href="/nonononomad/" class="tab ">插画</a>
                    <a href="/nonononomad/" class="tab">画集</a>
                </div>
            </div>
        </div>
    </div>
</div>

<%--<script type="text/javascript" >--%>
<%--var pId = ${requestScope.personId};--%>
<%--//    console.log(pId);--%>
<%--</script>--%>
<script type="text/javascript" src="/acg12/view/js/home-person-info.js"></script>
</body>
</html>
