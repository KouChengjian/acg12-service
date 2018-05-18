<%@ page import="org.springframework.context.ApplicationContext" %>
<%@ page import="org.springframework.web.context.support.WebApplicationContextUtils" %>
<%@ page import="com.acg12.utils.StringUtil" %>
<%@ page import="com.acg12.service.subject.SubjectService" %>
<%@ page import="com.acg12.service.subject.impl.SubjectServiceImpl" %>
<%@ page import="com.acg12.entity.dto.subject.SubjectInfoDto" %>
<%@ page import="java.util.List" %>
<%@ page import="com.acg12.entity.po.subject.SubjectDetailEntity" %>
<%@ page import="com.acg12.entity.po.subject.SubjectCrtEntity" %>
<%@ page import="com.acg12.entity.dto.subject.SubjectStaffDao" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="com.acg12.entity.view.MenuItem" %>
<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2018/5/4
  Time: 10:42
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html >
<head>
    <meta charset="UTF-8">
    <%--<title>详情</title>--%>
    <link rel="stylesheet" href="/acg12/view/css/common/home-header.css">
    <link rel="stylesheet" href="/acg12/view/css/home-subject-info.css" >

    <script type="text/javascript" src="/acg12/view/js/common/home-header.js"></script>
    <script type="text/javascript" src="/acg12/view/js/common/util.js"></script>
</head>
<body>
<%
    ApplicationContext ctx = WebApplicationContextUtils.getWebApplicationContext(request.getSession().getServletContext());
    SubjectService subjectService = (SubjectServiceImpl) ctx.getBean("subjectServiceImpl");
    SubjectInfoDto result = new SubjectInfoDto();
    String sId = (String) request.getAttribute("sId");
    if (StringUtil.isNumeric(sId)) {
        result = subjectService.queryBySIdJoinDetail(Integer.valueOf(Integer.valueOf(sId).intValue()));
        if (result == null){
            result = new SubjectInfoDto();
        }
    }
%>
<div>
    <header id="header-menu"></header>
    <div class="container" style="display: <%=result.getsId()== null ? "none" :"" %>;">
        <div class="container-center">

            <div id="container-preview">
                <div class="info-left">
                    <div class="cover">
                        <img src="<%=result.getImage()%>">
                    </div>
                    <ul id="info-box" style="height: 0px">
                        <%
                            List<ArrayList<SubjectStaffDao>> subjectStaffDaos = StringUtil.getSubjectStaffDaoList(result.getStaff());
                            for (int s = 0 , sn = subjectStaffDaos.size() ; s < sn ; s++){
                                ArrayList<SubjectStaffDao> subjectStaffDaoArrayList = subjectStaffDaos.get(s);
                        %>
                                <li><span class="tip"> <%= subjectStaffDaoArrayList.get(0).getJob()%>: </span>
                        <%
                                for (int s1 = 0 , sn1 = subjectStaffDaoArrayList.size() ; s1 < sn1 ; s1++){
                                    SubjectStaffDao subjectStaffDao = subjectStaffDaoArrayList.get(s1);
                                    if(s1 == 0){
                        %>
                                    <%= subjectStaffDao.getName()%>
                        <%
                                    } else{
                        %>
                                    <%= "、"+subjectStaffDao.getName()%>
                        <%
                                    }
                                }
                        %>
                                </li>
                        <%

                            }
                        %>
                        <%
                            for (int s = 0 , sn = result.getDetails().size() ; s < sn ; s++){
                                SubjectDetailEntity subjectDetailEntity = result.getDetails().get(s);
                        %>
                                <li><span class="tip"><%= subjectDetailEntity.getOtherTitle()%>: </span><%= subjectDetailEntity.getOtherValue()%></li>
                        <%
                            }
                        %>
                        <%--<li><span class="tip">原作: </span>--%>
                            <%--<a href="/person/1523" class="l" title="藤子·F·不二雄">藤子・F・不二雄</a>--%>
                        <%--</li>--%>
                    </ul>
                </div>

                <div class="info-right">
                    <div id="info">
                        <p class="title">
                            <a title="<%= result.getNameCn()%>" style="color: #252525;"><%=result.getName()%></a>
                            <a class="name" title=<%= result.getNameCn()%>><%= result.getNameCn() +"（"+result.getTypeName()+"）"%></a>
                        </p>
                        <p class="subtitle">
                            <%
                                if(result.getAuthor() != null && !result.getAuthor().isEmpty()){
                            %>
                            作者：<a target="_blank"><%=result.getAuthor()%></a>
                            <%
                                }
                            %>

                        </p>
                        <p class="status">
                            <span class="block">开始时间：<span><%=result.getAirDate()%></span></span>
                            <%
                                if(result.getAirDate() != null && result.getAirDate().equals("0000-00-00")){
                            %>
                            <span class="block">话数：<span><%=result.getEpsCount()%></span></span>
                            <%
                                }
                            %>

                            <%
                                if(result.getEpsCount() != null && result.getEpsCount() != 0){
                            %>
                            <span class="block">话数：<span><%=result.getEpsCount()%></span></span>
                            <%
                                }
                            %>
                            <%--<span class="block">话数：<span><%=result.getEpsCount()%></span></span>--%>
                            <!--<span class="block">题材<a href="/manhua-rexue/"><span>热血</span></a>-->
                            </span>
                        </p>
                        <p class="content" id="intro">
                            <%=result.getSummary()%>
                        </p>
                    </div>

                    <div id="info-expand">
                        <a >查看详情>></a>
                    </div>
                    <div id="section" style="height: 0px">
                        <div class="subject-section">
                            <h2 class="subtitle">角色介绍</h2>
                            <ul id="browser-item-list">
                                <%
                                    for (int s = 0 , sn = result.getCrt().size() ; s < sn ; s++){
                                        SubjectCrtEntity subjectCrtEntity = result.getCrt().get(s);
                                %>
                                <li class="browser-item">
                                    <div class="user-container">
                                        <strong>
                                            <a href="/character/951" title="<%=subjectCrtEntity.getName() %>  <%= "/"+ subjectCrtEntity.getNameCn() %>" class="l line">
                                                <img class="avatar" src="<%=subjectCrtEntity.getImage()%>"><%=subjectCrtEntity.getName()%>
                                                <span class="badge_job_tip"><%=subjectCrtEntity.getRoleName()%></span>
                                            </a>
                                        </strong>

                                        <div class="info">
                                        <span class="tip_j">
                                            CV:<a href="/person/3936" rel="v:starring"><%=subjectCrtEntity.getpName()%></a>
                                        </span>
                                        </div>
                                    </div>
                                </li>
                                <%
                                    }
                                %>
                            </ul>
                        </div>
                    </div>
                </div>
            </div>

            <div class="container-tabs">
                <div class="tabs-content">
                    <%--<%--%>
                        <%--List<MenuItem> menuItemList = StringUtil.getSubjectTypeMenuList(result.getType()+"");--%>
                        <%--for (int i = 0 ; i < menuItemList.size() ; i++ ){--%>
                            <%--MenuItem menuItem = menuItemList.get(i);--%>
                    <%--%>--%>
                            <%--<a href="<%=menuItem.getUrl()%>" class="tab "><%=menuItem.getTitle()%></a>--%>
                    <%--<%--%>
                        <%--}--%>
                    <%--%>--%>
                        <a href="/acg12/view/subject/<%=result.getsId()%>" class="tab active">资源</a>
                        <a href="/acg12/view/subject/<%=result.getsId()%>/images" class="tab ">插画</a>
                        <a href="/acg12/view/subject/<%=result.getsId()%>/boards" class="tab">画集</a>
                </div>
            </div>

            <div class="container-info">
                <div class="info-content">

                </div>
            </div>

            <div class="container-foot"></div>
        </div>
    </div>

    <div style="margin-top: 65px; display: <%=result.getsId()== null ? "" :"none" %>;">
        <%@include file="../../error-404.html" %>
    </div>

</div>
<script type="text/javascript" charset="UTF-8">
    <%
    String title = result.getName();
    int n1 = subjectStaffDaos.size() + result.getDetails().size();
    int n2 = result.getCrt().size()/4;
    int n3 = result.getCrt().size()%4;
    if(n3 != 0){
        n2 +=1;
    }
    %>
    var left = <%=n1%>;
    var catLine =<%=n2%>;
    document.title = "<%=title%>";
</script>
<script type="text/javascript" src="/acg12/view/js/home-subject-info.js"></script>
</body>
</html>
