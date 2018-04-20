<%@ page import="org.json.JSONObject" %>
<%@ page import="org.springframework.web.context.support.WebApplicationContextUtils" %>
<%@ page import="org.springframework.context.ApplicationContext" %>
<%@ page import="com.acg12.service.subject.impl.SubjectServiceImpl" %>
<%@ page import="com.acg12.service.subject.SubjectService" %>
<%@ page import="com.acg12.utils.JsonParse" %>
<%@ page import="org.json.JSONArray" %>
<%@ page import="java.util.Iterator" %>
<%@ page import="java.util.Map" %>
<%@ page import="java.util.HashMap" %>
<%@ page import="jdk.nashorn.internal.parser.JSONParser" %>
<%@ page import="com.fasterxml.jackson.databind.util.JSONPObject" %>
<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2018/4/18
  Time: 8:26
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="UTF-8">
    <title>详情</title>
    <link href="css/subject-info.css" rel="stylesheet">
</head>
<body>
<%!
    JSONObject result = null;
%>

<%
    ApplicationContext ctx = WebApplicationContextUtils.getWebApplicationContext(request.getSession().getServletContext());
    SubjectService clientService = (SubjectServiceImpl) ctx.getBean("subjectServiceImpl");
    result = clientService.getSubjectInfo(2);
//    out.println(result.toString());
%>

<div class="container">
    <div class="container-header">
        <div class="banner-detail-form">
            <div class="cover">
                <img src= <%= JsonParse.getString(result , "image")%> >
            </div>

            <div class="info">
                <p class="title">
                    <a href="/manhua3566/" title="<%=JsonParse.getString(result , "name") %>" style="color: #252525;"><%=JsonParse.getString(result , "name") %> </a>
                </p>
                <p class="subtitle">作者：
                    <%
                        String jobs = JsonParse.getJobs(result);
                        String[] s = jobs.split(" ");
                        for (int i = 0 , total = s.length ; i < total ; i++){
                            out.println(" <a href=\"/search?title=%e9%ab%98%e6%a1%a5%e5%bc%a5%e4%b8%83%e9%83%8e&amp;language=1&amp;f=2\" target=\"_blank\">" + s[i] +"</a>&nbsp;&nbsp;");
                        }
                    %>
                </p>
                <p class="tip">
                    <span class="block">放送时间<span><%=JsonParse.getString(result , "airDate") %></span></span>
                    <span class="block">话数<a href="/manhua-rexue/"><span><%=JsonParse.getInt(result , "epsCount") %></span></a></span>
                </p>
                <p class="content">
                    <%=JsonParse.getString(result , "summary") %>
                </p>
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

    <div class="container-info">
        <div class="info-content" >
            <div id="columnA">
                <div class="infobox">
                    <ul id="infobox">
                        <li><span class="tip">中文名: </span><%=JsonParse.getString(result , "nameCn")%></li>
                        <%
                            Map<String , String> stringMap = JsonParse.getStaffs(result);
                            Iterator iterator = stringMap.keySet().iterator();
                            while (iterator.hasNext()) {
                                String key = (String) iterator.next();
                                String value = stringMap.get(key);
                                out.println("<li><span class=\"tip\">" + key +": </span>" );
                                String[] s1 = value.split(" ");
                                for (int i = 0 , total = s1.length ; i < total ; i++){
                                    out.print("<a href=\"/person/1523\" class=\"l\" title=" + s1[i] +">" +s1[i] +"</a>");
                                    if(i < total-1){
                                        out.print("、");
                                    }
                                }
                                out.println("</li>");
                            }

                            JSONArray jsonArray = JsonParse.getJSONArray(result , "details");
                            for (int i = 0 , total = jsonArray.length(); i < total ; i++){
                                JSONObject jsonObject = JsonParse.getJSONObject(jsonArray , i);
                                out.println("<li><span class=\"tip\">" + JsonParse.getString(jsonObject , "otherTitle") +": </span>" +JsonParse.getString(jsonObject , "otherValue")+"</li>");
                            }
                        %>
                    </ul>
                </div>
            </div>
            <div id="columnB">

                <div class="subject-section">
                    <h2 class="subtitle">简介</h2>

                    <div id="subject_summary" class="subject_summary" property="v:summary">
                        <%=JsonParse.getString(result , "summary") %>
                    </div>
                </div>
                <div class="subject-section">
                    <h2 class="subtitle">角色介绍</h2>
                    <div>
                        <ul id="browser-item-list">
                            <%
                                JSONArray crtArray = JsonParse.getJSONArray(result , "crt");
                                for (int i = 0 , total = crtArray.length() ; i < total ; i++){
                                    JSONObject item = JsonParse.getJSONObject(crtArray , i);
                                    out.println("<li class=\"browser-item\"> <div class=\"user-container\"> ");
                                    out.println("<strong>");
                                    out.println("<a href=\"/character/951\" title="+  JsonParse.getString(item , "name")  +" class=\"l\">");
                                    out.println("<img class=\"avatar\" src="+ JsonParse.getString(item , "image") +">" + JsonParse.getString(item , "name"));
                                    out.println("</a> </strong>");

                                    out.print("<div class=\"info\">" + "<span class=\"tip_j\">");
                                    out.print("<small>\n" + "<span class=\"badge_job_tip\">"+ JsonParse.getString(item , "roleName") +"</span>\n" + "</small>");
                                    out.print("<span class=\"tip\">"+JsonParse.getString(item , "nameCn")+"</span>");
                                    if(!JsonParse.getString(item , "personNameCn").isEmpty()){
                                        out.print("<br>CV:");
                                        out.print("<a href=\"/person/3936\" rel=\"v:starring\">"+JsonParse.getString(item , "personNameCn")+"</a>");
                                    }
                                    out.print("</span>");
                                    out.println("</div>");

                                    out.println("</div> </li>");
                                }
                            %>
                        </ul>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <div class="container-foot"></div>
</div>

</body>
</html>
