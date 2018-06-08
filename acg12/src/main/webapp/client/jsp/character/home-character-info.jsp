<%@ page import="org.springframework.context.ApplicationContext" %>
<%@ page import="org.springframework.web.context.support.WebApplicationContextUtils" %>
<%@ page import="com.acg12.common.utils.StringUtil" %>
<%@ page import="com.acg12.modules.app.service.character.impl.CharacterServiceImpl" %>
<%@ page import="com.acg12.modules.app.service.character.CharacterService" %>
<%@ page import="com.acg12.entity.po.character.CharacterEntity" %>
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
    CharacterService characterService = (CharacterServiceImpl) ctx.getBean("characterServiceImpl");
    CharacterEntity result = new CharacterEntity();
    String characterId = (String) request.getAttribute("characterId");
    if (StringUtil.isNumeric(characterId)) {
        result = characterService.queryByCharacterIdJoinDetail(Integer.valueOf(Integer.valueOf(characterId).intValue()));
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
                        <%--<a >职业：<%= StringUtil.getJob(result.getType())%></a>--%>
                    </p>
                    <p class="status">
                        <a><%=StringUtil.getCharacterInfo(result)%></a>
                    </p>
                    <p class="content">
                        <%=result.getSummary()%>
                    </p>

                    <div class="info-expand">
                        <a id="expand"></a><%--查看详情>>--%>
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
